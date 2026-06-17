package es.upm.pproject.sokoban.model.exceptions;

public class CouldntCloneException extends RuntimeException {

    public CouldntCloneException(CloneNotSupportedException e){
        super("No se puede clonar " +  e);
    }

}
