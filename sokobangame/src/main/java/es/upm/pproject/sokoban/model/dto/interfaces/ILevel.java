package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Score;
import es.upm.pproject.sokoban.model.dto.classes.Square;

public interface ILevel {
    
    public Square[][] getCapaInf();
    public Square[][] getCapaSup();
    public Square[][] setCapaInf();
    public Square[][] setCapaSup();
    public String getNombre();
    public int getFilas();
    public int getColumnas();
    public Score getPuntuacion();
    public String setNombre();
    public int setFilas();
    public int setColumnas();
    public Score setPuntuacion();
    public void incrementar();
    


}
