package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.classes.Square;

public class SquareTest {
    @Test
    void SquareCoordenadaXCorrecta(){
        Square square = new Square(2, 5);
        assertEquals(2, square.getX());
    }

    @Test
    void SquareCoordenadaYCorrecta(){
        Square square = new Square(2, 5);
        assertEquals(5, square.getY());
    }
    @Test
    void SquareCoordenadaXIncorrecta(){
        Square square = new Square(2, 5);
        assertNotEquals(3, square.getX());
    }
    @Test
    void SquareCoordenadaYIncorrecta(){
        Square square = new Square(2, 5);
        assertNotEquals(6, square.getY());
    }
    @Test
    void deberiaGuardarCoordenadasCero(){
        Square square = new Square(0, 0);
        assertEquals(0, square.getX());
        assertEquals(0, square.getY());
    }
    @Test 
    void deberiaGuardarCoordenadasNegativas(){
        Square square = new Square(-1, -1);
        assertEquals(-1, square.getX());
        assertEquals(-1, square.getY());
    }
}
