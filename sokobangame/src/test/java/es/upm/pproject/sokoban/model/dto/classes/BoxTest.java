package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;

class BoxTest {

    @Test
    void boxGuardaCoordenadaX() {
        Box box = new Box(3, 5);
        assertEquals(3, box.getX());
    }

    @Test
    void boxGuardaCoordenadaY() {
        Box box = new Box(3, 5);
        assertEquals(5, box.getY());
    }

    @Test
    void boxColorSiemprePINK() {
        Box box = new Box(0, 0);
        assertEquals(Color.PINK,box.getColor());
    }

    @Test
    void boxSetColorCambiaColor() {
        Box box = new Box( 1, 1);
        box.setColor(Color.RED);
        assertEquals(Color.RED, box.getColor());
    }

    @Test
    void boxSetColorAzul() {
        Box box = new Box(2, 3);
        box.setColor(Color.BLUE);
        assertEquals(Color.BLUE, box.getColor());
    }

    @Test
    void cuantasBoxesIncrementaAlCrearBox() {
        int antes = Box.cuantasBoxes();
        new Box(0, 0);
        assertEquals(antes + 1, Box.cuantasBoxes());
    }

    @Test
    void cuantasBoxesIncrementaVarias() {
        int antes = Box.cuantasBoxes();
        new Box(0, 0);
        new Box(1, 1);
        new Box(2, 2);
        assertEquals(antes + 3, Box.cuantasBoxes());
    }

    @Test
    void boxCoordenadaXCero() {
        Box box = new Box(0, 4);
        assertEquals(0, box.getX());
    }

    @Test
    void boxCoordenadaYCero() {
        Box box = new Box(4, 0);
        assertEquals(0, box.getY());
    }

    @Test
    void equalsConMismasCoordenadas() {
        assertEquals(new Box(2, 3), new Box(2, 3));
    }

    @Test
    void notEqualsConDistintasCoordenadas() {
        assertNotEquals(new Box(1, 1), new Box(2, 2));
    }

    @Test
    void notEqualsConNulo() {
        assertNotEquals(null, new Box(1, 1));
    }

    @Test
    void notEqualsConDistintoColor() {
        Box a = new Box(2, 2);
        Box b = new Box(2, 2);
        b.setColor(java.awt.Color.BLUE);
        assertNotEquals(a, b);
    }

    @Test
    void notEqualsConObjetoDeOtraClase() {
        assertNotEquals(new Box(1, 1), "una cadena");
    }

    @Test
    void notEqualsWhenComparedWithSquare() {
        // A Box and a Square with same coordinates must not be equal (different classes)
        Box box = new Box(2, 2);
        Square square = new Square(2, 2);
        assertNotEquals(box, square);
    }

    @Test
    void hashCodeConsistenteConEquals() {
        Box a = new Box(5, 5);
        Box b = new Box(5, 5);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
