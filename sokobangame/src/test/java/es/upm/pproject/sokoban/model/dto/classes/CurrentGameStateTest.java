package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CurrentGameStateTest {

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
        cs.añadirLevel(level);
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
}
