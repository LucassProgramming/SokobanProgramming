package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LevelTest{

    @Test
    void levelGuardaYCambiaAtributosBasicos() {
        Square[][] capaInf = new Square[2][2];
        Square[][] capaSup = new Square[2][2];
        Score score = new Score();
        Level level = new Level("nivel1", 2, 2, capaInf, capaSup, score, null);

        assertSame(capaInf, level.getCapaInf());
        assertSame(capaSup, level.getCapaSup());
        assertEquals("nivel1", level.getNombre());
        assertEquals(2, level.getFilas());
        assertEquals(2, level.getColumnas());
        assertSame(score, level.getPuntuacion());

        level.setNombre("nuevo");
        level.setFilas(3);
        level.setColumnas(4);
        Score nuevo = new Score();
        nuevo.setPuntuacion(5);
        level.setPuntuacion(nuevo);

        assertEquals("nuevo", level.getNombre());
        assertEquals(3, level.getFilas());
        assertEquals(4, level.getColumnas());
        assertEquals(5, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void incrementarAumentaLaPuntuacionDelNivel() {
        Square[][] capaInf = new Square[1][1];
        Square[][] capaSup = new Square[1][1];
        Score score = new Score();
        Level level = new Level("l",1,1,capaInf,capaSup,score,null);

        assertEquals(0, level.getPuntuacion().getPuntuacion());
        level.incrementar();
        assertEquals(1, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void estaCompletadoDevuelveFalsoCuandoHayGoalSinCaja() {
        int filas = 2, columnas = 2;
        Square[][] capaInf = new Square[filas][columnas];
        Square[][] capaSup = new Square[filas][columnas];
        // Colocar una meta sin caja encima
        capaInf[0][0] = new Goal(0,0);
        Level level = new Level("l", filas, columnas, capaInf, capaSup, new Score(), null);

        assertFalse(level.estaCompletado());
    }

    @Test
    void estaCompletadoDevuelveTrueCuandoTodasLasMetasTienenCajas() {
        int filas = 2, columnas = 2;
        Square[][] capaInf = new Square[filas][columnas];
        Square[][] capaSup = new Square[filas][columnas];
        capaInf[0][0] = new Goal(0,0);
        capaSup[0][0] = new Box(0,0);
        // Añadir una celda sin meta para asegurar no interfiere
        capaInf[1][1] = new Square(1,1);

        Level level = new Level("l", filas, columnas, capaInf, capaSup, new Score(), null);

        assertTrue(level.estaCompletado());
    }

    @Test
    void equalsYHashCodeConsistentesConMismosValores() {
        int filas = 1, columnas = 1;
        Square[][] capaInfA = new Square[filas][columnas];
        Square[][] capaSupA = new Square[filas][columnas];
        Square[][] capaInfB = new Square[filas][columnas];
        Square[][] capaSupB = new Square[filas][columnas];

        capaInfA[0][0] = new Goal(0,0);
        capaSupA[0][0] = new Box(0,0);

        capaInfB[0][0] = new Goal(0,0);
        capaSupB[0][0] = new Box(0,0);

        Score s1 = new Score();
        s1.setPuntuacion(3);
        Score s2 = new Score();
        s2.setPuntuacion(3);

        Level l1 = new Level("igual", filas, columnas, capaInfA, capaSupA, s1, null);
        Level l2 = new Level("igual", filas, columnas, capaInfB, capaSupB, s2, null);

        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

}