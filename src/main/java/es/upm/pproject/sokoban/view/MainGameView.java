package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.GameController;
import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
         //Cambio para mensaje de sobreescritura del nivel
        restart.setOnAction(e -> {

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar reinicio");
            confirm.setHeaderText("¿Seguro que quieres reiniciar el nivel?");
            confirm.setContentText("Perderás el progreso actual de este nivel.");

            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            confirm.showAndWait().ifPresent(response -> {
                  if (response == ButtonType.YES) {
                  gameController.restart();
               }
            });
         });

        menu.setOnAction(e -> {
            if (!controller.hayPartidaActiva()){
                controller.volverAlMenu();
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Volver al menu");
            confirm.setHeaderText("Seguro que quieres volver al menu?");
            confirm.setContentText("Perderas el progreso de la partida actual.");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    controller.volverAlMenu();
                }
            });
        });
        save.setOnAction(e -> new SaveGameView(stage, controller, true));
        mas.setOnAction(e -> MusicView.turnUp());
        menos.setOnAction(e -> MusicView.turnDown());
        MusicView.stop();
        MusicView.start(getClass().getResource("/music/zelda_song.mp3").toExternalForm());
     }
}
