package es.upm.pproject.sokoban.model.dto.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;
import es.upm.pproject.sokoban.model.dto.interfaces.ISaveSlotManager;

public class SaveSlotManager implements ISaveSlotManager{
    @Override
    public void guardarPartida(ICurrentGameState estado, int slot) throws IOException{
        
        String nombreArchivo = "slot" + slot + ".dat";
        // Abre o crea si no existe el archivo
        FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        // Convierte el objeto en bytes
        ObjectOutputStream salida = new ObjectOutputStream(archivo);
        // Escribe el objeto en el archivo
        salida.writeObject(estado);
        salida.close();
        archivo.close();
    }
    @Override
    public ICurrentGameState cargarPartida(int slot)  throws IOException, ClassNotFoundException{
        String nombreArchivo = "slot" + slot + ".dat";
        //Abre el archivo del slot indica para leer bytes
        FileInputStream archivo = new FileInputStream(nombreArchivo);
        // Convierte los bytes en un objeto
        ObjectInputStream entrada = new ObjectInputStream(archivo);
        // Lee el objeto del archivo
        ICurrentGameState estado = (ICurrentGameState) entrada.readObject();
        entrada.close();
        archivo.close();
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
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

}
