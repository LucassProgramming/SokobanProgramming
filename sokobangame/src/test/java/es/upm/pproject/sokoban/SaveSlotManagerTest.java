package es.upm.pproject.sokoban;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.classes.CurrentGameState;
import es.upm.pproject.sokoban.model.dto.classes.SaveSlotManager;


public class SaveSlotManagerTest {
    @Test
    void guardarPartida_CrearArchivo() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        CurrentGameState estado = new CurrentGameState();
        manager.guardarPartida(estado, 1);
        File archivo = new File("slot1.dat");
        assertTrue(archivo.exists());
        archivo.delete();
    }

}
