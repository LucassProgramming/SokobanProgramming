package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface IBoxManager {
    
 boolean moveBox(ILevel level,int x, int y);

}