package es.upm.pproject.sokoban.model.dto.interfaces;
import es.upm.pproject.sokoban.model.dto.classes.Direccion;
import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface ICurrentGameState {
    public Level[] getArray();
    public void añadirLevel(Level level); //Añade un nivel al array
    Level getCurrent(); //Obtiene el nivel actual
    void setCurrent(Level current); //Establece el nivel actual y lo añade al array (Puede que sea innecesario que sea public)
    /*
    *Devuelve el indice del nivel actual en el array de niveles
    */
    int getIndex();
    void setIndex(int index); //Añade el indice del nivel deseado al cambiar de niveles
    void reversionEstado(); //Vuelve al movimiento anterior del nivel actual
    void restart(); //Reinicia el nivel actual al estado inicial
    void moverPersonaje(Direccion dir); //metodo para mover el personaje del nivel actual
}
