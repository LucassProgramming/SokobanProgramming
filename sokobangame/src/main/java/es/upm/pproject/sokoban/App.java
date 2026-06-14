package es.upm.pproject.sokoban;

import es.upm.pproject.sokoban.controller.MenuController;
import es.upm.pproject.sokoban.view.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setMaximized(true);
        MenuController controller = new MenuController(stage);
        new MainMenuView(stage, controller);
    }

    public static void main(String[] args) {
        launch();
    }
}