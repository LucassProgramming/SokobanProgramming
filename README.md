# Sokoban

Classic Sokoban puzzle game built with Java and JavaFX. Push all the boxes onto the goal squares to complete each level.

## Authors

| Name | Registration number |
|---|---|
| Jiaxu He | 230226 |
| Lucas Daniel Benítez Maidana | 230223 |
| Denis Andrei Cosor Strimbeanu | 230069 |
| Mateo Cuñarro Alfonsín | 230245 |

## Requirements

- Java 17 or higher
- Maven 3.6+
- JavaFX 21 (downloaded automatically via Maven)
- **Linux only:** GStreamer + PulseAudio for music (see [Linux audio setup](#linux-audio-setup))

## How to run

```bash
mvn javafx:run
```

## Controls

### Keyboard

| Key | Action |
|---|---|
| `W` | Move up |
| `S` | Move down |
| `A` | Move left |
| `D` | Move right |

### Buttons

| Button | Action |
|---|---|
| **Undo** | Undo the last move |
| **Restart** | Restart the current level from the beginning |
| **Save** | Open the save/load screen |
| **Menú** | Return to the main menu |
| **Audio +** | Increase music volume |
| **Audio -** | Decrease music volume |

## How to play

1. Launch the game and click **New Game** from the main menu.
2. Use the keyboard to move the golem (character).
3. Push every box (`#`) onto a goal square (`*`).
4. When all goals are covered, the level is complete and the next one loads automatically.
5. The score counts the total number of moves made across all levels.

## Files read by the application

### Level files

- **Location:** `sokobangame/src/main/resources/levels/`
- **Naming:** `Level_1.txt`, `Level_2.txt`, `Level_3.txt`, …
- **Loaded at startup.** All files matching the pattern are loaded in order; loading stops at the first missing number.

**Format:**

```
Nivel 1          ← Level name (line 1)
8 8              ← Number of rows and columns (line 2)
++++             ← Grid rows, one per line
+  +
+  +++++
+      +
++W*+# +
+   +  +
+   ++++
+++++
```

**Cell symbols:**

| Symbol | Meaning |
|---|---|
| `+` | Wall (impassable) |
| ` ` | Empty floor |
| `*` | Goal square |
| `#` | Box |
| `W` | Starting position of the player |

**Rules for a valid level file:**
- Exactly one `W` (player).
- At least one `#` (box) and one `*` (goal).
- Number of boxes must equal number of goals.
- Rows may be shorter than the declared column count; missing cells are treated as empty floor.

### Save files

- **Location:** Project root directory (same folder as `pom.xml`).
- **Names:** `slot1.dat`, `slot2.dat`, `slot3.dat`
- **Format:** Java binary serialization of the full game state (`CurrentGameState`). Not human-readable.
- The save file stores the complete game state: current level, all levels loaded, score, and move history.

### Image and audio assets

All assets are bundled inside the JAR and are not meant to be modified by the user.

| Path | Contents |
|---|---|
| `resources/images/` | Sprites: golem, boxes, walls, goals, backgrounds |
| `resources/music/` | Background music tracks (`.mp3`) |
| `resources/css/` | UI stylesheet and custom font |

## Files written by the application

| File | When created | Contents |
|---|---|---|
| `slot1.dat` | When saving to slot 1 | Serialized game state |
| `slot2.dat` | When saving to slot 2 | Serialized game state |
| `slot3.dat` | When saving to slot 3 | Serialized game state |

Save files are created or overwritten each time the player saves. They can be deleted manually to clear a slot.

## Adding new levels

1. Create a new text file in `sokobangame/src/main/resources/levels/`.
2. Name it `Level_N.txt` where N follows the last existing number.
3. Follow the format described above.
4. The level will be loaded automatically the next time the game starts.

## Saving and loading a game

1. During a game, click **Save**.
2. Select one of the three available slots (a chest icon marks occupied slots).
3. Click **SAVE GAME** to save, or **LOAD GAME** to restore a previously saved state.

To load a game from the main menu, click **Load Game** and select a slot.

## Linux audio setup

On Linux (including WSL2 with WSLg), the following packages are required for music playback:

```bash
sudo apt install libavformat58 pulseaudio
echo 'export PULSE_SERVER=unix:/mnt/wslg/PulseServer' >> ~/.bashrc
source ~/.bashrc
```

On native Linux (not WSL2), only the first line is needed. PulseAudio is typically already running.

## Architecture

The project follows the **MVC (Model-View-Controller)** pattern:

- **Model** (`model/dto/classes/`) — game state and logic. No JavaFX dependency.
- **View** (`view/`) — JavaFX scenes and widgets. Only reads from the model, never modifies it directly.
- **Controller** (`controller/`) — bridges input and model. `MenuController` handles navigation; `GameController` translates key presses into model calls and triggers view updates.

The game board uses two overlapping grids (`Square[][]`):
- `capaInf` — static layer: walls, goal squares, empty floor.
- `capaSup` — dynamic layer: the player and boxes (null means empty).

On each move `GameController` calls `CurrentGameState.moverPersonaje()`, which delegates to `CharacterManager` (player movement) and `BoxManager` (box pushing). After each move, `actualizarVistas()` redraws the board.

Undo history is kept in `LevelRecorder` as a stack of serialized snapshots. Restart reloads the initial snapshot stored when the level was first loaded.

## Logging

The application uses two logging frameworks:

| Framework | Used in | Output |
|---|---|---|
| SLF4J (`slf4j-simple`) | `SaveSlotManager`, `MenuController`, `LevelFileReader` | `stderr` (console) |
| `java.util.logging` | `MainMenuView` | `stderr` (console) |

No log files are written to disk. All messages appear in the terminal where `mvn javafx:run` was launched. The current log messages cover:

- Save/load file errors (`SaveSlotManager`)
- Font loading failure at startup (`MainMenuView`)

There is no logging configuration file; `slf4j-simple` uses its defaults (INFO level and above).

## Running tests

```bash
mvn test
```

After running, the JaCoCo coverage report is available at:

```
sokobangame/target/site/jacoco/index.html
```

## SonarQube analysis

```bash
mvn verify sonar:sonar -Dsonar.id=YOUR_ID -Dsonar.token=YOUR_TOKEN
```

## Project structure

```
sokobangame/
├── src/
│   ├── main/
│   │   ├── java/es/upm/pproject/sokoban/
│   │   │   ├── App.java                        ← JavaFX entry point
│   │   │   ├── controller/
│   │   │   │   ├── MenuController.java         ← Navigation and game flow
│   │   │   │   └── GameController.java         ← Keyboard input and move orchestration
│   │   │   ├── model/dto/classes/
│   │   │   │   ├── CurrentGameState.java       ← Full game state (serializable)
│   │   │   │   ├── Level.java                  ← Single level: grid, score, character
│   │   │   │   ├── LevelRecorder.java          ← Undo/restart history (stack)
│   │   │   │   ├── LevelFileReader.java        ← Parses level .txt files
│   │   │   │   ├── SaveSlotManager.java        ← Save/load .dat files
│   │   │   │   ├── CharacterManager.java       ← Player movement logic
│   │   │   │   ├── BoxManager.java             ← Box movement and collision
│   │   │   │   ├── PlayableCharacter.java      ← Player entity
│   │   │   │   ├── Box.java                    ← Box entity
│   │   │   │   ├── Goal.java                   ← Goal square entity
│   │   │   │   ├── Wall.java                   ← Wall entity
│   │   │   │   ├── Square.java                 ← Base grid cell
│   │   │   │   ├── Score.java                  ← Per-level move counter
│   │   │   │   ├── GameScore.java              ← Total score across levels
│   │   │   │   └── Direccion.java              ← Movement direction (row/col increments)
│   │   │   └── view/
│   │   │       ├── MainMenuView.java           ← Main menu screen
│   │   │       ├── MainGameView.java           ← In-game button bar
│   │   │       ├── BoardView.java              ← Game grid renderer (GridPane)
│   │   │       ├── GameInfoView.java           ← Score and level info bar
│   │   │       ├── SaveGameView.java           ← Save/load slot screen
│   │   │       └── MusicView.java              ← Music playback controller
│   │   └── resources/
│   │       ├── levels/                         ← Level definition files
│   │       ├── images/                         ← Game sprites
│   │       ├── music/                          ← Background music
│   │       └── css/                            ← Stylesheet and font
│   └── test/                                   ← JUnit 5 tests
└── pom.xml
```
