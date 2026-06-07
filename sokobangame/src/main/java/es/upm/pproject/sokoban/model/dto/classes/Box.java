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
    @Override
    public boolean equals(Object object){
        boolean iguales=false;
        Box acomparar;
        if (object == null || getClass() != object.getClass())
           return iguales;
        if(object instanceof Box){
            acomparar = (Box) object;
            if(super.equals(acomparar) && acomparar.color.equals(this.color))
                iguales = true;
        }
        return iguales;
    }
}
