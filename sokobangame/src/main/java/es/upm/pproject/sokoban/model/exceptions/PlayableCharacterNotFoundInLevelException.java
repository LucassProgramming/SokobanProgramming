package es.upm.pproject.sokoban.model.exceptions;

public class PlayableCharacterNotFoundInLevelException extends RuntimeException {
   public PlayableCharacterNotFoundInLevelException(String level){
       super("Error en el nivel " + level + ": debe existir exactamente un personaje.");
  }
}
