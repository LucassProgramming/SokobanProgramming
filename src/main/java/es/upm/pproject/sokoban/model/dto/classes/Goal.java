package es.upm.pproject.sokoban.model.dto.classes;


//Clase Dedicada a la Meta sobre la cual se ubicara
// una Caja como requisito para completar un nivel
public class Goal extends Square {
    private static int cantidad = 0; //Variable que cuenta la cantidad de Metas libres disponibles

    public Goal(int x,int y){ //Constructor de Goal
        super(x, y);
        cantidad++; //Aumento de cantidad de Metas
    }

    public static int getCantidad(){ //método get de Cantidad se usara para saber si un nivel 
    // esta completo o si hay la misma cantidad de Goal que de Box al crear el nivel
        return cantidad;
    }
    public static void decrementar(){ //Método para decrementar la cantidad cada vez que una caja se encuentra por encima
        cantidad--;
    }

}

