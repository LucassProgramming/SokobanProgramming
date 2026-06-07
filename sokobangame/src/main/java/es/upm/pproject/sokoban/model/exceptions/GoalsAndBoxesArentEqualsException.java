package es.upm.pproject.sokoban.model.exceptions;

public class GoalsAndBoxesArentEqualsException extends RuntimeException {
    public GoalsAndBoxesArentEqualsException(String level){
       super("Error en el nivel " + level + ": el número de cajas y de metas no coincide.");
    }
}
