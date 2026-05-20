package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import es.upm.pproject.sokoban.model.dto.classes.LevelFileReader;
import es.upm.pproject.sokoban.view.BoardView;
import es.upm.pproject.sokoban.view.GameInfoView;
import es.upm.pproject.sokoban.view.MainGameView;
import es.upm.pproject.sokoban.view.MainMenuView;
import java.nio.file.Paths;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
    private Stage stage;
    private MainMenuView mainMenuView;

    public MenuController(Stage stage) {
        this.stage = stage;
    }
    public void setMainMenuView(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }
    public void iniciarJuego() throws Exception {
        String path = Paths.get(getClass().getResource("/levels/nivel1.txt").toURI()).toString();
        Level level = LevelFileReader.CrearNivel(path);

        GameInfoView gameInfoView = new GameInfoView(level.getNombre(), 1, 0, 0);
        BoardView boardView = new BoardView(level);
        MainGameView mainGameView = new MainGameView(stage,this);

        BorderPane root = new BorderPane();
        root.setTop(gameInfoView);
        root.setCenter(boardView);
        root.setBottom(mainGameView);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    public void cargarPartida(int slot){
        System.out.println("Cargar slot" + slot);
    }
    public void guardarPartida(int slot){
        System.out.println("Guardar en slot " + slot);
    }
    public void cerrarApp(){
        stage.close();
    }
}
