package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CurrentGameStateTest {

    @Test
    void getArrayReturnsLength99AndStartsEmpty() {
        CurrentGameState cs = new CurrentGameState();
        assertEquals(99, cs.getArray().length);
        assertNull(cs.getArray()[0]);
    }

    @Test
    void añadirLevelAddsLevelAndIncrementsIndex() {
        CurrentGameState cs = new CurrentGameState();
        Level level = new Level();
        cs.anadirLevel(level);
        assertEquals(1, cs.getIndex());
        assertSame(level, cs.getArray()[0]);
    }

    @Test
    void setCurrentSetsCurrentAndRegistersInicio() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[2][2];
        Square[][] capaSup = new Square[2][2];
        Score puntuacion = new Score();
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L1", 2, 2, capaInf, capaSup, puntuacion, pc);
        cs.setCurrent(level);
        assertEquals(1, cs.getIndex());
        Level restarted = LevelRecorder.restart();
        assertNotNull(restarted);
        assertEquals("L1", restarted.getNombre());
    }

    @Test
    void moverPersonajeIncreasesGameScoreWhenMoveHappens() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        Score puntuacion = new Score();
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L2", 3, 3, capaInf, capaSup, puntuacion, pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        assertEquals(0, cs.getPuntuacionTotal().getTotal());
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(1, cs.getPuntuacionTotal().getTotal());
        assertEquals(1, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void moverPersonajeDoesNotIncreaseWhenBlockedByWall() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        Score puntuacion = new Score();
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L3", 3, 3, capaInf, capaSup, puntuacion, pc);
        capaSup[0][0] = pc;
        capaInf[0][1] = new Wall(0, 1);
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(0, cs.getPuntuacionTotal().getTotal());
        assertEquals(0, level.getPuntuacion().getPuntuacion());
        assertSame(level.getCapaSup()[0][0], pc);
    }

    @Test
    void reversionEstadoRestoresPreviousStateAndDecrementsTotal() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        Score puntuacion = new Score();
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L4", 3, 3, capaInf, capaSup, puntuacion, pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(1, cs.getPuntuacionTotal().getTotal());
        cs.reversionEstado();
        assertEquals(0, cs.getPuntuacionTotal().getTotal());
        assertEquals(0, cs.getCurrent().getPuntuacion().getPuntuacion());
    }

    @Test
    void reversionEstadoConIndiceMayorQueCeroActualizaArrayLevels() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        Score puntuacion = new Score();
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("R1", 3, 3, capaInf, capaSup, puntuacion, pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        // realizar un movimiento que se guarde
        cs.moverPersonaje(new Direccion(0, 1));
        // ahora hay un estado anterior y el index debe ser > 0
        assertTrue(cs.getIndex() > 0);
        cs.reversionEstado();
        // comprobar que el arrayLevels en index-1 fue actualizado con el current restaurado
        assertSame(cs.getCurrent(), cs.getArray()[cs.getIndex() - 1]);
    }
    @Test
    void reversionEstadoConMalIndiceNoAnade(){
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs1 = new CurrentGameState();
        cs1.setCurrent(new Level("Buenas", 0, 0, new Square[2][2], new Square[2][2],
         new Score(), new PlayableCharacter(0, 0)));
        cs1.setIndex(0);
        cs1.moverPersonaje(new Direccion(1, 0));
       assertDoesNotThrow(() -> cs1.reversionEstado()); 
        
    }
    @Test
    void reversionEstadoConPilaVaciaNoHaceNada() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L5", 3, 3, capaInf, capaSup, new Score(), pc);
        cs.setCurrent(level);
        cs.reversionEstado();
        assertEquals(0, cs.getPuntuacionTotal().getTotal());
    }

    @Test
    void restartRestoresPuntuacionCero() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L6", 3, 3, capaInf, capaSup, new Score(), pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1));
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(2, cs.getPuntuacionTotal().getTotal());
        cs.restart();
        assertEquals(0, cs.getPuntuacionTotal().getTotal());
        assertEquals(0, cs.getCurrent().getPuntuacion().getPuntuacion());
    }

    @Test
    void restartConIndiceMayorQueCeroActualizaArrayLevels() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("R2", 3, 3, capaInf, capaSup, new Score(), pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1));
        cs.moverPersonaje(new Direccion(0, 1));
        assertTrue(cs.getIndex() > 0);
        cs.restart();
        assertSame(cs.getCurrent(), cs.getArray()[cs.getIndex() - 1]);
    }
    @Test
    void restartNoRevierteSiEstaVacio() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("L6", 3, 3, capaInf, capaSup, new Score(), pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1));
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(2, cs.getPuntuacionTotal().getTotal());
        Level currentAnt = cs.getCurrent();
        LevelRecorder.setInicio(null);
        cs.restart();
        assertSame(currentAnt,cs.getCurrent());
    }

    @Test
    void setIndexActualizaIndice() {
        CurrentGameState cs = new CurrentGameState();
        cs.setIndex(7);
        assertEquals(7, cs.getIndex());
    }

    @Test
    void equalsConMismoEstado() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        boolean estado = cs1.equals(cs2);
        assertTrue(estado);
    }
    @Test
    void equalsConMismoEstadoNoVacio() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        cs1.anadirLevel(new Level());
        cs2.anadirLevel(new Level());
        cs1.getPuntuacionTotal().setTotal(1);
        cs2.getPuntuacionTotal().setTotal(1);
        cs1.setCurrent(new Level("Buenas", 0, 0, new Square[2][2], new Square[2][2],
         new Score(), new PlayableCharacter(0, 0)));
        cs2.setCurrent(new Level("Buenas", 0, 0, new Square[2][2], new Square[2][2],
         new Score(), new PlayableCharacter(0, 0)));
        boolean estado = cs1.equals(cs2);
        assertTrue(estado);
    }
    @Test
    void equalsNoMismaTipoInstancia(){
        CurrentGameState cs1 = new CurrentGameState();
        Level cs2 = new Level();
        assertNotEquals(cs1, cs2);
    }

    @Test
    void equalsConObjetoNuloDevuelveFalso() {
        CurrentGameState cs = new CurrentGameState();
        assertNotEquals(cs, null);
    }

    @Test
    void equalsConIndexDistintoDevuelveFalso() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        cs1.setIndex(1);
        cs2.setIndex(0);
        assertNotEquals(cs1, cs2);
    }

    @Test
    void equalsConArrayLevelsDistintoDevuelveFalso() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        cs1.anadirLevel(new Level());
        assertNotEquals(cs1, cs2);
    }

    @Test
    void equalsConPuntuacionTotalDistintaDevuelveFalso() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        cs1.getPuntuacionTotal().setTotal(5);
        cs2.getPuntuacionTotal().setTotal(0);
        assertNotEquals(cs1, cs2);
    }

    @Test
    void equalsConCurrentDistintoDevuelveFalso() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        Level l1 = new Level("A", 1, 1, new Square[1][1], new Square[1][1], new Score()
                , new PlayableCharacter(0,0));
        Level l2 = new Level("B", 1, 1, new Square[1][1], new Square[1][1], new Score(),
                new PlayableCharacter(0,0));
        cs1.setCurrent(l1);
        cs2.setCurrent(l2);
        assertNotEquals(cs1, cs2);
    }

    @Test
    void hashCodeConsistenteConEquals() {
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        assertEquals(cs1.hashCode(), cs2.hashCode());
    }

    // Cubre la rama FALSE de "if (index > 0)" en reversionEstado cuando undo devuelve no-nulo.
    // Con index=0, arrayLevels[index-1] sería arrayLevels[-1] → ArrayIndexOutOfBoundsException
    // si el if no existiera. Verifica que el movimiento se deshace igualmente (puntuacion baja).
    @Test
    void reversionEstadoConIndexCeroYUndoNoNuloNoCrashea() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("T1", 3, 3, capaInf, capaSup, new Score(), pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level);
        cs.moverPersonaje(new Direccion(0, 1)); // movimiento válido → LevelRecorder.save() llamado
        assertEquals(1, cs.getPuntuacionTotal().getTotal());
        cs.setIndex(0); // forzar index=0 aunque haya estado guardado
        assertDoesNotThrow(() -> cs.reversionEstado()); // no debe lanzar ArrayIndexOutOfBoundsException
        assertEquals(0, cs.getPuntuacionTotal().getTotal()); // restar() se ejecutó igualmente
    }

    // Cubre la rama FALSE de "if (index > 0)" en restart cuando restart() devuelve no-nulo.
    @Test
    void restartConIndexCeroYInicioNoNuloNoCrashea() {
        LevelRecorder.reiniciarDeque();
        CurrentGameState cs = new CurrentGameState();
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        Level level = new Level("T2", 3, 3, capaInf, capaSup, new Score(), pc);
        capaSup[0][0] = pc;
        cs.setCurrent(level); // setInicio() llamado con level
        cs.moverPersonaje(new Direccion(0, 1));
        cs.moverPersonaje(new Direccion(0, 1));
        assertEquals(2, cs.getPuntuacionTotal().getTotal());
        cs.setIndex(0); // forzar index=0
        assertDoesNotThrow(() -> cs.restart()); // no debe lanzar ArrayIndexOutOfBoundsException
        assertEquals(0, cs.getPuntuacionTotal().getTotal()); // puntuacion restada
    }

    // Cubre el return true de equals con current no-nulo usando la misma instancia de
    // PlayableCharacter (que no tiene equals propio) para que Level.equals devuelva true.
    @Test
    void equalsConSoloCurrentDistintoDevuelveFalso() throws Exception {
        // Cubre D (Objects.equals(current, that.current)) = FALSE con A, B, C = TRUE.
        // La única forma sin cambiar la API es fijar current via reflection.
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        // index=0, arrays=[null...], puntuacion=0 en ambos → A, B, C = TRUE
        java.lang.reflect.Field f = CurrentGameState.class.getDeclaredField("current");
        f.setAccessible(true);
        f.set(cs1, new Level("X", 1, 1, new Square[1][1], new Square[1][1],
                new Score(), new PlayableCharacter(0, 0)));
        // cs1.current != null, cs2.current == null → D = FALSE
        assertNotEquals(cs1, cs2);
    }

    @Test
    void equalsDevuelveTrueConCurrentNoNuloIgual() {
        LevelRecorder.reiniciarDeque();
        PlayableCharacter sharedPc = new PlayableCharacter(0, 0);
        Square[][] capaInf = new Square[2][2];
        Square[][] capaSup = new Square[2][2];
        Level l1 = new Level("X", 2, 2, capaInf, capaSup, new Score(), sharedPc);
        Level l2 = new Level("X", 2, 2, capaInf, capaSup, new Score(), sharedPc);
        CurrentGameState cs1 = new CurrentGameState();
        CurrentGameState cs2 = new CurrentGameState();
        cs1.setCurrent(l1);
        LevelRecorder.reiniciarDeque();
        cs2.setCurrent(l2);
        assertEquals(cs1, cs2);
    }
}
