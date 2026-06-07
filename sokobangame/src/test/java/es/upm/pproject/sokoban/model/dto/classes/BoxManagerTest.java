package es.upm.pproject.sokoban.model.dto.classes;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class BoxManagerTest {

    private Square[][] capaInf;
    private Square[][] capaSup;
    private Level level;
    private BoxManager manager;
    private Box box;

    @BeforeEach
    void setUp() {
        capaInf = new Square[4][4];
        capaSup = new Square[4][4];
        level = new Level(capaInf, capaSup);
        manager = new BoxManager(level);
        box = new Box(1, 1);
        capaSup[1][1] = box;
    }

    @Test
    void moveBoxBloqueadoPorMuroRetornaFalso() {
        capaInf[1][2] = new Wall(1, 2);
        manager.setCaja(box);
        boolean resultado = manager.moveBox(level, 0, 1);
        assertFalse(resultado);
    }

    @Test
    void moveBoxBloqueadoCajaNoMueveX() {
        capaInf[1][2] = new Wall(1, 2);
        manager.setCaja(box);
        manager.moveBox(level, 0, 1);
        assertEquals(1, box.getX());
    }

    @Test
    void moveBoxBloqueadoCajaNoMueveY() {
        capaInf[1][2] = new Wall(1, 2);
        manager.setCaja(box);
        manager.moveBox(level, 0, 1);
        assertEquals(1, box.getY());
    }

    @Test
    void moveBoxBloqueadoCajaPermaneceEnCapaSup() {
        capaInf[1][2] = new Wall(1, 2);
        manager.setCaja(box);
        manager.moveBox(level, 0, 1);
        assertSame(box, capaSup[1][1]);
    }

    @Test
    void moveBoxLibreRetornaVerdadero() {
        manager.setCaja(box);
        boolean resultado = manager.moveBox(level, 1, 0);
        assertTrue(resultado);
    }

    @Test
    void moveBoxLibreActualizaCoordenadaX() {
        manager.setCaja(box);
        manager.moveBox(level, 1, 0);
        assertEquals(2, box.getX());
    }

    @Test
    void moveBoxLibreActualizaCoordenadaY() {
        manager.setCaja(box);
        manager.moveBox(level, 0, 1);
        assertEquals(2, box.getY());
    }

    @Test
    void moveBoxLibreActualizaCapaSupDestino() {
        manager.setCaja(box);
        manager.moveBox(level, 1, 0);
        assertSame(box, capaSup[2][1]);
    }

    @Test
    void moveBoxLibreLimpiaCasillaOrigen() {
        manager.setCaja(box);
        manager.moveBox(level, 1, 0);
        assertNull(capaSup[1][1]);
    }

    @Test
    void moveBoxDireccionArriba() {
        box = new Box(2, 2);
        capaSup[2][2] = box;
        manager.setCaja(box);
        boolean resultado = manager.moveBox(level, -1, 0);
        assertTrue(resultado);
        assertEquals(1, box.getX());
        assertEquals(2, box.getY());
        assertSame(box, capaSup[1][2]);
        assertNull(capaSup[2][2]);
    }

    @Test
    void moveBoxMuroArriba() {
        box = new Box(2, 2);
        capaSup[2][2] = box;
        capaInf[1][2] = new Wall(1, 2);
        manager.setCaja(box);
        boolean resultado = manager.moveBox(level, -1, 0);
        assertFalse(resultado);
        assertEquals(2, box.getX());
    }
}
