package es.upm.pproject.sokoban.model.dto.classes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;


class SaveSlotManagerTest {
    @Test
    void guardarPartida_CrearArchivo() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        CurrentGameState estado = new CurrentGameState();
        manager.guardarPartida(estado, 1);
        File archivo = new File("slot1.dat");
        assertTrue(archivo.exists());
        archivo.delete();
    }
    @Test
    void existeSlot() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        CurrentGameState estado = new CurrentGameState();
        manager.guardarPartida(estado, 2);
        assertTrue(manager.existeSlot(2));
        File archivo = new File("slot2.dat");
        archivo.delete();
    }
    @Test
    void borrarSlot() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        CurrentGameState estado = new CurrentGameState();
        manager.guardarPartida(estado, 3);
        manager.borrarSlot(3);
        File archivo = new File("slot3.dat");
        assertFalse(archivo.exists());
    }
    @Test
    void cargarPartida_RecuperaEstadoCorrectamente() throws IOException, ClassNotFoundException{
        SaveSlotManager manager = new SaveSlotManager();
        CurrentGameState estadoOriginal = new CurrentGameState();
        Square[][] capaInf = new Square[2][2];
        Square[][] capaSup = new Square[2][2];
        Level nivel = new Level(capaInf, capaSup);
        estadoOriginal.anadirLevel(nivel);
        manager.guardarPartida(estadoOriginal, 1);
        ICurrentGameState estadoCargado = manager.cargarPartida(1);
        assertNotNull(estadoCargado);
        assertEquals(1, estadoCargado.getIndex());

        File archivo = new File("slot1.dat");
        archivo.delete();
    }

    @Test
    void cargarPartidaSiNoExisteDevuelveNull() throws IOException, ClassNotFoundException {
        SaveSlotManager manager = new SaveSlotManager();
        assertEquals(null, manager.cargarPartida(99));
    }
    @Test
    void borrarSlotSiNoExisteNoFalla() {
        SaveSlotManager manager = new SaveSlotManager();
        manager.borrarSlot(99);
        assertFalse(manager.existeSlot(99));
    }
    @Test
    void guardarPartidaSiHayUnaCarpetaNoFalla() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        File carpeta = new File("slot98.dat");
        carpeta.mkdir();
        manager.guardarPartida(new CurrentGameState(), 98);
        assertTrue(carpeta.exists());
        carpeta.delete();
    }
    @Test
    void borrarSlotConCarpetaNoVaciaNoFalla() throws IOException {
        SaveSlotManager manager = new SaveSlotManager();
        Path carpeta = Path.of("slot97.dat");
        Path archivo = carpeta.resolve("partida.txt");
        Files.createDirectory(carpeta);
        Files.createFile(archivo);
        manager.borrarSlot(97);
        assertTrue(Files.exists(carpeta));
        Files.delete(archivo);
        Files.delete(carpeta);
    }
}
