package es.upm.pproject.sokoban.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.model.dto.classes.CurrentGameState;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import es.upm.pproject.sokoban.model.dto.classes.LevelFileReader;
import es.upm.pproject.sokoban.model.dto.classes.SaveSlotManager;
import es.upm.pproject.sokoban.view.BoardView;
import es.upm.pproject.sokoban.view.GameCompleteView;
import es.upm.pproject.sokoban.view.GameInfoView;
import es.upm.pproject.sokoban.view.MainGameView;
import es.upm.pproject.sokoban.view.MainMenuView;
import es.upm.pproject.sokoban.view.MusicView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;




public class MenuController {
    private Stage stage;
    private CurrentGameState estadoActual;
    private SaveSlotManager saveSlotManager = new SaveSlotManager();
    private boolean partidaEnCurso = false;
    private List<Level> niveles;
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    public MenuController(Stage stage) {
        this.stage = stage;
        niveles = LevelFileReader.cargarTodosLosNiveles();
    }
    public void iniciarJuego(){
        estadoActual = new CurrentGameState();
        partidaEnCurso = true;
        estadoActual.setCurrent(niveles.get(0));
        Level level = estadoActual.getCurrent();

        GameInfoView gameInfoView = new GameInfoView(level.getNombre(), 1, 0,
         estadoActual.getPuntuacionTotal().getTotal());
        BoardView boardView = new BoardView(level);

        GameController gameController = new GameController(estadoActual, boardView, gameInfoView, 1, this);
        mostrarEscenaJuego(gameInfoView, boardView, gameController);
    }
    public void cargarPartida(int slot){
        try{
            estadoActual = saveSlotManager.cargarPartida(slot);
            if (estadoActual == null) {
                logger.info("No se pudo cargar la partida del slot {}", slot);
                return;
            }
            if (estadoActual.getArray() == null || estadoActual.getCurrent() == null) {
                logger.info("Estado de partida invalido en slot {}", slot);
                return;
            }

            List<Level> loadedLevels = new ArrayList<>();
            for (Level level : estadoActual.getArray()) {
                if (level == null) break;
                loadedLevels.add(level);
            }
            if (!loadedLevels.isEmpty()) {
                for (int i = 0; i < loadedLevels.size() && i < niveles.size(); i++) {
                    if (loadedLevels.get(i) != null) {
                        niveles.set(i, loadedLevels.get(i));
                    }
                }
            }

            Level level = estadoActual.getCurrent();
            partidaEnCurso = true;
            GameInfoView gameInfoView = new GameInfoView(level.getNombre(), estadoActual.getIndex(),
             level.getPuntuacion().getPuntuacion(), estadoActual.getPuntuacionTotal().getTotal());

            BoardView boardView = new BoardView(level);
            GameController gameController = new GameController(estadoActual, boardView, gameInfoView, estadoActual.getIndex(), this);
            mostrarEscenaJuego(gameInfoView, boardView, gameController);
            logger.info("Partida cargada correctamente {}", slot);
        } catch (Exception e) {
            logger.info("Error al cargar la partida");
        }
    }
    public void guardarPartida(int slot){
        try {
            saveSlotManager.guardarPartida(estadoActual, slot);
            logger.info("Partida guardada en slot {}", slot);
        } catch (Exception e) {
            logger.info("Error al guardar la partida en slot {}", slot);
        }
    }
    /*
     * Vuelve al menu principal y marca que no hay partida en curso
     */
    public void volverAlMenu(){
        partidaEnCurso = false;
        this.niveles = LevelFileReader.cargarTodosLosNiveles();
        new MainMenuView(stage, this);
    }
    /*
    * Comprueba si existe una partida guardada en el slot indicado
    * Se utiliza en SaveGameView para mostrar visualmente que los slots estan ocupados
    */
    public boolean existeSlot(int slot){
        return saveSlotManager.existeSlot(slot);
    }
    public boolean hayPartidaActiva(){
        return partidaEnCurso && estadoActual != null && estadoActual.getCurrent() != null;
    }
    public void siguienteNivel(){
        int siguiente = estadoActual.getIndex();
        if(siguiente < niveles.size()){
            estadoActual.setCurrent(niveles.get(siguiente));
            Level level = estadoActual.getCurrent();
            GameInfoView gameInfoView = new GameInfoView(level.getNombre(), siguiente + 1,
             level.getPuntuacion().getPuntuacion(), estadoActual.getPuntuacionTotal().getTotal());
            BoardView boardView = new BoardView(level);
            GameController gameController = new GameController(estadoActual, boardView, gameInfoView, siguiente + 1, this);
            mostrarEscenaJuego(gameInfoView, boardView, gameController);
        } else {
            logger.info("Juego completado");
            int totalScore = estadoActual.getPuntuacionTotal().getTotal();
            int levelsCompleted = estadoActual.getIndex();
            List<Level> completedLevels = new ArrayList<>();
            for (Level level : estadoActual.getArray()) {
                if (level == null) break;
                completedLevels.add(level);
            }
            if (completedLevels.isEmpty()) {
                completedLevels = niveles;
            }
            GameCompleteView gameCompleteView = new GameCompleteView(this, totalScore, levelsCompleted, completedLevels);

            //Añadimos esto porque asi­ mantenemos las dimensiones que tenia la ventana maximizada
            //Usamos el tamanho de la escena actual y no el del Stage.Ya que el Stage incluye la barra de titulo y los bordes de la ventana,
            // mientras que la Scene representa solo el area util donde se dibuja la interfaz.
            Scene scene = new Scene(gameCompleteView.getRoot(),stage.getScene().getWidth(),stage.getScene().getHeight());
            stage.setScene(scene);

            //Quitamos el stage.setFullScreen porque eso obligaba a tener la pantalla completa sin los iconos de quitar ,maximizar y minimizar. AdemÃ¡s con poner setMaximized en App.java ya sirve para que se quede grande toda la aplicaciÃ³n,y salgan dichos botones.  

            stage.show();
        }
    }
    public void cerrarApp(){
        MusicView.dispose();
        Platform.exit();
        System.exit(0);
    }

    private void mostrarEscenaJuego(GameInfoView gameInfoView, BoardView boardView, GameController gameController) {
        MainGameView mainGameView = new MainGameView(stage, gameController, this);
        BorderPane root = new BorderPane();
        root.setTop(gameInfoView.getRoot());
        root.setCenter(boardView.getRoot());
        root.setBottom(mainGameView.getRoot());

        //Anhadimos esto porque asi­ mantenemos las dimensiones que teni­a la ventana maximizada
        //Usamos el tamanho de la escena actual y no el del Stage.Ya que el Stage incluye la barra de titulo y los bordes de la ventana,
        // mientras que la Scene representa solo el area util donde se dibuja la interfaz.
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> { gameController.handleKey(e.getCode()); e.consume(); });
        stage.setScene(scene);

        //Quitamos el stage.setFullScreen porque eso obligaba a tener la pantalla completa sin los iconos de quitar ,maximizar y minimizar. Ademas con poner setMaximized en App.java ya sirve para que se quede grande toda la aplicacion,y salgan dichos botones.

        stage.show();
    }
}
