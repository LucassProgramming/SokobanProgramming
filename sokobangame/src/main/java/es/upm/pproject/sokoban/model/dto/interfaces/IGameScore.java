package es.upm.pproject.sokoban.model.dto.interfaces;

public interface IGameScore {
    
    public int getTotal();

    public void setTotal(int total);

    public int totalScores(IScore[] arrayScore);
}
