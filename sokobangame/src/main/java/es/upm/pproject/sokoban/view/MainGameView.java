package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainGameView extends HBox {
     private Stage stage;
     private MenuController controller;
     private Button undo;
     private Button save;
     private Button restart;
     private Button menu;

     public MainGameView(Stage stage, MenuController controller){
        this.stage = stage;
        this.controller = controller;
        undo = new Button("Undo");
        save = new Button("Save");
        restart = new Button("Restart");
        menu = new Button("Menú");
        CreaVista();
     }

     public void CreaVista(){
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(undo,save,restart,menu);
        menu.setOnAction(e -> new MainMenuView(stage, controller));
        restart.setOnAction(e -> controller.restart());
     }
}
