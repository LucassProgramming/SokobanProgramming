package es.upm.pproject.sokoban.model.dto.classes;

import java.awt.Color;

public class Box extends Square{
    private Color color;
    private static int cantidad=0;
    public Box(int x, int y){
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
}
