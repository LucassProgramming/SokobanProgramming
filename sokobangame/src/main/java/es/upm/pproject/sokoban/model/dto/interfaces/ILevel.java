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
    public void setFilas(int filas);
    public void setColumnas(int columnas);
    public void setPuntuacion(Score puntuacion);
    public void incrementar();
    public void moverPersonaje();  //Metodo para mover al personaje invocado por CurrentGameState necesita llamar a CharacterManager


}
