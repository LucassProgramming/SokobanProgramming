package es.upm.pproject.sokoban;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage stage){
        stage.setTitle("Sokoban");
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}