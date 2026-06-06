package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;
import java.util.Objects;

import es.upm.pproject.sokoban.model.dto.interfaces.IGameScore;

public class GameScore implements IGameScore, Serializable{

    private int total;

    public GameScore(){
        total = 0;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public void restar(int cantidad){
        this.total-=cantidad;
    }
    public void sumar(int cantidad){
        this.total+= cantidad;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GameScore gameScore)) return false;
        return total == gameScore.total;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(total);
    }
}
