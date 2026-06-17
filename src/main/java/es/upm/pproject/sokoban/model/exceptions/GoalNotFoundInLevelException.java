package es.upm.pproject.sokoban.model.exceptions;

public class GoalNotFoundInLevelException extends RuntimeException {
   public GoalNotFoundInLevelException(String level){
       super("Error en el nivel " + level + ": no hay metas.");
  }
}
