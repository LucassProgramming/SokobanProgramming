package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;

import es.upm.pproject.sokoban.model.dto.interfaces.IGameScore;
import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class GameScore implements IGameScore, Serializable{

    private int total;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int totalScores(IScore[] arrayScores){
        for(IScore score : arrayScores){
            total=+score.getPuntuacion();
        }
        setTotal(total);
        return total;
    }
}
