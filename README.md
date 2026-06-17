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

- Java 11 or higher
- Maven 3.6+
- JavaFX 17 (downloaded automatically via Maven)

## How to run

```bash
mvn javafx:run
```

## Controls

### Keyboard

| Key | Action |
|---|---|
| `W` / `↑` | Move up |
| `S` / `↓` | Move down |
| `A` / `←` | Move left |
| `D` / `→` | Move right |

### Buttons

| Button | Action |
|---|---|
| **Undo** | Undo the last move |
| **Restart** | Restart the current level from the beginning (shows confirmation dialog) |
| **Save** | Open the save/load screen |
| **Menú** | Return to the main menu (shows confirmation dialog if a game is in progress) |
| **Audio +** | Increase music volume |
| **Audio -** | Decrease music volume |

## How to play

1. Launch the game and click **New Game** from the main menu.
2. Use the keyboard to move the golem (character).
3. Push every box onto a goal square.
4. When all goals are covered, the level is complete and the next one loads automatically.
5. The score counts the total number of moves made across all levels.
6. When all levels are completed, a summary screen shows the total score per level.
7. Closing the window ends the application cleanly (music is stopped and the JVM exits).

## Music

Music is implemented using `javafx-media` (GStreamer on Linux). The game handles missing audio support gracefully — if the required system libraries are not available, the game runs silently without any crash or error visible to the player.

### Music on the course VM (Ubuntu 24.04, Java 11)

The course VM likely does not have GStreamer MP3 plugins installed, so music will not play. The rest of the game is fully functional.

To enable music on Ubuntu 24.04, the following packages must be installed:

```bash
sudo apt install gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly gstreamer1.0-libav
```

For full music support including the Minecraft-style tracks bundled in the game, **Java 17 or higher** combined with JavaFX 21 is the recommended environment. On Java 11 + JavaFX 17, music works if GStreamer is properly installed, but this combination has not been validated on the course VM.

## Files read by the application

### Level files

- **Location:** `src/main/resources/levels/`
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

1. Create a new text file in `src/main/resources/levels/`.
2. Name it `Level_N.txt` where N follows the last existing number.
3. Follow the format described above.
4. The level will be loaded automatically the next time the game starts.

## Saving and loading a game

1. During a game, click **Save**.
2. Select one of the three available slots (a chest icon marks occupied slots).
3. Click **SAVE GAME** to save, or **LOAD GAME** to restore a previously saved state.

To load a game from the main menu, click **Load Game** and select a slot.

## Architecture

The project follows the **MVC (Model-View-Controller)** pattern:

- **Model** (`model/dto/classes/`) — game state and logic. No JavaFX dependency.
- **View** (`view/`) — JavaFX scenes and widgets. Each view class uses **composition** (wraps a JavaFX node internally and exposes it via `getRoot()`) instead of extending JavaFX classes directly, keeping the inheritance hierarchy within the 5-level limit.
- **Controller** (`controller/`) — bridges input and model. `MenuController` handles navigation and scene transitions; `GameController` translates key presses into model calls and triggers view updates.

### Board representation

Each `Level` holds two overlapping `Square[][]` grids:
- `capaInf` — static layer: walls, goal squares, empty floor.
- `capaSup` — dynamic layer: the player and boxes (`null` means empty).

### Move flow

On each key press, `GameController` calls `CurrentGameState.moverPersonaje()`, which delegates to `CharacterManager` (player movement and bounds checking) and `BoxManager` (box pushing and collision). After each move, `actualizarVistas()` redraws the board and updates the score bar.

### Undo / Restart

`LevelRecorder` holds a static deque of deep-copied level snapshots. Each valid move pushes a snapshot. `undo()` pops the last snapshot; `restart()` reloads the initial snapshot saved when the level was first set.

### Application lifecycle

`App.java` registers a close-request handler on the JavaFX `Stage`. When the window is closed (or the **Exit** / **Menú** button is used), `MenuController.cerrarApp()` is called, which disposes the media player, calls `Platform.exit()`, and then `System.exit(0)` to ensure no background threads keep the JVM alive.

### Exceptions

Level loading validates the file and throws specific runtime exceptions for each broken invariant:

| Exception | Trigger |
|---|---|
| `CajaNotFoundInLevelException` | No box found in the level |
| `GoalNotFoundInLevelException` | No goal found in the level |
| `PlayableCharacterNotFoundInLevelException` | No player found in the level |
| `GoalsAndBoxesArentEqualsException` | Box count ≠ goal count |
| `LevelDoesntExistException` | Level file not found |

## Logging

The application uses two logging frameworks:

| Framework | Used in | Output |
|---|---|---|
| SLF4J (`slf4j-simple`) | `SaveSlotManager`, `MenuController`, `LevelFileReader` | `stderr` (console) |
| `java.util.logging` | `MainMenuView`, `MusicView` | `stderr` (console) |

No log files are written to disk. All messages appear in the terminal where `mvn javafx:run` was launched. The current log messages cover:

- Save/load file errors (`SaveSlotManager`)
- Font loading failure at startup (`MainMenuView`)
- Music initialization failure (`MusicView`)
- Game state transitions (`MenuController`)

There is no logging configuration file; `slf4j-simple` uses its defaults (INFO level and above).

## Running tests

```bash
mvn test
```

The test suite contains **207 tests** across **16 test classes**, covering the entire model layer (view and controller classes are excluded from coverage as they require a running JavaFX environment).

After running, the JaCoCo coverage report is available at:

```
target/site/jacoco/index.html
```

## SonarQube analysis

```bash
mvn verify sonar:sonar -Dsonar.id=YOUR_ID -Dsonar.token=YOUR_TOKEN
```

Coverage exclusions (configured in `pom.xml`): `**/view/**`, `**/controller/**`, `App.java`, `SokobanService.java`.

## Project structure

```
├── src/
│   ├── main/
│   │   ├── java/es/upm/pproject/sokoban/
│   │   │   ├── App.java                                    ← JavaFX entry point
│   │   │   ├── controller/
│   │   │   │   ├── MenuController.java                     ← Navigation, scene transitions, save/load
│   │   │   │   └── GameController.java                     ← Keyboard input and move orchestration
│   │   │   ├── model/
│   │   │   │   ├── service/
│   │   │   │   │   └── SokobanService.java                 ← Service interface (placeholder)
│   │   │   │   ├── dto/
│   │   │   │   │   ├── classes/
│   │   │   │   │   │   ├── Square.java                     ← Base grid cell (x, y position)
│   │   │   │   │   │   ├── Wall.java                       ← Wall entity
│   │   │   │   │   │   ├── Goal.java                       ← Goal square entity
│   │   │   │   │   │   ├── Box.java                        ← Box entity (pushable)
│   │   │   │   │   │   ├── PlayableCharacter.java          ← Player entity
│   │   │   │   │   │   ├── Direccion.java                  ← Movement direction (row/col increments)
│   │   │   │   │   │   ├── Score.java                      ← Per-level move counter
│   │   │   │   │   │   ├── GameScore.java                  ← Total score across levels
│   │   │   │   │   │   ├── Level.java                      ← Single level: grids, score, character
│   │   │   │   │   │   ├── CurrentGameState.java           ← Full game state (serializable)
│   │   │   │   │   │   ├── CharacterManager.java           ← Player movement and bounds logic
│   │   │   │   │   │   ├── BoxManager.java                 ← Box pushing and collision logic
│   │   │   │   │   │   ├── LevelRecorder.java              ← Undo/restart history (deque of snapshots)
│   │   │   │   │   │   ├── LevelFileReader.java            ← Parses level .txt files
│   │   │   │   │   │   └── SaveSlotManager.java            ← Save/load .dat files
│   │   │   │   │   ├── interfaces/
│   │   │   │   │   │   ├── ILevel.java
│   │   │   │   │   │   ├── ICurrentGameState.java
│   │   │   │   │   │   ├── IBoxManager.java
│   │   │   │   │   │   ├── IScore.java
│   │   │   │   │   │   ├── IGameScore.java
│   │   │   │   │   │   ├── ISaveSlotManager.java
│   │   │   │   │   │   └── ILevelRecorder.java
│   │   │   │   │   └── exceptions/
│   │   │   │   │       ├── LevelDoesntExistException.java
│   │   │   │   │       ├── CajaNotFoundInLevelException.java
│   │   │   │   │       ├── GoalNotFoundInLevelException.java
│   │   │   │   │       ├── PlayableCharacterNotFoundInLevelException.java
│   │   │   │   │       ├── GoalsAndBoxesArentEqualsException.java
│   │   │   │   │       └── CouldntCloneException.java
│   │   │   └── view/
│   │   │       ├── MainMenuView.java                       ← Main menu screen
│   │   │       ├── MainGameView.java                       ← In-game button bar
│   │   │       ├── BoardView.java                          ← Game grid renderer
│   │   │       ├── GameInfoView.java                       ← Score and level info bar
│   │   │       ├── GameCompleteView.java                   ← World completion screen
│   │   │       ├── SaveGameView.java                       ← Save/load slot screen
│   │   │       └── MusicView.java                          ← Music playback controller
│   │   └── resources/
│   │       ├── levels/                                     ← Level_1.txt, Level_2.txt, Level_3.txt
│   │       ├── images/                                     ← Game sprites (golem, box, wall, goal…)
│   │       ├── music/                                      ← zelda_song.mp3, musica_minecraft.mp3
│   │       └── css/                                        ← style.css, Minecraftia-Regular.ttf
│   └── test/
│       └── java/es/upm/pproject/sokoban/model/
│           ├── dto/classes/                                ← 15 test classes (model layer)
│           └── exceptions/                                 ← ExceptionsTest.java
└── pom.xml
```
