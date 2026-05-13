package es.upm.pproject.sokoban.model.dto.classes;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class Level implements ILevel {

    private Square capaInf[][];
    private Square capaSup[][];
    private Score puntuacion;
    private int filas;
    private String nombre;
    private int columnas;
    private CharacterManager manejadorPersonaje;



    public Square[][] getCapaInf(){
        return capaInf;
    }
    public Square[][] getCapaSup(){
        return capaSup;
    }
    public void setCapaInf(){

    }
    public void setCapaSup(){

    }
    public String getNombre(){
        return nombre;
    }
    public int getFilas(){
        return filas;
    }
    public int getColumnas(){
        return columnas;
    }
    public Score getPuntuacion(){
        return puntuacion;
    }
    public void setNombre(){

    }
    public void setFilas(){

    }
    public void setColumnas(){
        
    }
    public void setPuntuacion(){
        
    }
    public void incrementar(){
        
    }
    public void move(){}
}
