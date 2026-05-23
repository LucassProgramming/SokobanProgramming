package es.upm.pproject.sokoban.model.dto.interfaces;

import es.upm.pproject.sokoban.model.dto.classes.Level;

public interface IGameScore {
    
    public int getTotal();

    public void setTotal(int total);

    public int totalScores(Level [] arrayScore);
}
