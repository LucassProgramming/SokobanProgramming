package es.upm.pproject.sokoban.model.dto.classes;

import java.io.Serializable;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;

public class CurrentGameState implements ICurrentGameState, Serializable {
    private Level [] arrayLevels;
    private int index = 0;
    private Level current;

    public CurrentGameState(){
        arrayLevels = new Level[99];
    }

    public Level[] getArray(){
        return arrayLevels;
    }
    public void añadirLevel(Level level){
       arrayLevels[index] = level;
       index++;
    }
    @Override
    public Level getCurrent() {
        return current;
    }
    @Override
    public void setCurrent(Level current) {
        this.current = current;
    }
    @Override
    public int getIndex() {
        return index;
    }
    @Override
    public void setIndex(int index) {
        this.index = index;
    }
    @Override
    public void reversionEstado(){
        Level anterior = current.estadoAnterior();
        if(anterior!=null) current = anterior;
    }
}
