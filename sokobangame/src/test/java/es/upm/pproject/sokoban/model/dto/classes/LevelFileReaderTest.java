package es.upm.pproject.sokoban.model.dto.classes;

import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.exceptions.LevelDoesntExistException;

import static org.junit.jupiter.api.Assertions.*;

 class LevelFileReaderTest {

    @Test
     void crearNivelProducesBoxesGoalsAndSinglePlayableCharacter() {
        Level level = LevelFileReader.crearNivel("/levels/Level_2.txt");
        int boxes = 0;
        int goals = 0;
        int playableChars = 0;
        Square[][] capaInf = level.getCapaInf();
        Square[][] capaSup = level.getCapaSup();
        for (int i = 0; i < level.getFilas(); i++) {
            for (int j = 0; j < level.getColumnas(); j++) {
                if (capaSup[i][j] instanceof Box)
                    boxes++;
                if (capaInf[i][j] instanceof Goal)
                    goals++;
                if (capaSup[i][j] instanceof PlayableCharacter)
                    playableChars++;
            }
        }
        assertTrue(boxes > 0);
        assertTrue(goals > 0);
        assertEquals(boxes, goals);
        assertEquals(1, playableChars);
        assertNotNull(level.getCharacter());
    }

    @Test
     void cThrowsWhenFileNotFound() {
        assertThrows(LevelDoesntExistException.class,
                () -> LevelFileReader.crearNivel("/levels/NoSuchLevel.txt"));
    }

    @Test
     void cargarTodosLosNivelesEachLevelMeetsBasicValidation() {
        java.util.List<Level> niveles = LevelFileReader.cargarTodosLosNiveles();
        for (Level level : niveles) {
            int boxes = 0;
            int goals = 0;
            int playableChars = 0;
            Square[][] capaInf = level.getCapaInf();
            Square[][] capaSup = level.getCapaSup();
            for (int i = 0; i < level.getFilas(); i++) {
                for (int j = 0; j < level.getColumnas(); j++) {
                    if (capaSup[i][j] instanceof Box)
                        boxes++;
                    if (capaInf[i][j] instanceof Goal)
                        goals++;
                    if (capaSup[i][j] instanceof PlayableCharacter)
                        playableChars++;
                }
            }
            assertTrue(boxes > 0);
            assertTrue(goals > 0);
            assertEquals(boxes, goals);
            assertEquals(1, playableChars);
        }
    }
}
