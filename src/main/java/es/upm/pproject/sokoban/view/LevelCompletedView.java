package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.MenuController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LevelCompletedView {

    private VBox root;

    public LevelCompletedView(int levelScore, MenuController menuController) {

        Label title = new Label("¡NIVEL COMPLETADO!");
        Label scoreLabel = new Label("PUNTUACIÓN DEL NIVEL: " + levelScore);

        Button btnSigLevel = new Button("Siguiente Nivel");
        Button btnRestart = new Button("Reiniciar Nivel");
        Button btnMenu = new Button("Volver al menú");

        btnSigLevel.setOnAction(e -> menuController.siguienteNivel());
        btnRestart.setOnAction(e -> {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar reinicio");
            confirm.setHeaderText("¿Seguro que quieres reiniciar el nivel?");
            confirm.setContentText("Perderás el progreso actual de este nivel.");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    menuController.reiniciarNivelActual();
                }
            });
        });
        btnMenu.setOnAction(e -> {
            if (!menuController.hayPartidaActiva()){
                menuController.volverAlMenu();
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Volver al menu");
            confirm.setHeaderText("Seguro que quieres volver al menu?");
            confirm.setContentText("Perderas el progreso de la partida actual.");
            confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    menuController.volverAlMenu();
                }
            });
        });

        root = new VBox(10, title, scoreLabel, btnSigLevel, btnRestart, btnMenu);
    }

    public VBox getRoot() {
        return root;
    }
}
