package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
