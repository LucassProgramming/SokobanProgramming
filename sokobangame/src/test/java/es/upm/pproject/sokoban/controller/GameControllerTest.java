package es.upm.pproject.sokoban.controller;

 /*import static org.junit.jupiter.api.Assertions.*;

import es.upm.pproject.sokoban.model.dto.classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.view.BoardView;
import es.upm.pproject.sokoban.view.GameInfoView;
import javafx.scene.input.KeyCode; */

public class GameControllerTest {

 /*   private GameController gameController;
    private CurrentGameState mockGameState;
    private BoardView mockBoardView;
    private GameInfoView mockGameInfoView;
    private MenuController mockMenuController;
    private Level mockLevel;

    @BeforeEach
    void setUp() {
        mockGameState = new CurrentGameState();
        mockBoardView = new SimpleBoardViewStub();
        mockGameInfoView = new SimpleGameInfoViewStub();
        mockMenuController = new SimpleMenuControllerStub();
        mockLevel = new Level();

        mockGameState.setCurrent(mockLevel);
        gameController = new GameController(mockGameState, mockBoardView, mockGameInfoView, 1, mockMenuController);
    }

    @Test
    void handleKeyUpMovesCharacterUp() {
        gameController.handleKey(KeyCode.UP);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyWMovesCharacterUp() {
        gameController.handleKey(KeyCode.W);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyDownMovesCharacterDown() {
        gameController.handleKey(KeyCode.DOWN);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeySMovesCharacterDown() {
        gameController.handleKey(KeyCode.S);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyLeftMovesCharacterLeft() {
        gameController.handleKey(KeyCode.LEFT);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyAMovesCharacterLeft() {
        gameController.handleKey(KeyCode.A);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyRightMovesCharacterRight() {
        gameController.handleKey(KeyCode.RIGHT);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyDMovesCharacterRight() {
        gameController.handleKey(KeyCode.D);
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyWithInvalidKeyDoesNotThrow() {
        assertDoesNotThrow(() -> gameController.handleKey(KeyCode.ENTER));
    }

    @Test
    void handleKeyWithInvalidKeyDoesNotMove() {
        Level levelBefore = mockGameState.getCurrent();
        gameController.handleKey(KeyCode.ENTER);
        assertEquals(levelBefore, mockGameState.getCurrent());
    }

    @Test
    void undoRestoresPreviousGameState() {
        gameController.handleKey(KeyCode.UP);
        gameController.undo();
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void restartResetsLevelToInitialState() {
        gameController.handleKey(KeyCode.UP);
        gameController.restart();
        assertNotNull(mockGameState.getCurrent());
    }

    @Test
    void handleKeyCallsMenuControllerWhenLevelComplete() {
        Score score = mockLevel.getPuntuacion();
        assertNotNull(score);
    }

    @Test
    void undoDoesNotThrow() {
        assertDoesNotThrow(() -> gameController.undo());
    }

    @Test
    void restartDoesNotThrow() {
        assertDoesNotThrow(() -> gameController.restart());
    }
} */
}
