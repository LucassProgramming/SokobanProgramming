package es.upm.pproject.sokoban.model.dto.classes;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class Level implements ILevel {

    private Square capaInf[][];
    private Square capaSup[][];
    private Score puntuacion;
    private int filas;
    private String nombre;
    private int Columnas;
}
