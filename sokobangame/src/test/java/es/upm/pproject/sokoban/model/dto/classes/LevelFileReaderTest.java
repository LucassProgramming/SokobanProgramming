package es.upm.pproject.sokoban.model.dto.classes;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.exceptions.CajaNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalsAndBoxesArentEqualsException;
import es.upm.pproject.sokoban.model.exceptions.LevelDoesntExistException;
import es.upm.pproject.sokoban.model.exceptions.PlayableCharacterNotFoundInLevelException;

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

    @Test
    void crearNivelSinCajasLanzaExcepcion() {
        assertThrows(CajaNotFoundInLevelException.class,
                () -> LevelFileReader.crearNivel("/levels/invalid/NoBox.txt"));
    }

    @Test
    void crearNivelSinGoalsLanzaExcepcion() {
        assertThrows(GoalNotFoundInLevelException.class,
                () -> LevelFileReader.crearNivel("/levels/invalid/NoGoal.txt"));
    }

    @Test
    void crearNivelSinPersonajeLanzaExcepcion() {
        assertThrows(PlayableCharacterNotFoundInLevelException.class,
                () -> LevelFileReader.crearNivel("/levels/invalid/NoPlayer.txt"));
    }

    @Test
    void crearNivelConMasCajasQueGoalsLanzaExcepcion() {
        assertThrows(GoalsAndBoxesArentEqualsException.class,
                () -> LevelFileReader.crearNivel("/levels/invalid/TwoBoxesOneGoal.txt"));
    }

    @Test
    void crearNivelAceptaRutaSinBarraInicial() {
        Level level = LevelFileReader.crearNivel("levels/Level_1.txt");
        assertEquals("Nivel 1", level.getNombre());
    }

    @Test
    @DisplayName("Devuelve lista vacia si ocurre un error leyendo el archivo")
    void leerLineasConErrorDevuelveVacio() throws Exception {
        Method metodo = LevelFileReader.class.getDeclaredMethod("leerLineas", InputStream.class);
        metodo.setAccessible(true);
        Object lineas = metodo.invoke(null, new InputStream(){
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        });

        assertEquals("[]", lineas.toString());
    }
}
