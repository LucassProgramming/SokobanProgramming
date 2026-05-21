package es.upm.pproject.sokoban.model.dto.classes;

public class PlayableCharacter extends Square implements Cloneable { //Clase para el personaje jugable

     public PlayableCharacter(int x, int y){
        super(x,y); //Solo tiene las variables de la superclase Square
     }
    @Override
    public PlayableCharacter clone() {
        try {
            return (PlayableCharacter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
