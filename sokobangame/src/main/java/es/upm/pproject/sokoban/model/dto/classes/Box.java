package es.upm.pproject.sokoban.model.dto.classes;

import java.awt.Color;
import java.util.Objects;

public class Box extends Square{
    private Color color;
    private static int cantidad=0;
    public Box(Color color, int x, int y){
        super(x,y);
        Box.cantidad++;
        this.color=Color.PINK;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public static int cuantasBoxes(){
        return cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Box box)) return false;
        return Objects.equals(color, box.color);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }
}
