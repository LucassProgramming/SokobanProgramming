package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.dto.classes.GameScore;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import es.upm.pproject.sokoban.model.dto.classes.Score;
import es.upm.pproject.sokoban.model.dto.interfaces.IScore;

public class GameScoreTest { //

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
       Level[] scores = new Level[0];
        int resultado = gameScore.totalScores(scores);
        assertEquals(0, resultado);
    }

    @Test
    void totalScoresUnSoloElemento() {
        Level s = new Level();
        s.getPuntuacion().setPuntuacion(15);
        int resultado = gameScore.totalScores(new Level[]{s});
        assertEquals(15, resultado);
    }

    @Test
    void totalScoresVariosElementosSuma() {
        Level s1 = new Level();
        s1.getPuntuacion().setPuntuacion(10);
        Level s2 = new Level();
        s2.getPuntuacion().setPuntuacion(20);
        Level s3 = new Level();
        s3.getPuntuacion().setPuntuacion(30);
        int resultado = gameScore.totalScores(new Level[]{s1, s2, s3});
        assertEquals(60, resultado);
    }

    @Test
    void totalScoresActualizaGetTotal() {
        Level s = new Level();
        s.getPuntuacion().setPuntuacion(7);
        gameScore.totalScores(new Level[]{s});
        assertEquals(7, gameScore.getTotal());
    }

    @Test
    void totalScoresConCerosDevuelveCero() {
        Level s1 = new Level();
        Level s2 = new Level();
        int resultado = gameScore.totalScores(new Level[]{s1, s2});
        assertEquals(0, resultado);
    }
}
