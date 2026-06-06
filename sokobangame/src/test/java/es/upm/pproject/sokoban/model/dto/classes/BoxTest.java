package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;

public class BoxTest {

    @Test
    void boxGuardaCoordenadaX() {
        Box box = new Box(Color.BLUE, 3, 5);
        assertEquals(3, box.getX());
    }

    @Test
    void boxGuardaCoordenadaY() {
        Box box = new Box(Color.BLUE, 3, 5);
        assertEquals(5, box.getY());
    }

    @Test
    void boxColorSiemprePINK() {
        Box box = new Box(Color.BLUE, 0, 0);
        assertEquals(Color.PINK, box.getColor());
    }

    @Test
    void boxSetColorCambiaColor() {
        Box box = new Box(Color.PINK, 1, 1);
        box.setColor(Color.RED);
        assertEquals(Color.RED, box.getColor());
    }

    @Test
    void boxSetColorAzul() {
        Box box = new Box(Color.PINK, 2, 3);
        box.setColor(Color.BLUE);
        assertEquals(Color.BLUE, box.getColor());
    }

    @Test
    void cuantasBoxesIncrementaAlCrearBox() {
        int antes = Box.cuantasBoxes();
        new Box(Color.PINK, 0, 0);
        assertEquals(antes + 1, Box.cuantasBoxes());
    }

    @Test
    void cuantasBoxesIncrementaVarias() {
        int antes = Box.cuantasBoxes();
        new Box(Color.PINK, 0, 0);
        new Box(Color.PINK, 1, 1);
        new Box(Color.PINK, 2, 2);
        assertEquals(antes + 3, Box.cuantasBoxes());
    }

    @Test
    void boxCoordenadaXCero() {
        Box box = new Box(Color.PINK, 0, 4);
        assertEquals(0, box.getX());
    }

    @Test
    void boxCoordenadaYCero() {
        Box box = new Box(Color.PINK, 4, 0);
        assertEquals(0, box.getY());
    }
}
