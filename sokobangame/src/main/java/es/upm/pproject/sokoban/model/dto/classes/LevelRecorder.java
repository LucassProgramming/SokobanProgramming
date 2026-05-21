package es.upm.pproject.sokoban.model.dto.classes;

import java.util.Stack;

public class LevelRecorder {
   private static Stack<Level> estadoNivel = new Stack<>();
   private static Level inicio;
   private static Level copia;


   public static void save(Level elNivel) {
    //guardo el estado del nivel a cada movimiento

    estadoNivel.push(clonarLevel(elNivel));
   }

   public static Level undo() {
    // Devuelvo el estado anterior del nivel 
    if(estadoNivel.empty()){
      return null;
    }
    return estadoNivel.pop();
   }
   public static void setInicio(Level level){
       inicio = clonarLevel(level);
   }
   public static Level restart(){
      Level incio = clonarLevel(inicio);
      return incio;
   }
   public static void reiniciarStack(){
      estadoNivel = new Stack<>();
   }
   private static Level clonarLevel(Level level){
      Square [][] capaInf = level.getCapaInf();
      Square [][] capaSup = level.getCapaSup();
      Square [][] copiaSup = new Square [capaSup.length][];
      for(int i = 0; i<capaSup.length;i++){
         copiaSup[i] = capaSup[i].clone();
      }
      copia = new Level(level.getNombre(), level.getFilas(),
       level.getColumnas(), capaInf, copiaSup, level.getPuntuacion().clone(), level.getCharacter().clone());
      return copia;
   }

}
