package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.classes.GameScore;
import es.upm.pproject.sokoban.model.dto.classes.Score;
import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class GameScoreTest {

    private GameScore gameScore;

    @BeforeEach
    void setUp() {
        gameScore = new GameScore();
    }

    @Test
    void totalInicialEsCero() {
        assertEquals(0, gameScore.getTotal());
    }

    @Test
    void setTotalGuardaValor() {
        gameScore.setTotal(42);
        assertEquals(42, gameScore.getTotal());
    }

    @Test
    void setTotalSobreescribe() {
        gameScore.setTotal(10);
        gameScore.setTotal(99);
        assertEquals(99, gameScore.getTotal());
    }

    @Test
    void setTotalValorNegativo() {
        gameScore.setTotal(-5);
        assertEquals(-5, gameScore.getTotal());
    }

    @Test
    void totalScoresArrayVacioDevuelveCero() {
        IScore[] scores = new IScore[0];
        int resultado = gameScore.totalScores(scores);
        assertEquals(0, resultado);
    }

    @Test
    void totalScoresUnSoloElemento() {
        Score s = new Score();
        s.setPuntuacion(15);
        int resultado = gameScore.totalScores(new IScore[]{s});
        assertEquals(15, resultado);
    }

    @Test
    void totalScoresVariosElementosSuma() {
        Score s1 = new Score();
        s1.setPuntuacion(10);
        Score s2 = new Score();
        s2.setPuntuacion(20);
        Score s3 = new Score();
        s3.setPuntuacion(30);
        int resultado = gameScore.totalScores(new IScore[]{s1, s2, s3});
        assertEquals(60, resultado);
    }

    @Test
    void totalScoresActualizaGetTotal() {
        Score s = new Score();
        s.setPuntuacion(7);
        gameScore.totalScores(new IScore[]{s});
        assertEquals(7, gameScore.getTotal());
    }

    @Test
    void totalScoresConCerosDevuelveCero() {
        Score s1 = new Score();
        Score s2 = new Score();
        int resultado = gameScore.totalScores(new IScore[]{s1, s2});
        assertEquals(0, resultado);
    }
}
