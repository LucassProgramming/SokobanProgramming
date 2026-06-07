package es.upm.pproject.sokoban.model.dto.classes;


public class CharacterManager { //Clase para controlar el movimiento del personaje esta asociada con Level

    public static void moverPersonaje(Level level,PlayableCharacter personaje,
        Direccion direccion ){ //moverPersonaje llamado por el nivel con la direccion del teclado
        BoxManager manejador = new BoxManager(level);
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
            //Si es un muro termina sin moverse
        } else if(casillaSup instanceof Box caja){
          LevelRecorder.save(level); //Guardo el estado anterior cuando ya se que se va ha mover el personaje
           caja.setX(coorX + incX); //Les pongo otras coordenadas como contramedida de restart o 
           // undo para que no se queden las que tenian antes de ello
           caja.setY( coorY + incY);
           manejador.setCaja(caja); //Si es caja se añade la caja al manejador
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
        } else { LevelRecorder.save(level); //Guardo el estado anterior cuando ya se que se va ha mover el personaje
            personaje.setX(coorX + incX); personaje.setY(coorY + incY);
             level.incrementar(); 
            capaSup[coorX][coorY] = null; //lo mismo
            capaSup[personaje.getX()][personaje.getY()] = personaje;}
    }

}
}
