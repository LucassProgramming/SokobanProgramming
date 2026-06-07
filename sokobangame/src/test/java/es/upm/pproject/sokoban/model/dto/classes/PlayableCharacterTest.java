package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class PlayableCharacterTest {
   @Test
    void CharacterCoordenadaXCorrecta(){
        PlayableCharacter personaje = new PlayableCharacter(2, 5);
        assertEquals(2, personaje.getX());
    }
    @Test
    void CharacterCoordenadaYCorrecta(){
        PlayableCharacter personaje = new PlayableCharacter(2, 5);
        assertEquals(5, personaje.getY());
    }
}
