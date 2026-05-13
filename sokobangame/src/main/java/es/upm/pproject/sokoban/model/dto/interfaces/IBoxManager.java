package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface IBoxManager {
    
 boolean moveBox(Level level,int x, int y);

}