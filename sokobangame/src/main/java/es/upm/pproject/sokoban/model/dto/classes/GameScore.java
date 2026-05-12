package es.upm.pproject.sokoban.model.dto.classes;
import es.upm.pproject.sokoban.model.dto.interfaces.*;

public class GameScore implements IGameScore{
    private int total;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
