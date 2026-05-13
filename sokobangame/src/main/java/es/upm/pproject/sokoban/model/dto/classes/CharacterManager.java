package es.upm.pproject.sokoban.model.dto.classes;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class CharacterManager { //Clase para controlar el movimiento del personaje esta asociada con Level
   
    private ILevel level; //Atributo level que representa 
    private PlayableCharacter personaje; //personaje se le añade en LevelFileReader
    private BoxManager manejador; //BoxManager autogenerado

    public CharacterManager(ILevel level, PlayableCharacter character){ //Contructor de CharacterManager
        this.level = level;
        this.personaje = character;
        manejador = new BoxManager(level);
    }
    public void moverPersonaje(Direccion direccion ){ //moverPersonaje llamado por el nivel con la direccion del teclado
        Square [][] capaSup = level.getCapaSup(); //obtener la capa superior para cajas
        Square [][] capaInf = level.getCapaInf(); //obtener capa inferior para Muros

        int coorX = personaje.getX(); 
        int coorY = personaje.getY();
        //Obtener posición actual del personaje
        int incX = direccion.getX();
        int incY = direccion.getY();
        //Obtener movimiento solicitado
    if(level.getFilas() > (coorX + incX) && level.getColumnas() > (coorY + incY)){ //Precondicion para no salirse del mapa
        Square casillaInf = capaInf[coorX + incX][coorY + incY]; //Obtener posicion futura en la capa inferior
        Square casillaSup = capaSup[coorX + incX][coorY + incY]; //Obtener posicion futura en la capa superior
        if(casillaInf instanceof Wall){ 
            return; //Si es un muro termina sin moverse
        } else if(casillaSup instanceof Box){
           manejador.setCaja((Box) casillaSup); //Si es caja se añade la caja al manejador
           boolean movido = manejador.moveBox(level,incX,incY); //Se llama al manejador para moverla
           if(movido){
            personaje.setX(coorX + incX); 
            personaje.setY(coorY + incY);
            level.incrementar();
            //Se mueve el personaje y se aumenta la puntuacion del nivel
            capaSup[coorX][coorY] = null;
            capaSup[personaje.getX()][personaje.getY()] = personaje;
            //Se cambia la posicion del personaje en la capa superior
        }
        } else {personaje.setX(coorX + incX); personaje.setY(coorY + incY);
             level.incrementar(); 
            capaSup[coorX][coorY] = null; //lo mismo
            capaSup[personaje.getX()][personaje.getY()] = personaje;}
    }
}
}
