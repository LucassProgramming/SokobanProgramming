package es.upm.pproject.sokoban.model.dto.classes;

public class CharacterManager {
   
    private Level level;
    private PlayableCharacter personaje;

    public CharacterManager(Level level, PlayableCharacter character){
        this.level = level;
        this.personaje = character;
    }
    

    public void moverPersonaje(Direccion direccion){
        Square [][] capaSup = level.getCapaSup();
        Square [][] capaInf = level.getCapaInf();

        int coorX = personaje.getX();
        int coorY = personaje.getY();

        int incX = direccion.getX();
        int incY = direccion.getY();
        
        Square casillaInf = capaInf[coorX + incX][coorY + incY];
        Square casillaSup = capaSup[coorX + incX][coorY + incY];
        if(casillaInf instanceof Wall){
            return;
        } else if(casillaSup instanceof Box){
           boolean movido = BoxManager.moveBox(level,incX,incY);
           if(movido){ personaje.setX(coorX + incX); personaje.setY(coorY + incY); level.incrementar();}
        } else {personaje.setX(coorX + incX); personaje.setY(coorY + incY); level.incrementar();}
    }
}
