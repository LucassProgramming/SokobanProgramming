package es.upm.pproject.sokoban.model.dto.classes;
import es.upm.pproject.sokoban.model.dto.interfaces.*;

public class Score implements IScore{

    private int puntuacion;

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion=puntuacion;
    }
    
}
