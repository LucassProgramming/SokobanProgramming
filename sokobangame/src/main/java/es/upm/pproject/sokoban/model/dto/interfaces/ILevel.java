package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Score;
import es.upm.pproject.sokoban.model.dto.classes.Square;

public interface ILevel {
    
    public Square[][] getCapaInf();
    public Square[][] getCapaSup();
    public void setCapaInf(Square[][] capaInf);
    public void setCapaSup(Square[][] capaSup);
    public String getNombre();
    public int getFilas();
    public int getColumnas();
    public Score getPuntuacion();
    public void setNombre(String nombre);
    public void setFilas(int Filas);
    public void setColumnas(int Columnas);
    public void setPuntuacion(Score Puntuacion);
    public void incrementar();
    


}
