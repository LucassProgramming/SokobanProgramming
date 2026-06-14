package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void notEqualsConObjetoDeOtraClase() {
        Level level = new Level();
        assertNotEquals("nivel", level);
    }


    @Test
    void notEqualsConCapaInfDistinta() {
        Square[][] ci1 = new Square[1][1];
        Square[][] ci2 = new Square[1][1];
        Square[][] cs = new Square[1][1];
        ci1[0][0] = new Square(0, 0);
        ci2[0][0] = new Square(0, 1);
        Level l1 = new Level("A", 1, 1, ci1, cs, new Score(), null);
        Level l2 = new Level("A", 1, 1, ci2, cs, new Score(), null);
        assertNotEquals(l1, l2);
    }

    @Test
    void notEqualsConCapaSupDistinta() {
        Square[][] ci = new Square[1][1];
        Square[][] cs1 = new Square[1][1];
        Square[][] cs2 = new Square[1][1];
        cs1[0][0] = new Box(0, 0);
        Level l1 = new Level("A", 1, 1, ci, cs1, new Score(), null);
        Level l2 = new Level("A", 1, 1, ci, cs2, new Score(), null);
        assertNotEquals(l1, l2);
    }

    @Test
    void notEqualsConPuntuacionDistinta() {
        Square[][] ci = new Square[1][1];
        Square[][] cs = new Square[1][1];
        Score s1 = new Score();
        Score s2 = new Score();
        s2.setPuntuacion(1);
        Level l1 = new Level("A", 1, 1, ci, cs, s1, null);
        Level l2 = new Level("A", 1, 1, ci, cs, s2, null);
        assertNotEquals(l1, l2);
    }

    @Test
    void notEqualsConPersonajeDistinto() {
        Square[][] ci = new Square[1][1];
        Square[][] cs = new Square[1][1];
        Level l1 = new Level("A", 1, 1, ci, cs, new Score(), new PlayableCharacter(0, 0));
        Level l2 = new Level("A", 1, 1, ci, cs, new Score(), new PlayableCharacter(0, 1));
        assertNotEquals(l1, l2);
    }

    @Test
    void setCapaInfActualizaReferencia() {
        Square[][] ci = new Square[2][2];
        Square[][] cs = new Square[2][2];
        Level level = new Level("l", 2, 2, ci, cs, new Score(), null);
        Square[][] nuevaCapaInf = new Square[3][3];
        level.setCapaInf(nuevaCapaInf);
        assertSame(nuevaCapaInf, level.getCapaInf());
    }

    @Test
    void setCapaSupActualizaReferencia() {
        Square[][] ci = new Square[2][2];
        Square[][] cs = new Square[2][2];
        Level level = new Level("l", 2, 2, ci, cs, new Score(), null);
        Square[][] nuevaCapaSup = new Square[3][3];
        level.setCapaSup(nuevaCapaSup);
        assertSame(nuevaCapaSup, level.getCapaSup());
    }

    @Test
    void getCharacterDevuelvePersonaje() {
        Square[][] ci = new Square[2][2];
        Square[][] cs = new Square[2][2];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("l", 2, 2, ci, cs, new Score(), pc);
        assertSame(pc, level.getCharacter());
    }

    @Test
    void moverPersonajeDelegaEnCharacterManager() {
        LevelRecorder.reiniciarDeque();
        Square[][] ci = new Square[3][3];
        Square[][] cs = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        cs[0][0] = pc;
        Level level = new Level("l", 3, 3, ci, cs, new Score(), pc);
        LevelRecorder.setInicio(level);
        level.moverPersonaje(new Direccion(0, 1));
        assertSame(pc, cs[0][1]);
        assertEquals(1, level.getPuntuacion().getPuntuacion());
    }


    @ParameterizedTest
    @CsvSource({"A, 1, 1, B, 1, 1", "A, 1, 1, A, 2, 1","A, 1, 1, A, 1, 2" })
    void notEqualsConDatosBasicosDistintos(String nombre1, int filas1, int columnas1,
        String nombre2, int filas2, int columnas2){
        Square[][] ci = new Square[1][1];
        Square[][] cs = new Square[1][1];

        Level l1 = new Level(nombre1, filas1, columnas1, ci, cs, new Score(), null);
        Level l2 = new Level(nombre2, filas2, columnas2, ci, cs, new Score(), null);

        assertNotEquals(l1, l2);
    }
}
