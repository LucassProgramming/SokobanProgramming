package es.upm.pproject.sokoban.model.dto.classes;

import java.util.Objects;

public class Direccion { //Clase provisional para la direccion que devolvera el teclado
     private int incX; //incremento en las filas
     private int incY; //incremento en las columnas

    public Direccion(int x,int y){ //Constructor
        incX = x;
        incY = y;
    }

    public int getX(){ //get de incX
        return incX;
    }
    public int getY(){ // get de incY
        return incY;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Direccion)) return false;
        Direccion direccion = (Direccion) o;
        return incX == direccion.incX && incY == direccion.incY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(incX, incY);
    }
}
