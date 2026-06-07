package es.upm.pproject.sokoban.model.dto.classes;

import es.upm.pproject.sokoban.model.exceptions.CouldntCloneException;

public class PlayableCharacter extends Square implements Cloneable { //Clase para el personaje jugable

     public PlayableCharacter(int x, int y){
        super(x,y); //Solo tiene las variables de la superclase Square
     }
    @Override
    public PlayableCharacter clone() { //Metodo para clonar el personaje para el save
        try {
            return (PlayableCharacter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CouldntCloneException(e); //Exception por si acaso
        }
    }

}
