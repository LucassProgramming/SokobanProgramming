package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainGameView extends HBox {
     private Button undo;
     private Button save;
     private Button restart;
     private Button mas;
     private Button menos;
     private Button menu;
     private MusicView musicView;

     public MainGameView(Stage stage, GameController gameController, MenuController menuController){
        undo = new Button("Undo");
        save = new Button("Save");
        restart = new Button("Restart");
        menu = new Button("Menú");
        musicView = new MusicView("/music/zelda_song.mp3");
        mas = new Button("Audio +");
        menos = new Button("Audio -");
        crearVista(gameController, stage, menuController);
     }

     private void crearVista(GameController gameController, Stage stage, MenuController controller){
        this.setSpacing(40);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(undo, save, restart, menu,mas,menos);

        // Cada boton delega su accion en el GameController
        undo.setOnAction(e    -> gameController.undo());
        restart.setOnAction(e -> gameController.restart());
        menu.setOnAction(e -> { controller.volverAlMenu();});
        save.setOnAction(e -> new SaveGameView(stage, controller, true));
        mas.setOnAction(e -> MusicView.turnUp());
        menos.setOnAction(e -> MusicView.turnDown());
        MusicView.stop();
        musicView.start();
     }
}
