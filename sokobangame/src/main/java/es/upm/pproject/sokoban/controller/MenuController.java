package es.upm.pproject.sokoban.controller;


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
    private MainMenuView mainMenuView;
    private CurrentGameState estadoActual;
    private SaveSlotManager saveSlotManager = new SaveSlotManager();
    private boolean partidaEnCurso = false;

    public MenuController(Stage stage) {
        this.stage = stage;
    }
    public void setMainMenuView(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }
    public void iniciarJuego() throws Exception {
        estadoActual = new CurrentGameState();
        partidaEnCurso = true;
        estadoActual.setCurrent(LevelFileReader.CrearNivel("levels/Level_2.txt"));
        Level level = estadoActual.getCurrent();

        GameInfoView gameInfoView = new GameInfoView(level.getNombre(), 1, 0, 0);
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
            estadoActual = (CurrentGameState) saveSlotManager.cargarPartida(slot);
            Level level = estadoActual.getCurrent();
            GameInfoView gameInfoView = new GameInfoView(level.getNombre(), estadoActual.getIndex(), level.getPuntuacion().getPuntuacion(), 0);

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
            System.out.println("Partida cargada correctamente" + slot);
        } catch (Exception e) {
            System.out.println("Error al cargar la partida");
        }
    }
    public void guardarPartida(int slot){
        try {
            saveSlotManager.guardarPartida(estadoActual, slot);
            System.out.println("Partida guardada en slot" + slot);
        } catch (Exception e) {
            System.out.println("Error al guardar la partida en slot" + slot);
        }
    }
    /*
     * Vuelve al menú principal y marca que no hay partida en curso
     */
    public void volverAlMenu(){
        partidaEnCurso = false;
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
        try{
            int siguiente = estadoActual.getIndex() + 1;
            Level siguienteLevel = LevelFileReader.CrearNivel("levels/Level_" + siguiente + ".txt");
            estadoActual.setCurrent(siguienteLevel);

            GameInfoView gameInfoView = new GameInfoView(siguienteLevel.getNombre(), siguiente, 0, 0);

            BoardView boardView = new BoardView(siguienteLevel);
            GameController gameController = new GameController(estadoActual, boardView, gameInfoView, siguiente, this);
            MainGameView mainGameView = new MainGameView(stage, gameController, this);

            BorderPane root = new BorderPane();
            root.setTop(gameInfoView);
            root.setCenter(boardView);
            root.setBottom(mainGameView);

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(e -> gameController.handleKey(e.getCode()));

            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("No hay mas niveles disponibles");
            volverAlMenu();
        }
    }
    public void cerrarApp(){
        stage.close();
    }
}