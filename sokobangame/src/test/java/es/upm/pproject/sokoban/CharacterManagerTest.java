package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.classes.*;
import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class CharacterManagerTest {
    
    @Test
    void CharacterManagerNoSeMueveMuro(){
        PlayableCharacter pers = new PlayableCharacter(0, 0);
        Square [][] capaInf = new Square [2][2];
        Square [][] capaSup = new Square [2][2];
        capaInf[0][1] = new Wall(0, 1);
        capaSup[0][0] = pers;
        ILevel level = new Level(capaInf,capaSup);
        CharacterManager manager = new CharacterManager(level, pers);
        manager.moverPersonaje(new Direccion(0, 1));
        assertEquals(capaSup[0][0], pers);
    }
}
