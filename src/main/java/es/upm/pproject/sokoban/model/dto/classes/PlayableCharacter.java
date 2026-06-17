package es.upm.pproject.sokoban.model.dto.classes;

public class PlayableCharacter extends Square {

    public PlayableCharacter(int x, int y){
        super(x, y);
    }

    public PlayableCharacter(PlayableCharacter other) {
        super(other.getX(), other.getY());
    }

}
