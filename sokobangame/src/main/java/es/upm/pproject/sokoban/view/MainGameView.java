package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainGameView {
     private final HBox hbox;
     private Button undo;
     private Button save;
     private Button restart;
     private Button mas;
     private Button menos;
     private Button menu;

     public MainGameView(Stage stage, GameController gameController, MenuController menuController){
        hbox = new HBox();
        undo = new Button("Undo");
        save = new Button("Save");
        restart = new Button("Restart");
        menu = new Button("Menú");
        mas = new Button("Audio +");
        menos = new Button("Audio -");
        crearVista(gameController, stage, menuController);
     }

     public HBox getRoot() {
        return hbox;
     }

     private void crearVista(GameController gameController, Stage stage, MenuController controller){
        hbox.setSpacing(40);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(undo, save, restart, menu, mas, menos);

        // Cada boton delega su accion en el GameController
        undo.setOnAction(e    -> gameController.undo());
        restart.setOnAction(e -> gameController.restart());
        menu.setOnAction(e -> controller.volverAlMenu());
        save.setOnAction(e -> new SaveGameView(stage, controller, true));
        mas.setOnAction(e -> MusicView.turnUp());
        menos.setOnAction(e -> MusicView.turnDown());
        MusicView.stop();
        MusicView.start(getClass().getResource("/music/zelda_song.mp3").toExternalForm());
     }
}
