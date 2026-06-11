package es.upm.pproject.sokoban.view;

import java.util.List;

import es.upm.pproject.sokoban.controller.MenuController;
import es.upm.pproject.sokoban.model.dto.classes.Level;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameCompleteView extends BorderPane {

    public GameCompleteView(Stage stage, MenuController menuController, int totalScore, int levelsCompleted, List<Level> allLevels) {
        setPrefSize(800, 600);
        setStyle("-fx-background-color: #1b1b1b;");

        Label title = new Label("¡MUNDO COMPLETADO!");
        title.setStyle("-fx-font-size: 44px; -fx-font-weight: bold; -fx-text-fill: #55AA33; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 6, 0.7, 0, 2);");
        title.setTextFill(Color.web("#55AA33"));

        Label subtitle = new Label("Has completado todos los niveles del mundo.");
        subtitle.setStyle("-fx-font-size: 20px; -fx-text-fill: #DADADA;");

        Label scoreLabel = new Label("PUNTUACIÓN TOTAL: " + totalScore);
        scoreLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFD700; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 4, 0.7, 1, 1);");

        Label levelsLabel = new Label("NIVELES COMPLETADOS: " + levelsCompleted + " / " + allLevels.size());
        levelsLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #FFFFFF;");

        VBox headerBox = new VBox(12, title, subtitle, scoreLabel, levelsLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(30, 20, 10, 20));

        Label summaryTitle = new Label("RESUMEN POR NIVEL:");
        summaryTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");
        summaryTitle.setPadding(new Insets(10, 0, 5, 0));

        VBox summaryList = new VBox(6);
        summaryList.setPadding(new Insets(10));
        summaryList.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 8px;");

        for (int i = 0; i < allLevels.size(); i++) {
            Level level = allLevels.get(i);
            int score = level != null && level.getPuntuacion() != null ? level.getPuntuacion().getPuntuacion() : 0;
            String name = level != null ? level.getNombre() : "Desconocido";
            Label levelLabel = new Label("Nivel " + (i + 1) + " - " + name + ": " + score + " pts");
            levelLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #DADADA;");
            summaryList.getChildren().add(levelLabel);
        }

        VBox summaryContainer = new VBox(8, summaryTitle, summaryList);
        summaryContainer.setPadding(new Insets(0, 20, 10, 20));

        ScrollPane scrollPane = new ScrollPane(summaryContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setPrefViewportHeight(300);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Button menuButton = new Button("VOLVER AL MENÚ");
        menuButton.setPrefSize(220, 48);
        menuButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #6B6B6B; -fx-text-fill: #FFFFFF; -fx-border-color: #2B2B2B; -fx-border-width: 3; -fx-background-radius: 8px; -fx-border-radius: 8px;");
        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #8B8B8B; -fx-text-fill: #FFFF55; -fx-border-color: #FFFFFF; -fx-border-width: 3; -fx-background-radius: 8px; -fx-border-radius: 8px;"));
        menuButton.setOnMouseExited(e -> menuButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #6B6B6B; -fx-text-fill: #FFFFFF; -fx-border-color: #2B2B2B; -fx-border-width: 3; -fx-background-radius: 8px; -fx-border-radius: 8px;"));
        menuButton.setOnAction(e -> menuController.volverAlMenu());

        VBox buttonBox = new VBox(menuButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 30, 0));

        setTop(headerBox);
        setCenter(scrollPane);
        setBottom(buttonBox);
    }
}
