package es.upm.pproject.sokoban.model.dto.classes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;

public class CurrentGameState implements ICurrentGameState, Serializable {
    private Level [] arrayLevels;
    private GameScore puntuacionTotal;
    private int index = 0;
    private Level current;

    public CurrentGameState(){
        arrayLevels = new Level[99];
        puntuacionTotal = new GameScore();
    }

    public Level[] getArray(){
        return arrayLevels;
    }
    public void añadirLevel(Level level){ //Añade un nivel al array
       arrayLevels[index] = level;
       index++;
    }
    @Override
    public Level getCurrent() {  //Obtiene el nivel actual
        return current;
    }
    @Override
    public void setCurrent(Level current) { //Establece el nivel actual y lo añade al array
    //  (Puede que sea innecesario que sea public)
        añadirLevel(current); //Lo añado por si acaso pero en verdad deberia de estar ya en el array
        LevelRecorder.reiniciarDeque(); //Reinicio el registro de movimientos guardados
        LevelRecorder.setInicio(current); //Se pone como el nuevo estado inicial
        this.current = current;
    }
    @Override
    public int getIndex() {  //Devuelve el indice del nivel actual en el array de niveles
        return index;
    }
    @Override
    public void setIndex(int index) { //Añade el indice del nivel deseado al cambiar de niveles
        this.index = index;
    }
    public GameScore getPuntuacionTotal(){
       return puntuacionTotal;
    }
    @Override
    public void reversionEstado(){ //Vuelve al movimiento anterior del nivel actual
        Level ant = LevelRecorder.undo(); //Obtiene el estado anterior al ultimo movimiento
        if(ant!=null){current = ant;
        puntuacionTotal.restar(1); //Resto un movimiento de la puntuacion total
        }
    }
    @Override
    public void restart(){ //Reinicia el nivel actual al estado inicial
        Level inicio = LevelRecorder.restart(); //Obtiene el primer save
        int cantidad = current.getPuntuacion().getPuntuacion();
        if(inicio!=null){current = inicio; //Establece el actual como el primer save
            puntuacionTotal.restar(cantidad);
        }
    }
    @Override
    public void moverPersonaje(Direccion dir){ //metodo para mover el personaje del nivel actual
        int puntini = current.getPuntuacion().getPuntuacion();
        current.moverPersonaje(dir);
        if(current.getPuntuacion().getPuntuacion()> puntini) puntuacionTotal.sumar(1);
        
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CurrentGameState that)) return false;
        return index == that.index && Objects.deepEquals(arrayLevels, that.arrayLevels) && Objects.equals(puntuacionTotal, that.puntuacionTotal) && Objects.equals(current, that.current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(arrayLevels), puntuacionTotal, index, current);
    }
}
