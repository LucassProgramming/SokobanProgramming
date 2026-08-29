# Sokoban

Desktop implementation of the classic **Sokoban puzzle game**, developed with **Java and JavaFX** as a collaborative academic project for the **Programming Project** course at **Universidad Politécnica de Madrid (UPM)**.

The project was originally developed using UPM's institutional **GitLab** and later migrated to GitHub while preserving the original commit history, authors and development timeline.

## Tech Stack

- **Java**
- **JavaFX**
- **Maven**
- **JUnit 5**
- **JaCoCo**
- **SonarQube**
- **Git**
- **GitLab CI**

## Key Features

- Multiple Sokoban levels loaded dynamically from external files
- Graphical user interface built with JavaFX
- Player movement and box collision logic
- Undo and level restart functionality
- Level progression and completion screens
- Per-level and total score tracking
- Three persistent save slots
- Save/load system using Java serialization
- Background music support
- Level validation and custom exception handling
- Automated unit testing
- Code coverage analysis with JaCoCo
- Static code quality analysis with SonarQube
- Continuous Integration using GitLab CI

---

## Academic Context

This project was developed by a team of four students as part of the **Programming Project** course at **Universidad Politécnica de Madrid (UPM)**.

Development was carried out collaboratively through the university's institutional GitLab environment, using Git for version control and GitLab CI for Continuous Integration.

The repository was later migrated to GitHub while preserving the original commits, authors, dates, branches and development history.

## Team

- Jiaxu He
- Lucas Daniel Benítez Maidana
- Denis Andrei Cosor Strimbeanu
- Mateo Cuñarro Alfonsín

---

## Requirements

- Java 11 or higher
- Maven 3.6+
- JavaFX 17 — downloaded automatically through Maven

## Running the Application

Clone the repository and run:

```bash
mvn javafx:run
```

---

## How to Play

1. Launch the application.
2. Select **New Game** from the main menu.
3. Move the player around the board.
4. Push every box onto a goal square.
5. Complete the level using as few moves as possible.
6. Continue to the next level from the completion screen.
7. Complete all available levels to reach the final summary.

### Keyboard Controls

| Key | Action |
|---|---|
| `W` / `↑` | Move up |
| `S` / `↓` | Move down |
| `A` / `←` | Move left |
| `D` / `→` | Move right |

### Interface Controls

| Button | Action |
|---|---|
| **Undo** | Undo the previous move |
| **Restart** | Restart the current level |
| **Save** | Open the save/load screen |
| **Menú** | Return to the main menu |
| **Audio +** | Increase music volume |
| **Audio -** | Decrease music volume |

---

## Software Architecture

The application follows the **Model-View-Controller (MVC)** architectural pattern.

```text
                ┌──────────────┐
                │     View     │
                │    JavaFX    │
                └──────┬───────┘
                       │
                       ▼
                ┌──────────────┐
                │  Controller  │
                └──────┬───────┘
                       │
                       ▼
                ┌──────────────┐
                │    Model     │
                │  Game Logic  │
                └──────────────┘
```

### Model

The model contains the game state and core game logic and has no dependency on JavaFX.

Its main responsibilities include:

- Player movement
- Box movement and collisions
- Level representation
- Score management
- Undo and restart history
- Save/load functionality
- Level parsing and validation

### View

The graphical interface is implemented using **JavaFX**.

The main views include:

- `MainMenuView`
- `MainGameView`
- `BoardView`
- `GameInfoView`
- `LevelCompletedView`
- `GameCompleteView`
- `SaveGameView`
- `MusicView`

Composition is preferred over directly extending JavaFX classes. Each view encapsulates its corresponding JavaFX node and exposes it through `getRoot()`.

### Controller

Controllers coordinate the interaction between the graphical interface and the model.

The main controllers are:

- `MenuController` — navigation, scene transitions and save/load operations
- `GameController` — keyboard input, game actions and view updates

---

## Board Representation

Each `Level` contains two overlapping `Square[][]` grids.

### Static Layer

```text
capaInf
```

Contains:

- Walls
- Goal squares
- Empty floor

### Dynamic Layer

```text
capaSup
```

Contains:

- Player
- Boxes

A `null` value represents an empty dynamic cell.

This separation allows static board elements and movable entities to be managed independently.

---

## Movement System

Player actions follow this flow:

```text
Keyboard input
      │
      ▼
GameController
      │
      ▼
CurrentGameState.moverPersonaje()
      │
      ├── CharacterManager
      │
      └── BoxManager
      │
      ▼
Updated model
      │
      ▼
View refresh
```

`CharacterManager` handles player movement and boundary checking.

`BoxManager` handles:

- Box pushing
- Collision detection
- Destination validation

After every valid move, the board and score interface are refreshed.

---

## Undo and Restart

The application implements an undo system through `LevelRecorder`.

A deque stores deep copies of previous level states.

```text
Move
 │
 ▼
Store level snapshot
 │
 ▼
Apply movement
```

### Undo

Restores the previous stored level state.

### Restart

Restores the initial snapshot created when the level was loaded.

---

## Level Completion

After every move, the application checks whether every box has been placed on a goal square.

When a level is completed, an intermediate screen displays:

- Score for the completed level
- Number of completed levels
- Total number of levels

The player can then choose:

| Option | Action |
|---|---|
| **Siguiente Nivel** | Continue to the next level |
| **Reiniciar Nivel** | Replay the completed level |
| **Volver al menú** | Return to the main menu |

After completing the final level, the application displays a summary with the total and per-level scores.

---

## Saving and Loading

The game provides three persistent save slots.

Save files are stored in the project root:

```text
slot1.dat
slot2.dat
slot3.dat
```

The complete game state is persisted using Java serialization.

A save file contains:

- Current level
- Loaded levels
- Current score
- Level scores
- Move history
- Complete game state

### Saving

1. Click **Save**
2. Select one of the three slots
3. Select **SAVE GAME**

### Loading

1. Open the save/load screen
2. Select an occupied slot
3. Select **LOAD GAME**

---

## Level System

Levels are stored in:

```text
src/main/resources/levels/
```

Files follow the naming convention:

```text
Level_1.txt
Level_2.txt
Level_3.txt
...
```

The application loads them sequentially at startup.

### Level Format

Example:

```text
Nivel 1
8 8
++++++++
+      +
+  +++++
+      +
++W*+# +
+      +
+   ++++
++++++++
```

### Symbols

| Symbol | Meaning |
|---|---|
| `+` | Wall |
| ` ` | Empty floor |
| `*` | Goal |
| `#` | Box |
| `W` | Player starting position |

### Validation Rules

A valid level must contain:

- Exactly one player
- At least one box
- At least one goal
- The same number of boxes and goals

Invalid level files trigger specific application exceptions.

---

## Exception Handling

The level loader validates input files and throws specific exceptions when an invariant is violated.

| Exception | Trigger |
|---|---|
| `CajaNotFoundInLevelException` | No box exists |
| `GoalNotFoundInLevelException` | No goal exists |
| `PlayableCharacterNotFoundInLevelException` | No player exists |
| `GoalsAndBoxesArentEqualsException` | Number of boxes differs from number of goals |
| `LevelDoesntExistException` | Requested level file does not exist |

---

## Testing

Run the automated test suite with:

```bash
mvn test
```

The project contains:

**207 automated tests across 16 test classes**

The test suite focuses primarily on the model layer, covering areas such as:

- Player movement
- Box movement
- Collision handling
- Score management
- Level loading
- Game state management
- Undo and restart functionality
- Exceptions
- Save/load behavior

View and controller classes are excluded from coverage because they require an active JavaFX environment.

---

## Code Coverage

The project uses **JaCoCo** to measure test coverage.

After running the tests, the HTML coverage report is generated at:

```text
target/site/jacoco/index.html
```

Coverage exclusions configured in `pom.xml` include:

```text
**/view/**
**/controller/**
App.java
SokobanService.java
```

---

## Continuous Integration

The project used **GitLab CI** during its original development on the institutional GitLab instance of **Universidad Politécnica de Madrid (UPM)**.

The pipeline contained two main jobs.

### Maven Build & Tests

The first job executed the Maven test lifecycle inside a `maven:3.6.3-jdk-11` container:

```bash
mvn test
```

This automated the compilation and execution of the project's JUnit test suite. **JaCoCo** was integrated into the Maven test lifecycle to generate code coverage data.

### SonarQube Analysis

A second job performed static code analysis with **SonarQube** on the `main` branch:

```bash
mvn verify sonar:sonar
```

The SonarQube connection and authentication values were provided through **GitLab CI environment variables**, keeping credentials outside the repository.

The SonarQube job was configured with `allow_failure: true`, meaning that an analysis failure did not cause the entire pipeline to fail.

The original CI configuration is preserved in:

```text
.gitlab-ci.yml
```

Although the project has been migrated to GitHub, this file remains in the repository as part of the original academic project's development history.

## SonarQube

Static code analysis was performed using **SonarQube**, integrated with Maven and the GitLab CI pipeline.

The analysis was executed on the `main` branch using:

```bash
mvn verify sonar:sonar
```

SonarQube configuration and authentication were supplied through GitLab CI environment variables such as:

```text
SONAR_LOGIN
SONAR_HOST_URL
SONAR_ID
```

This avoided storing authentication credentials directly in the pipeline configuration.

SonarQube was used together with **JaCoCo** to analyse aspects such as:

- Code quality
- Maintainability
- Technical debt
- Test coverage

The SonarQube analysis was configured as an allowed-to-fail CI job and therefore did not block the rest of the pipeline if the analysis failed.

## Logging

The application uses:

- **SLF4J (`slf4j-simple`)**
- **java.util.logging**

Logging covers:

- Save/load failures
- Font loading issues
- Audio initialization failures
- Game state transitions

Logs are written to the console. No log files are persisted to disk.

---

## Music

Background music is implemented using JavaFX Media.

The application handles environments without multimedia support gracefully. If the required audio libraries are unavailable, the game continues running without music.

On Linux, JavaFX Media relies on GStreamer.

For Ubuntu-based systems, the required packages can be installed with:

```bash
sudo apt install \
  gstreamer1.0-plugins-base \
  gstreamer1.0-plugins-good \
  gstreamer1.0-plugins-ugly \
  gstreamer1.0-libav
```

Java 17 or higher is recommended for better JavaFX Media compatibility.

---

## Project Structure

```text
src/
├── main/
│   ├── java/es/upm/pproject/sokoban/
│   │   ├── App.java
│   │   │
│   │   ├── controller/
│   │   │   ├── MenuController.java
│   │   │   └── GameController.java
│   │   │
│   │   ├── model/
│   │   │   ├── service/
│   │   │   │   └── SokobanService.java
│   │   │   │
│   │   │   └── dto/
│   │   │       ├── classes/
│   │   │       │   ├── Square.java
│   │   │       │   ├── Wall.java
│   │   │       │   ├── Goal.java
│   │   │       │   ├── Box.java
│   │   │       │   ├── PlayableCharacter.java
│   │   │       │   ├── Direccion.java
│   │   │       │   ├── Score.java
│   │   │       │   ├── GameScore.java
│   │   │       │   ├── Level.java
│   │   │       │   ├── CurrentGameState.java
│   │   │       │   ├── CharacterManager.java
│   │   │       │   ├── BoxManager.java
│   │   │       │   ├── LevelRecorder.java
│   │   │       │   ├── LevelFileReader.java
│   │   │       │   └── SaveSlotManager.java
│   │   │       │
│   │   │       ├── interfaces/
│   │   │       │   ├── ILevel.java
│   │   │       │   ├── ICurrentGameState.java
│   │   │       │   ├── IBoxManager.java
│   │   │       │   ├── IScore.java
│   │   │       │   ├── IGameScore.java
│   │   │       │   ├── ISaveSlotManager.java
│   │   │       │   └── ILevelRecorder.java
│   │   │       │
│   │   │       └── exceptions/
│   │   │           ├── LevelDoesntExistException.java
│   │   │           ├── CajaNotFoundInLevelException.java
│   │   │           ├── GoalNotFoundInLevelException.java
│   │   │           ├── PlayableCharacterNotFoundInLevelException.java
│   │   │           ├── GoalsAndBoxesArentEqualsException.java
│   │   │           └── CouldntCloneException.java
│   │   │
│   │   └── view/
│   │       ├── MainMenuView.java
│   │       ├── MainGameView.java
│   │       ├── BoardView.java
│   │       ├── GameInfoView.java
│   │       ├── LevelCompletedView.java
│   │       ├── GameCompleteView.java
│   │       ├── SaveGameView.java
│   │       └── MusicView.java
│   │
│   └── resources/
│       ├── levels/
│       ├── images/
│       ├── music/
│       └── css/
│
└── test/
    └── java/es/upm/pproject/sokoban/model/
        ├── dto/classes/
        └── exceptions/

pom.xml
```

---

## Repository History

This repository preserves the original collaborative development history from the institutional GitLab environment used during the course.

The commit history reflects the contributions of the different team members throughout the development of the project.
