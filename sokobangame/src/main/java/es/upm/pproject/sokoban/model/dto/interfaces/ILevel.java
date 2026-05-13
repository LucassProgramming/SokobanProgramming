package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Score;
import es.upm.pproject.sokoban.model.dto.classes.Square;

public interface ILevel {
    
    public Square[][] getCapaInf();
    public Square[][] getCapaSup();
    public void setCapaInf();
    public void setCapaSup();
    public String getNombre();
    public int getFilas();
    public int getColumnas();
    public Score getPuntuacion();
    public void setNombre();
    public void setFilas();
    public void setColumnas();
    public void setPuntuacion();
    public void incrementar();
    public void move();


}
