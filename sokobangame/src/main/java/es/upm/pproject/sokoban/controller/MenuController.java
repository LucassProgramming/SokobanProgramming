package es.upm.pproject.sokoban.controller;
import es.upm.pproject.sokoban.view.GameInfoView;
import es.upm.pproject.sokoban.view.MainMenuView;
import javafx.scene.Scene;
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
    public void iniciarJuego(){
        GameInfoView gameInfoView = new GameInfoView("Nivel 1", 1, 0, 0);
        Scene scene = new Scene(gameInfoView, 900, 120);
        stage.setScene(scene);
    }
    public void cargarPartida(){
        // SaveSlotManager
        System.out.println("Load Game");
    }
    public void cerrarApp(){
        stage.close();
    }
}
