package es.upm.pproject.sokoban.model.dto.classes;

public class Square {

    private final int x;
    private final int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
