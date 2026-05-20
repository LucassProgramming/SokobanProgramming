package es.upm.pproject.sokoban.model.dto.classes;

import java.util.ArrayList;
import java.util.Stack;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevelRecorder;

public class LevelRecorder implements ILevelRecorder {
   private Stack<Level> estadoNivel = new Stack<>();
   private Level inicio;
   public LevelRecorder(Level inicio){
      this.inicio = inicio;
   }


   @Override
   public void save(Level elNivel) {
    //guardo el estado del nivel a cada movimiento
    estadoNivel.push(elNivel);
   }

   @Override
   public Level undo() {
    // Devuelvo el estado anterior del nivel 
    return estadoNivel.pop();
   }
   public Level restart(){
      return inicio;
   }


}
