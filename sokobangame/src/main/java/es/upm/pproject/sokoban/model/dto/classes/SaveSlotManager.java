package es.upm.pproject.sokoban.model.dto.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;
import es.upm.pproject.sokoban.model.dto.interfaces.ISaveSlotManager;

public class SaveSlotManager implements ISaveSlotManager{
   private static Logger logger = LoggerFactory.getLogger(SaveSlotManager.class);

    @Override
    public void guardarPartida(ICurrentGameState estado, int slot) throws IOException{
        
        String nombreArchivo = "slot" + slot + ".dat";
        // Abre o crea si no existe el archivo
        try (FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        ObjectOutputStream salida = new ObjectOutputStream(archivo)) {
        // Escribe el objeto en el archivo
        salida.writeObject(estado);
        } catch (FileNotFoundException e) {
          logger.info(e.getMessage());
        } catch(NullPointerException e){
            logger.info(e.getMessage());
        } catch(InvalidClassException e){
            logger.info(e.getMessage());
        }
        // Convierte el objeto en bytes
        
    }
    @Override
    public ICurrentGameState cargarPartida(int slot)  throws IOException, ClassNotFoundException{
        String nombreArchivo = "slot" + slot + ".dat";
        CurrentGameState estado = null;
    try(  //Abre el archivo del slot indica para leer bytes
        FileInputStream archivo = new FileInputStream(nombreArchivo);
        // Convierte los bytes en un objeto
        ObjectInputStream entrada = new ObjectInputStream(archivo)){
            estado = (CurrentGameState) entrada.readObject();
        } catch (FileNotFoundException e) {
          logger.info(e.getMessage());
        } catch(NullPointerException e){
            logger.info(e.getMessage());
        } catch(InvalidClassException e){
            logger.info(e.getMessage());
        }
        // Lee el objeto del archivo
        return estado;
    }
    @Override
    public boolean existeSlot(int slot){
        String nombreArchivo = "slot" + slot + ".dat";
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }
    @Override
    public void borrarSlot(int slot){
        String nombreArchivo = "slot" + slot + ".dat";
          try{
            Files.delete(Path.of(nombreArchivo));
          } catch(NoSuchFileException e){
            logger.info("No existe esta partida guardada");
          } catch(IOException e){
            logger.info(e.getMessage());
          }
    }

}
