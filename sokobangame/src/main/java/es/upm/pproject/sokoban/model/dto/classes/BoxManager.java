package es.upm.pproject.sokoban.model.dto.classes;
import es.upm.pproject.sokoban.model.dto.interfaces.*;

public class BoxManager implements IBoxManager{
    private Direccion direccion;
    private Level level;
    private Box caja;

    public BoxManager(Level level){
        this.level = level;
    }
    /*
    -Comprobar la direccion a la que se va mover desde la clase Direccion
    -Comprobar si es muro (+)
        -En caso de que no, mover incrementado/decrementando x o y, guardarlo en el array o base de datos de movimientos 
        -En caso de que si sea muro, no mover y tampoco guardarlo
    */
    public boolean moveBox(Level level,int x, int y){
        Square [][] capaSup = level.getCapaSup();
        Square [][] capaInf = level.getCapaInf();

        int coorX = caja.getX();
        int coorY = caja.getY();

        int incX = direccion.getX();
        int incY = direccion.getY();

        Square casillaInf = capaInf[coorX + incX][coorY + incY];
        Square casillaSup = capaSup[coorX + incX][coorY + incY];
        if(casillaInf instanceof Wall){
            return false;
        }
        else {
            caja.setX(coorX + incX);
            caja.setY(coorY + incY);
            level.incrementar();

            capaSup[coorX][coorY]=null;
            capaSup[coorX+incX][coorY+incY]=caja;

            return true;
        }
    }

    public void setCaja(Box caja){
        this.caja=caja;
    }
}
