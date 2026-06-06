package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterManagerTest {

    private PlayableCharacter pers;
    private Level level;
    
    @BeforeEach
    void Set(){
        level = new Level();
    }

    @Test
    void CharacterManagerNoSeMueveMuro(){ //Test para comprobar que el personaje no se mueve cuando hay muro delante
        level.getCapaInf()[0][1] = new Wall(0, 1);
        pers = new PlayableCharacter(0, 0);
        level.getCapaSup()[0][0] = pers;
        CharacterManager.moverPersonaje(level,pers,new Direccion(0, 1));
        assertEquals(level.getCapaSup()[0][0], pers);
    }
  /*  @Test
    void CharacterManagerSeMueveGoal(){ //Comprobar que el personaje se mueve si hay goal delante
        level.getCapaInf()[0][2] = new Goal(0, 2);
        pers = new PlayableCharacter(0, 1);
        level.getCapaSup()[0][1] = pers;
        CharacterManager.moverPersonaje(level, pers, new Direccion(0, 1));
        assertEquals(level.getCapaSup()[0][2], pers);
    } */
  /*   @Test
    void CharacterManagerDesplazaCaja(){ //Test para comprobar que el personaje ocupa el puesto de la caja
        level.getCapaSup()[1][0] = new Box(Color.PINK, 1, 0);
        pers = new PlayableCharacter(0, 0);
        level.getCapaSup()[0][0] = pers;
        CharacterManager.moverPersonaje(level, pers, new Direccion(1, 0));
        assertEquals(level.getCapaSup()[1][0], pers);
    } */
}
