package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;
import java.util.Objects;

import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class Score implements IScore, Serializable {

    private int puntuacion;

    public Score(){
        puntuacion = 0;
    }

    public Score(Score other) {
        this.puntuacion = other.puntuacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion=puntuacion;
    }
    
    public void incrementar(){
        this.puntuacion++;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Score score)) return false;
        return puntuacion == score.puntuacion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(puntuacion);
    }
}
