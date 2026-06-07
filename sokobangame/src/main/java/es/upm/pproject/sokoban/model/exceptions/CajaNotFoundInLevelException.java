package es.upm.pproject.sokoban.model.exceptions;

public class CajaNotFoundInLevelException extends RuntimeException {
    public CajaNotFoundInLevelException(String level){
       super("Error en el nivel " + level + ": no hay cajas.");
    }
}
