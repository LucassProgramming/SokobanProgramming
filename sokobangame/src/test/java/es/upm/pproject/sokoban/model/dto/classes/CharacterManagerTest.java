package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterManagerTest {

    private PlayableCharacter pers;
    private Level level;
    private Square[][] capaInf;
    private Square[][] capaSup;

    @BeforeEach
    void setUp() {
        LevelRecorder.reiniciarDeque();
        capaInf = new Square[4][4];
        capaSup = new Square[4][4];
        pers = new PlayableCharacter(1, 1);
        capaSup[1][1] = pers;
        level = new Level("test", 4, 4, capaInf, capaSup, new Score(), pers);
    }

    @Test
    void noSeMueveCuandoHayMuroDelante() {
        capaInf[1][2] = new Wall(1, 2);
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertSame(pers, capaSup[1][1]);
        assertNull(capaSup[1][2]);
    }

    @Test
    void seMueveAEspacioLibre() {
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertSame(pers, capaSup[1][2]);
        assertNull(capaSup[1][1]);
    }

    @Test
    void incrementaScoreAlMoverse() {
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertEquals(1, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void noIncrementaScoreCuandoEstaBlockeado() {
        capaInf[1][2] = new Wall(1, 2);
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertEquals(0, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void empujaCajaAEspacioLibre() {
        Box caja = new Box(1, 2);
        capaSup[1][2] = caja;
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertSame(pers, capaSup[1][2]);
        assertSame(caja, capaSup[1][3]);
        assertNull(capaSup[1][1]);
    }

    @Test
    void noPuedeEmpujarCajaContraMuro() {
        Box caja = new Box(1, 2);
        capaSup[1][2] = caja;
        capaInf[1][3] = new Wall(1, 3);
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertSame(pers, capaSup[1][1]);
        assertSame(caja, capaSup[1][2]);
        assertEquals(0, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void noPuedeEmpujarCajaContraCaja() {
        Box caja1 = new Box(1, 2);
        Box caja2 = new Box(1, 3);
        capaSup[1][2] = caja1;
        capaSup[1][3] = caja2;
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertSame(pers, capaSup[1][1]);
        assertSame(caja1, capaSup[1][2]);
        assertEquals(0, level.getPuntuacion().getPuntuacion());
    }

    @Test
    void seMueveHaciaArriba() {
        CharacterManager.moverPersonaje(level, pers, new Direccion(-1, 0));
        assertSame(pers, capaSup[0][1]);
        assertNull(capaSup[1][1]);
    }

    @Test
    void seMueveHaciaAbajo() {
        CharacterManager.moverPersonaje(level, pers, new Direccion(1, 0));
        assertSame(pers, capaSup[2][1]);
    }

    @Test
    void empujaCajaIncrementaScore() {
        Box caja = new Box(1, 2);
        capaSup[1][2] = caja;
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertEquals(1, level.getPuntuacion().getPuntuacion());
    }
}
