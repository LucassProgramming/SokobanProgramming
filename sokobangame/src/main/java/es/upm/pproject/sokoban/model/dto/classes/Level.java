package es.upm.pproject.sokoban.model.dto.classes;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class Level implements ILevel {

    private Square capaInf[][];
    private Square capaSup[][];
    private Score puntuacion;
    private int filas;
    private String nombre;
    private int columnas;

public Level(String nombre,int filas,int columnas,Square[][] capaInf,Square[][] capaSup,Score puntuacion){

    this.nombre = nombre;
    this.filas = filas;
    this.columnas = columnas;
    this.capaInf = capaInf;
    this.capaSup = capaSup;
    this.puntuacion = puntuacion;


}

@Override
public Square[][] getCapaInf() {
    return this.capaInf;
}

@Override
public Square[][] getCapaSup() {
    return this.capaSup;
}

@Override
public void setCapaInf(Square[][] capaInf) {
     this.capaInf = capaInf;
}

@Override
public void setCapaSup(Square[][] capaSup) {
    this.capaSup = capaSup;
}

@Override
public String getNombre() {
   return this.nombre;
}

@Override
public int getFilas() {
    return this.filas;
}

@Override
public int getColumnas() {
    return this.columnas;
}

@Override
public Score getPuntuacion() {
    return this.puntuacion;
}

@Override
public void setNombre(String nombre) {
    this.nombre = nombre;
}

@Override
public void setFilas(int Filas) {
    this.filas = Filas;
}

@Override
public void setColumnas(int Columnas) {
    this.columnas = Columnas;
}

@Override
public void setPuntuacion(Score Puntuacion) {
    this.puntuacion = Puntuacion;
}

@Override
public void incrementar() {
    
}


    
    



    


}
