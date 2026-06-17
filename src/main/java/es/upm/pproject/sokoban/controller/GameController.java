package es.upm.pproject.sokoban.controller;

import es.upm.pproject.sokoban.model.dto.classes.CurrentGameState;
import es.upm.pproject.sokoban.model.dto.classes.Direccion;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import es.upm.pproject.sokoban.view.BoardView;
import es.upm.pproject.sokoban.view.GameInfoView;
import javafx.scene.input.KeyCode;

public class GameController {

    private CurrentGameState estado;
    private BoardView boardView;
    private GameInfoView gameInfoView;
    private int levelNum;
    private MenuController menuController;

    public GameController(CurrentGameState estado, BoardView boardView,
            GameInfoView gameInfoView, int levelNum, MenuController menuController) {
        this.estado = estado;
        this.boardView = boardView;
        this.gameInfoView = gameInfoView;
        this.levelNum = levelNum;
        this.menuController = menuController;
    }

    // Recibe la tecla pulsada, la convierte en direccion y mueve al personaje
    public void handleKey(KeyCode code) {
        Direccion dir = toDireccion(code);
        if (dir == null) return; // tecla irrelevante, no hacer nada

        // Pide al nivel actual que mueva al personaje en esa direccion
        estado.moverPersonaje(dir);

        // Refresca la pantalla con el nuevo estado del nivel
        actualizarVistas();
        if(estado.getCurrent().estaCompletado()){
            menuController.siguienteNivel();
        }
    }

    // Deshace el ultimo movimiento y refresca la pantalla
    public void undo() {
        estado.reversionEstado();
        actualizarVistas();
    }

    // Reinicia el nivel al estado inicial y refresca la pantalla
    public void restart() {
        estado.restart();
        actualizarVistas();
    }

    // Traduce la tecla del teclado a un incremento de fila/columna
    // Fila sube → incX negativo; fila baja → incX positivo
    // Columna izq → incY negativo; columna der → incY positivo
    private Direccion toDireccion(KeyCode code) {
        switch (code) {
            case UP: return new Direccion(-1,  0);    
            case W: return new Direccion(-1,  0);
            case DOWN: return new Direccion( 1,  0);  
            case S: return new Direccion( 1,  0);
            case LEFT: return new Direccion( 0, -1);  
            case A: return new Direccion( 0, -1);
            case RIGHT: return new Direccion( 0,  1); 
            case D: return new Direccion( 0,  1);
            default:            return null;
        }
    }

    // Actualiza el tablero y la barra de info con los datos del nivel actual
    private void actualizarVistas() {
        Level level = estado.getCurrent();
        boardView.actualizar(level);
        gameInfoView.actualizarInfo(
            level.getNombre(),
            levelNum,
            level.getPuntuacion().getPuntuacion(),
            estado.getPuntuacionTotal().getTotal() // puntuacion total: se conectara cuando exista GameScore
        );
    }
}
