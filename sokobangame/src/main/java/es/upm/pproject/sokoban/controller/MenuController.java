package es.upm.pproject.sokoban.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.model.dto.classes.CurrentGameState;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import es.upm.pproject.sokoban.model.dto.classes.LevelFileReader;
import es.upm.pproject.sokoban.model.dto.classes.SaveSlotManager;
import es.upm.pproject.sokoban.view.BoardView;
import es.upm.pproject.sokoban.view.GameInfoView;
import es.upm.pproject.sokoban.view.MainGameView;
import es.upm.pproject.sokoban.view.MainMenuView;
import javafx.scene.Scene;
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

        // GameController conecta el teclado con el modelo y las vistas
        GameController gameController = new GameController(estadoActual, boardView, gameInfoView, 1, this);

        MainGameView mainGameView = new MainGameView(stage, gameController, this);

        BorderPane root = new BorderPane();
        root.setTop(gameInfoView);
        root.setCenter(boardView);
        root.setBottom(mainGameView);

        Scene scene = new Scene(root);

        // Cada vez que se pulsa una tecla, se lo pasamos al GameController
        scene.setOnKeyPressed(e -> gameController.handleKey(e.getCode()));

        stage.setScene(scene);
    }
    public void cargarPartida(int slot){
        try{
            estadoActual = saveSlotManager.cargarPartida(slot);
            Level level = estadoActual.getCurrent();
            GameInfoView gameInfoView = new GameInfoView(level.getNombre(), estadoActual.getIndex(),
             level.getPuntuacion().getPuntuacion(), estadoActual.getPuntuacionTotal().getTotal());

            BoardView boardView = new BoardView(level);
            GameController gameController = new GameController(estadoActual, boardView, gameInfoView, estadoActual.getIndex(), this);
            MainGameView mainGameView = new MainGameView(stage, gameController, this);
            BorderPane root = new BorderPane();
            root.setTop(gameInfoView);
            root.setCenter(boardView);
            root.setBottom(mainGameView);

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(e -> gameController.handleKey(e.getCode()));
            stage.setScene(scene);
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
     * Vuelve al menú principal y marca que no hay partida en curso
     */
    public void volverAlMenu(){
        partidaEnCurso = false;
        this.niveles = LevelFileReader.cargarTodosLosNiveles();
        new MainMenuView(stage, this);
    }
    /*
    * Comprueba si existe una partida guardad en el slot indicado
    * Se utiliza en SaveGameView para mostrar visualmente qué slots están ocupados
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
            MainGameView mainGameView = new MainGameView(stage, gameController, this);

            BorderPane root = new BorderPane();
            root.setTop(gameInfoView);
            root.setCenter(boardView);
            root.setBottom(mainGameView);

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(e -> gameController.handleKey(e.getCode()));
            stage.setScene(scene);
        } else {
            logger.info("Juego completado");
            //Aqui es donde tenemos que mostrar la pantalla de victoria o algo asi, por ahora volvemos al menu principal
            volverAlMenu();
        }
    }
    public void cerrarApp(){
        stage.close();
    }
}