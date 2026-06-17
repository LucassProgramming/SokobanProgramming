package es.upm.pproject.sokoban.model.dto.classes;
import es.upm.pproject.sokoban.model.dto.interfaces.*;

import java.util.Objects;

public class BoxManager implements IBoxManager{
    private ILevel level;
    private Box caja;

    public BoxManager(ILevel level){
        this.level = level;
    }

    public boolean moveBox(ILevel level,int x, int y){
        Square [][] capaSup = level.getCapaSup();
        Square [][] capaInf = level.getCapaInf();

        int coorX = caja.getX(); 
        int coorY = caja.getY();

        Square casillaInf = capaInf[coorX + x][coorY + y];
        Square casillaSup = capaSup[coorX + x][coorY + y];
        if(casillaInf instanceof Wall){
            return false;
        }
        else if(casillaSup instanceof Box){
            return false;
        }
        else {
            caja.setX(coorX + x);
            caja.setY(coorY + y);

            capaSup[coorX][coorY]=null;
            capaSup[coorX+x][coorY+y]=caja;

            return true;
        }
    }

    public void setCaja(Box caja){
        this.caja=caja;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoxManager that)) return false;
        return Objects.equals(level, that.level) && Objects.equals(caja, that.caja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, caja);
    }
}
