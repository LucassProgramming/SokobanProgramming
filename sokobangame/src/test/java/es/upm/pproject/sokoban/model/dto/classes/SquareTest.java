package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;


class SquareTest {
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

    @Test
    void setXActualizaCoordenadaX() {
        Square square = new Square(1, 1);
        square.setX(7);
        assertEquals(7, square.getX());
    }

    @Test
    void setYActualizaCoordenadaY() {
        Square square = new Square(1, 1);
        square.setY(9);
        assertEquals(9, square.getY());
    }

    @Test
    void equalsConMismasCoordenadas() {
        assertEquals(new Square(3, 4), new Square(3, 4));
    }

    @Test
    void notEqualsConDistintaX() {
        assertNotEquals(new Square(1, 4), new Square(2, 4));
    }

    @Test
    void notEqualsConDistintaY() {
        assertNotEquals(new Square(3, 4), new Square(3, 5));
    }

    @Test
    void hashCodeConsistenteConEquals() {
        assertEquals(new Square(3, 4).hashCode(), new Square(3, 4).hashCode());
    }

    @Test
    void notEqualsConObjetoNulo() {
        assertNotEquals(new Square(1, 1), null);
    }

    @Test
    void notEqualsConObjetoDeOtraClase() {
        assertNotEquals(new Square(1, 1), "otra clase");
    }
}
