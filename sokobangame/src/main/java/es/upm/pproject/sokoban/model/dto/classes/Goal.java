package es.upm.pproject.sokoban.model.dto.classes;

public class Goal extends Square {
    private static int cantidad = 0;

    public Goal(int x,int y){
        super(x, y);
        cantidad++;
    }

    public static int getCantidad(){
        return cantidad;
    }
}

