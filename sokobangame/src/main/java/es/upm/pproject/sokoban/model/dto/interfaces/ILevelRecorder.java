package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface ILevelRecorder {
   void save(Level elNivel);
   void undo(int numeroAtras);
}
