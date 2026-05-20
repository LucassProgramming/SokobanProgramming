package es.upm.pproject.sokoban.model.dto.classes;
import es.upm.pproject.sokoban.model.dto.interfaces.*;

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
        if(casillaInf instanceof Wall){
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
}
