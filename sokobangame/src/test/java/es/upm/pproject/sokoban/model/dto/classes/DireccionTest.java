package es.upm.pproject.sokoban.model.dto.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Direccion direccion = new Direccion(1000000,
                2000000);
        assertEquals(1000000, direccion.getX());
        assertEquals(2000000, direccion.getY());
    }
}
