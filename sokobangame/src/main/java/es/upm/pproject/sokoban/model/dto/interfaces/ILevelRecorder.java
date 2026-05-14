package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface ILevelRecorder {
   void save(Level elNivel); //Metodo para guardar el nivel actual en su totalidad, lo llama nivel antes de cada mover
   void undo(); // Metodo llamado por Controller para rehacer un movimiento 
}
