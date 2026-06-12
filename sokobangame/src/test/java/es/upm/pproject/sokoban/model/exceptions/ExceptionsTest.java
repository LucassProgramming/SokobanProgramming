package es.upm.pproject.sokoban.model.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExceptionsTest {

    @Test
    void cajaNotFoundExceptionContainsLevelName() {
        CajaNotFoundInLevelException ex = new CajaNotFoundInLevelException("nivel1");
        assertTrue(ex.getMessage().contains("nivel1"));
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    void goalNotFoundExceptionContainsLevelName() {
        GoalNotFoundInLevelException ex = new GoalNotFoundInLevelException("nivel2");
        assertTrue(ex.getMessage().contains("nivel2"));
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    void goalsAndBoxesArentEqualsExceptionContainsLevelName() {
        GoalsAndBoxesArentEqualsException ex = new GoalsAndBoxesArentEqualsException("nivel3");
        assertTrue(ex.getMessage().contains("nivel3"));
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    void levelDoesntExistExceptionContainsFileName() {
        LevelDoesntExistException ex = new LevelDoesntExistException("archivo.txt");
        assertTrue(ex.getMessage().contains("archivo.txt"));
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    void playableCharacterNotFoundExceptionContainsLevelName() {
        PlayableCharacterNotFoundInLevelException ex = new PlayableCharacterNotFoundInLevelException("nivel5");
        assertTrue(ex.getMessage().contains("nivel5"));
        assertTrue(ex instanceof RuntimeException);
    }

    @Test
    void couldntCloneExceptionWrapsOriginalCause() {
        CloneNotSupportedException cause = new CloneNotSupportedException("no-clone");
        CouldntCloneException ex = new CouldntCloneException(cause);
        assertNotNull(ex.getMessage());
        assertTrue(ex instanceof RuntimeException);
    }
}
