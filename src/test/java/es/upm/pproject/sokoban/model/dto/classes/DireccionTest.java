package es.upm.pproject.sokoban.model.dto.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DireccionTest {
    @Test void getXReturnsProvidedIncrement() {
        Direccion direccion = new Direccion(2, 5);
        assertEquals(2, direccion.getX()); }
    @Test void getYReturnsProvidedIncrement() {
        Direccion direccion = new Direccion(2, 5);
        assertEquals(5, direccion.getY()); }
    @Test void zeroIncrementsAreAllowed() { Direccion direccion = new Direccion(0, 0);
        assertEquals(0, direccion.getX());
        assertEquals(0, direccion.getY()); }
    @Test void negativeIncrementsAreAllowed() {
        Direccion direccion = new Direccion(-1, -3);
        assertEquals(-1, direccion.getX());
        assertEquals(-3, direccion.getY());
    }
    @Test
    void largeValuesArePreserved() {
        Direccion direccion = new Direccion(1000000, 2000000);
        assertEquals(1000000, direccion.getX());
        assertEquals(2000000, direccion.getY());
    }

    @Test
    void equalsConMismosValores() {
        assertEquals(new Direccion(1, 0), new Direccion(1, 0));
    }

    @Test
    void notEqualsConDistintaX() {
        assertNotEquals(new Direccion(1, 0), new Direccion(0, 0));
    }

    @Test
    void notEqualsConDistintaY() {
        assertNotEquals(new Direccion(0, 1), new Direccion(0, 0));
    }
     @Test
    void notEqualsConDistintaClase() {
        boolean cond = new Direccion(0, 0).equals("una cadena");
        assertFalse(cond);
    }

    @Test
    void hashCodeConsistenteConEquals() {
        assertEquals(new Direccion(-1, 0).hashCode(), new Direccion(-1, 0).hashCode());
    }

    @Test
    void notEqualsConObjetoNulo() {
        Direccion d = new Direccion(1, 1);
        assertNotEquals( null,d);
    }
}
