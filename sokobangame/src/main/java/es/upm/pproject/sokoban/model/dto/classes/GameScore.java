package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;

import es.upm.pproject.sokoban.model.dto.interfaces.IGameScore;

public class GameScore implements IGameScore, Serializable{

    private int total;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int totalScores(Level [] niveles){ //En verdad esto lo tienes que sacar de los array 
    // de niveles de CurrentGameState
        for(Level nivel:niveles){
            Score score = nivel.getPuntuacion();
            
            total= score != null ? total + score.getPuntuacion() : total + 0 ;
        }
        setTotal(total);
        return total;
    }
}
