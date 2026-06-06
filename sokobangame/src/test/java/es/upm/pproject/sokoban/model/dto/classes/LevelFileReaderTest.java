package es.upm.pproject.sokoban.model.dto.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelFileReaderTest {

    @Test
    public void levelFileReaderTest() {

    }
    @Test public void CrearNivelProducesBoxesGoalsAndSinglePlayableCharacter() {
        Level level = LevelFileReader.CrearNivel("/levels/Level_2.txt");
        int boxes = 0; int goals = 0; int playableChars = 0; Square[][] capaInf = level.getCapaInf();
        Square[][] capaSup = level.getCapaSup();
        for (int i = 0; i < level.getFilas(); i++) {
            for (int j = 0; j < level.getColumnas(); j++) {
                if (capaSup[i][j] instanceof Box) boxes++;
                if (capaInf[i][j] instanceof Goal) goals++;
                if (capaSup[i][j] instanceof PlayableCharacter) playableChars++;
            } } assertTrue(boxes > 0);
        assertTrue(goals > 0);
        assertEquals(boxes, goals);
        assertEquals(1, playableChars);
        assertNotNull(level.getCharacter());
    }
    @Test public void CrearNivelThrowsWhenFileNotFound() {
        assertThrows(RuntimeException.class,
                () -> LevelFileReader.CrearNivel("/levels/NoSuchLevel.txt"));
    }
    @Test public void cargarTodosLosNivelesEachLevelMeetsBasicValidation() {
        java.util.ArrayList<Level> niveles = LevelFileReader.cargarTodosLosNiveles();
        for(Level level : niveles) {
            int boxes = 0; int goals = 0;
            int playableChars = 0;
            Square[][] capaInf = level.getCapaInf();
            Square[][] capaSup = level.getCapaSup();
            for (int i = 0; i < level.getFilas(); i++) {
                for (int j = 0; j < level.getColumnas(); j++) {
                    if (capaSup[i][j] instanceof Box) boxes++;
                    if (capaInf[i][j] instanceof Goal) goals++;
                    if (capaSup[i][j] instanceof PlayableCharacter) playableChars++;
                }
            }
            assertTrue(boxes > 0);
            assertTrue(goals > 0);
            assertEquals(boxes, goals);
            assertEquals(1, playableChars); } }
}
