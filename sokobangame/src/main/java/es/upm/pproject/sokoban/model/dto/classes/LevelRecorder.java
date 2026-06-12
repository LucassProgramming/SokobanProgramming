package es.upm.pproject.sokoban.model.dto.classes;
import java.util.ArrayDeque;
import java.util.Deque;

public class LevelRecorder {
   private static Deque<Level> estadoNivel = new ArrayDeque<>();
   private static Level inicio;


   private LevelRecorder(){}

   public static void save(Level elNivel) {
    //guardo el estado del nivel a cada movimiento
    estadoNivel.push(clonarLevel(elNivel));
   }

   public static Level undo() {
    // Devuelvo el estado anterior del nivel 
    if(estadoNivel.isEmpty()){
      return null; //Devuelve null ya que sino tiraria excepcion
    }
    return estadoNivel.pop();
   }
   public static void setInicio(Level level){
       inicio = clonarLevel(level); //Clono el nivel para que no haga referencia a la misma direccion de memoria
   }
   public static Level restart(){
      Level incio = clonarLevel(inicio); //Clono el nivel para que no haga referencia a la misma direccion de memoria
      reiniciarDeque(); //Borro el registro de movimientos anterior luego del restart
      return incio;
   }
   public static void reiniciarDeque(){
      estadoNivel = new ArrayDeque<>(); //Borro el registro de movimientos anterior
   }
   private static Level clonarLevel(Level level){ //Metodo para clonar un nivel en su totalidad
      Square [][] capaInf = level.getCapaInf(); 
      Square [][] capaSup = level.getCapaSup();
      Square [][] copiaSup = new Square [capaSup.length][]; //Creamos una copia de la capa superior la importante
      for(int i = 0; i<capaSup.length;i++){ 
         copiaSup[i] = capaSup[i].clone(); //Clonamos cada fila una por una
      }
      
      return new Level(level.getNombre(), level.getFilas(),
       level.getColumnas(), capaInf, copiaSup, 
       new Score(level.getPuntuacion()), new PlayableCharacter(level.getCharacter()));
   }

}
