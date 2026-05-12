package es.upm.pproject.sokoban.model.dto.classes;

import java.awt.Color;

public class Box extends Square{
    private Color color;

    public Box(Color color, int x, int y){
        super(x,y);
        this.color=Color.PINK;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
