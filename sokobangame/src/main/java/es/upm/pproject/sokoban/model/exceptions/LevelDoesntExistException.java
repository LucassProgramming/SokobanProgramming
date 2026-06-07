package es.upm.pproject.sokoban.model.exceptions;

public class LevelDoesntExistException extends RuntimeException {
    public LevelDoesntExistException(String archivo){
        super("No se ha encontrado el nivel: " + archivo);
    }

}
