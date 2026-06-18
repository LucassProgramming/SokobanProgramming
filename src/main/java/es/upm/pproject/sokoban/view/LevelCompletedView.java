package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.controller.MenuController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class LevelCompletedView {

    private VBox root;
    private final String TEXTO_BLANCO = "-fx-text-fill: white;";
    private final String FUENTE_16 =  "-fx-font-size: 16px;";
    private final String PADDING = "-fx-padding: 10 20 10 20;";
    private final String BACKGROUND_RADIO =  "-fx-background-radius: 8;";
    private final String CURSOR = "-fx-cursor: hand;";

    public LevelCompletedView(int levelScore,int nivelesCompletados,int totalNiveles, MenuController menuController) {

        Label title = new Label("¡NIVEL COMPLETADO!");
        title.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #f1c40f;" +
                "-fx-effect: dropshadow(gaussian, rgba(241,196,15,0.6), 15, 0.5, 0, 0);"
        );
        //Estilos de los Labels
        Label scoreLabel = new Label("PUNTUACIÓN DEL NIVEL: " + levelScore);
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #dcdcdc;");

        Label progressLabel = new Label("Niveles completados: " + nivelesCompletados + "/" + totalNiveles);//Texto añadido para saber los niveles completados y los que te quedan
        progressLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #dcdcdc;");

        Button btnSigLevel = new Button("Siguiente Nivel");
        Button btnRestart = new Button("Reiniciar Nivel");
        Button btnMenu = new Button("Volver al menú");
        //Estilos de los botones
        String estiloBotonPrincipal =
                "-fx-background-color: #27ae60;" +
                TEXTO_BLANCO +
                FUENTE_16 +
                "-fx-font-weight: bold;" +
                PADDING +
                BACKGROUND_RADIO +
                CURSOR;

        String estiloBotonSecundario =
                "-fx-background-color: #34495e;" +
                TEXTO_BLANCO +
                FUENTE_16 +
                PADDING +
                BACKGROUND_RADIO +
                CURSOR;

        String estiloBotonPeligro =
                "-fx-background-color: #c0392b;" +
                TEXTO_BLANCO +
                FUENTE_16 +
                PADDING +
                BACKGROUND_RADIO +
                CURSOR;

        btnSigLevel.setStyle(estiloBotonPrincipal);
        btnRestart.setStyle(estiloBotonSecundario);
        btnMenu.setStyle(estiloBotonPeligro);

        //Efecto de cuando pones el ratón encima de los botones
        btnSigLevel.setOnMouseEntered(e ->
            btnSigLevel.setStyle(estiloBotonPrincipal + "-fx-background-color: #2ecc71;"));
        btnSigLevel.setOnMouseExited(e ->
            btnSigLevel.setStyle(estiloBotonPrincipal));

        btnRestart.setOnMouseEntered(e ->
            btnRestart.setStyle(estiloBotonSecundario + "-fx-background-color: #4a6572;"));
        btnRestart.setOnMouseExited(e ->
            btnRestart.setStyle(estiloBotonSecundario));

        btnMenu.setOnMouseEntered(e ->
            btnMenu.setStyle(estiloBotonPeligro + "-fx-background-color: #e74c3c;"));
        btnMenu.setOnMouseExited(e ->
            btnMenu.setStyle(estiloBotonPeligro));


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

        root = new VBox(15,title,scoreLabel,progressLabel, btnSigLevel, btnRestart, btnMenu);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #1e1e1e;");
        // Ancho uniforme para los botones
        btnSigLevel.setPrefWidth(220);
        btnRestart.setPrefWidth(220);
        btnMenu.setPrefWidth(220);
    }

    public VBox getRoot() {
        return root;
    }
}
