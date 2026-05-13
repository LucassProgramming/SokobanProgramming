package es.upm.pproject.sokoban.model.dto.interfaces;
import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface ICurrentGameState {
    public Level[] getArray();
    public void añadirLevel(Level level);
    Level getCurrent();
    void setCurrent(Level current);
    /*
    *Devuelve el indice del nivel actual en el array de niveles
    */
    int getIndex();
    void setIndex(int index);
}
