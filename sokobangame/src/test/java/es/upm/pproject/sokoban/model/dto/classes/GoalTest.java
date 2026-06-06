package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GoalTest {

    @BeforeEach
    void resetGoalCounter() {
        while (Goal.getCantidad() > 0) {
            Goal.decrementar();
        }
    }

    @Test
    void goalStoresCoordinateX() {
        Goal goal = new Goal(3, 5);
        assertEquals(3, goal.getX());
    }

    @Test
    void goalStoresCoordinateY() {
        Goal goal = new Goal(3, 5);
        assertEquals(5, goal.getY());
    }

    @Test
    void goalIncrementsCantidadUponCreation() {
        int initialCount = Goal.getCantidad();
        new Goal(0, 0);
        assertEquals(initialCount + 1, Goal.getCantidad());
    }

    @Test
    void multipleGoalsIncrementsCantidad() {
        int initialCount = Goal.getCantidad();
        new Goal(0, 0);
        new Goal(1, 1);
        new Goal(2, 2);
        assertEquals(initialCount + 3, Goal.getCantidad());
    }

    @Test
    void decrementarLowersCantidad() {
        new Goal(0, 0);
        int afterCreation = Goal.getCantidad();
        Goal.decrementar();
        assertEquals(afterCreation - 1, Goal.getCantidad());
    }

    @Test
    void decrementarCanBeCalledMultipleTimes() {
        new Goal(0, 0);
        new Goal(1, 1);
        int afterCreation = Goal.getCantidad();
        Goal.decrementar();
        Goal.decrementar();
        assertEquals(afterCreation - 2, Goal.getCantidad());
    }

    @Test
    void goalWithZeroCoordinates() {
        Goal goal = new Goal(0, 0);
        assertEquals(0, goal.getX());
        assertEquals(0, goal.getY());
    }

    @Test
    void goalWithLargeCoordinates() {
        Goal goal = new Goal(999, 999);
        assertEquals(999, goal.getX());
        assertEquals(999, goal.getY());
    }

    @Test
    void cantidadStartsAtZero() {
        assertEquals(0, Goal.getCantidad());
    }

    @Test
    void cantidadCanReachZeroAfterDecrements() {
        new Goal(0, 0);
        Goal.decrementar();
        assertEquals(0, Goal.getCantidad());
    }
}
