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

    @Test
    void moveBoxBloqueadoPorOtraCaja() {
        Box otraCaja = new Box(1, 2);
        capaSup[1][2] = otraCaja;
        manager.setCaja(box);
        boolean resultado = manager.moveBox(level, 0, 1);
        assertFalse(resultado);
        assertSame(box, capaSup[1][1]);
    }

    @Test
    void equalsConMismoLevelYCaja() {
        BoxManager m1 = new BoxManager(level);
        BoxManager m2 = new BoxManager(level);
        Box b = new Box(2, 2);
        m1.setCaja(b);
        m2.setCaja(b);
        assertEquals(m1, m2);
    }

    @Test
    void notEqualsConDistintaCaja() {
        BoxManager m1 = new BoxManager(level);
        BoxManager m2 = new BoxManager(level);
        m1.setCaja(new Box(1, 1));
        m2.setCaja(new Box(2, 2));
        assertNotEquals(m1, m2);
    }


    @Test
    void hashCodeConsistenteConEquals() {
        BoxManager m1 = new BoxManager(level);
        BoxManager m2 = new BoxManager(level);
        Box b = new Box(3, 3);
        m1.setCaja(b);
        m2.setCaja(b);
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void equalsConObjetoNuloDevuelveFalso() {
        BoxManager m1 = new BoxManager(level);
        assertFalse(m1.equals(null));
    }

    @Test
    void equalsConDistintaClaseDevuelveFalso() {
        BoxManager m1 = new BoxManager(level);
        // comparar con un objeto de otra clase debe devolver false
        assertFalse(m1.equals("una cadena"));
    }

    @Test
    void equalsConDistintoLevelDevuelveFalso() {
        // crear otro Level con dimensiones distintas para que no sean iguales
        Square[][] inf2 = new Square[3][3];
        Square[][] sup2 = new Square[3][3];
        Level otroLevel = new Level(inf2, sup2);

        BoxManager m1 = new BoxManager(level);
        BoxManager m2 = new BoxManager(otroLevel);
        Box b = new Box(1, 1);
        m1.setCaja(b);
        m2.setCaja(b);

        assertNotEquals(m1, m2);
    }

    @Test
    void equalsConLevelsDeepEqualDevuelveVerdadero() {
        // niveles distintos (instancias distintas de arrays) pero con mismo contenido (todo null)
        Square[][] infA = new Square[4][4];
        Square[][] supA = new Square[4][4];
        Square[][] infB = new Square[4][4];
        Square[][] supB = new Square[4][4];

        Level l1 = new Level(infA, supA);
        Level l2 = new Level(infB, supB);

        BoxManager m1 = new BoxManager(l1);
        BoxManager m2 = new BoxManager(l2);
        Box b = new Box(2, 2);
        m1.setCaja(b);
        m2.setCaja(b);

        assertEquals(m1, m2);
    }
}
