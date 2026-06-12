package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PlayableCharacterTest {

    @Test
    void characterCoordenadaXCorrecta(){
        PlayableCharacter personaje = new PlayableCharacter(2, 5);
        assertEquals(2, personaje.getX());
    }

    @Test
    void characterCoordenadaYCorrecta(){
        PlayableCharacter personaje = new PlayableCharacter(2, 5);
        assertEquals(5, personaje.getY());
    }

    @Test
    void setXActualizaCoordenada() {
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        pc.setX(4);
        assertEquals(4, pc.getX());
    }

    @Test
    void setYActualizaCoordenada() {
        PlayableCharacter pc = new PlayableCharacter(0, 0);
        pc.setY(6);
        assertEquals(6, pc.getY());
    }

    @Test
    void cloneProduceObjetoDistinto() {
        PlayableCharacter original = new PlayableCharacter(1, 2);
        PlayableCharacter clon = original.clone();
        assertNotSame(original, clon);
        assertEquals(original.getX(), clon.getX());
        assertEquals(original.getY(), clon.getY());
    }

    @Test
    void cloneEsIndependiente() {
        PlayableCharacter original = new PlayableCharacter(1, 2);
        PlayableCharacter clon = original.clone();
        clon.setX(99);
        assertEquals(1, original.getX());
    }
}
