package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void puntuacionInicialEsCero() {
        assertEquals(0, score.getPuntuacion());
    }

    @Test
    void setPuntuacionGuardaValor() {
        score.setPuntuacion(10);
        assertEquals(10, score.getPuntuacion());
    }

    @Test
    void setPuntuacionSobreescribe() {
        score.setPuntuacion(5);
        score.setPuntuacion(20);
        assertEquals(20, score.getPuntuacion());
    }

    @Test
    void setPuntuacionValorCero() {
        score.setPuntuacion(15);
        score.setPuntuacion(0);
        assertEquals(0, score.getPuntuacion());
    }

    @Test
    void setPuntuacionValorNegativo() {
        score.setPuntuacion(-3);
        assertEquals(-3, score.getPuntuacion());
    }

    @Test
    void incrementarAumentaEnUno() {
        score.incrementar();
        assertEquals(1, score.getPuntuacion());
    }

    @Test
    void incrementarVariasVeces() {
        score.incrementar();
        score.incrementar();
        score.incrementar();
        assertEquals(3, score.getPuntuacion());
    }

    @Test
    void incrementarDesdeValorNoNulo() {
        score.setPuntuacion(7);
        score.incrementar();
        assertEquals(8, score.getPuntuacion());
    }
}
