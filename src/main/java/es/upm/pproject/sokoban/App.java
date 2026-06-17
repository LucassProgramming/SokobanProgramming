package es.upm.pproject.sokoban;

import es.upm.pproject.sokoban.controller.MenuController;
import es.upm.pproject.sokoban.view.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        //Quitamos el stage.setFullScreen porque eso obligaba a tener la pantalla completa sin los iconos de quitar ,maximizar y minimizar. Con setMaximized haces que la App se despliegue ya maximizada pero además con los botones esos
        stage.setMaximized(true);
        MenuController controller = new MenuController(stage);
        stage.setOnCloseRequest(e -> controller.cerrarApp());
        new MainMenuView(stage, controller);
    }

    public static void main(String[] args) {
        launch();
    }
}