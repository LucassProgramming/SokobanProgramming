package es.upm.pproject.sokoban.model.dto.classes;

public class CurrentGameState {
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
}
