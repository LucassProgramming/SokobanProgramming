package es.upm.pproject.sokoban;

import es.upm.pproject.sokoban.controller.MenuController;
import es.upm.pproject.sokoban.view.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setFullScreenExitHint("");//Quita este mensaje que sale con la pantalla completa: Press ESC to exit full screen
        stage.setFullScreen(true); /*Con esto la pantalla se abre de primeras con pantalla completa*/
        MenuController controller = new MenuController(stage);
        new MainMenuView(stage, controller);
    }

    public static void main(String[] args) {
        launch();
    }
}