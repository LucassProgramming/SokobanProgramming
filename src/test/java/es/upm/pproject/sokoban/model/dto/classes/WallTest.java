package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class WallTest {
    @Test
    void WallCoordenadaXCorrecta(){
        Wall wall = new Wall(2, 5);
        assertEquals(2, wall.getX());
    }
    @Test
    void WallCoordenadaYCorrecta(){
        Wall wall = new Wall(2, 5);
        assertEquals(5, wall.getY());
    }

    @Test
    void wallEqualsConMismasCoordenadas() {
        assertEquals(new Wall(1, 2), new Wall(1, 2));
    }

    @Test
    void wallNotEqualsConDistintasCoordenadas() {
        assertNotEquals(new Wall(1, 2), new Wall(3, 4));
    }

    @Test
    void wallHashCodeConsistente() {
        assertEquals(new Wall(1, 2).hashCode(), new Wall(1, 2).hashCode());
    }

    @Test
    void wallEsInstanciaDeSquare() {
        assertTrue(new Wall(0, 0) instanceof Square);
    }
}
