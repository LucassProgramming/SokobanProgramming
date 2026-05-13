package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;

import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class Score implements IScore, Serializable{

    private int puntuacion;

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion=puntuacion;
    }
    
    public void incrementar(){
        this.puntuacion++;
    }
}
