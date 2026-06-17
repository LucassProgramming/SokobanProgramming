package es.upm.pproject.sokoban.model.dto.interfaces;
import java.io.IOException;

import es.upm.pproject.sokoban.model.dto.classes.CurrentGameState;

public interface ISaveSlotManager {
    /*
    * Guarda el estado actual del juego en el slot indicado
    * @param estado el estado actual del juego 
    * @param slot el slot donde se guardará la partida
    * @throws IOException si ocurre un error al guardar la partida
    */
    void guardarPartida(CurrentGameState estado, int slot) throws IOException;
    /*
    * Carga el estado del juego desde el slot indicado
    * @param slot el slot desde donde se cargará la partida
    * @return el estado del juego cargado desde el slot
    * @throws IOException si ocurre un error al cargar la partida
    * @throws ClassNotFoundException si la clase del estado del juego no se encuentra al cargar la partida
    */
    CurrentGameState cargarPartida(int slot)  throws IOException, ClassNotFoundException;
    /*
    * Comprueba si existe un slot guardado
    * @param slot slot a comprobar
    * @return true si existe
     */
    boolean existeSlot(int slot);
    /*
    * Borra el slot indicado
    * @param slot el slot a borrar
    */
    void borrarSlot(int slot);
}
