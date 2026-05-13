package es.upm.pproject.sokoban.model.dto.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import es.upm.pproject.sokoban.model.dto.interfaces.ICurrentGameState;
import es.upm.pproject.sokoban.model.dto.interfaces.ISaveSlotManager;

public class SaveSlotManager implements ISaveSlotManager, Serializable{
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
    public ICurrentGameState cargarPartida(int slot)  throws IOException, ClassNotFoundException{return null;}
    @Override
    public boolean existeSlot(int slot){return false;}
    @Override
    public void borrarSlot(int slot){}


}
