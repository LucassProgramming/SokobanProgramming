package es.upm.pproject.sokoban.model.dto.classes;
import java.io.Serializable;
import java.util.Objects;

import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class Score implements IScore, Serializable, Cloneable{

    private int puntuacion;

    public Score(){
        puntuacion = 0;
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
    public Score clone() {
        try {
            return (Score) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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
