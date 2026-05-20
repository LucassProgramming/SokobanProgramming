package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainGameView extends HBox {
     private Button undo;
     private Button save;
     private Button restart;
     private Button menu;

     public MainGameView(Stage stage, GameController gameController){
        undo = new Button("Undo");
        save = new Button("Save");
        restart = new Button("Restart");
        menu = new Button("Menú");
        crearVista(gameController);
     }

     private void crearVista(GameController gameController){
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(undo, save, restart, menu);

        // Cada boton delega su accion en el GameController
        undo.setOnAction(e    -> gameController.undo());
        restart.setOnAction(e -> gameController.restart());
        // save y menu se conectaran cuando esten implementados
     }
}
