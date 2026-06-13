package es.upm.pproject.sokoban.view;

import es.upm.pproject.sokoban.model.dto.classes.Box;
import es.upm.pproject.sokoban.model.dto.classes.Goal;
import es.upm.pproject.sokoban.model.dto.classes.PlayableCharacter;
import es.upm.pproject.sokoban.model.dto.classes.Square;
import es.upm.pproject.sokoban.model.dto.classes.Wall;
import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardView {

    /*Antes teniamos:
     private static final int TILE_SIZE = 64;
     Las casillas siempre medían 64x64 píxeles.
     
     Ahora ponemos esto para poder calcular el tamaño de las casillas según la 
     resolución de pantalla y el tamaño del nivel para aprovechar mejor el modo pantalla completa. */
    private final double tileSize;

    private final GridPane grid;
    private final Image sueloImg;
    private final Image muroImg;
    private final Image goalImg;
    private final Image cajaImg;
    private final Image cajaEnGoalImg;
    private final Image jugadorImg;
    private final Image golemEnGoalImg;

    public BoardView(ILevel level) {
    
    // Obtenemos con estas dos variables la resolución disponible de la pantalla
    double screenWidth =
            javafx.stage.Screen.getPrimary().getVisualBounds().getWidth();

    double screenHeight =
            javafx.stage.Screen.getPrimary().getVisualBounds().getHeight();

    // Reservar espacio para la barra superior e inferior, sin esto el tablero se pone demasiado grande y tapa los botones
    screenHeight -= 200; 
    //Calculamos el tamaño de cada casilla
    tileSize = Math.min(
            screenWidth / level.getColumnas(),
            screenHeight / level.getFilas()
    );

    grid = new GridPane();
    grid.setAlignment(javafx.geometry.Pos.CENTER);

    sueloImg = loadImage("/images/suelo.jpg");
    muroImg = loadImage("/images/muro.jpg");
    goalImg = loadImage("/images/goal.jpg");
    cajaImg = loadImage("/images/caja.png");
    cajaEnGoalImg = loadImage("/images/cajaengoal.png");
    jugadorImg = loadImage("/images/golemfondodepiedra.png");
    golemEnGoalImg = loadImage("/images/golemgoal.png");

    buildBoard(level);
}

    public GridPane getRoot() {
        return grid;
    }

    public void actualizar(ILevel level) {
        grid.getChildren().clear();
        buildBoard(level);
    }

    private void buildBoard(ILevel level) {
        Square[][] capaInf = level.getCapaInf();
        Square[][] capaSup = level.getCapaSup();

        for (int i = 0; i < level.getFilas(); i++) {
            for (int j = 0; j < level.getColumnas(); j++) {
                StackPane cell = new StackPane();

                cell.getChildren().add(bottomTile(capaInf[i][j]));

                Square sup = capaSup[i][j];
                if (sup != null) {
                    boolean onGoal = capaInf[i][j] instanceof Goal;
                    Node top = topTile(sup, onGoal);
                    if (top != null) cell.getChildren().add(top);
                }

                grid.add(cell, j, i);
            }
        }
    }

    private ImageView bottomTile(Square square) {
        Image img;
        if (square instanceof Wall) img = muroImg;
        else if (square instanceof Goal) img = goalImg;
        else img = sueloImg;
        return makeImageView(img);
    }

    private Node topTile(Square square, boolean onGoal) {
        if (square instanceof Box) {
            return makeImageView(onGoal ? cajaEnGoalImg : cajaImg);
        }
        if (square instanceof PlayableCharacter) {
            return makeImageView(onGoal ? golemEnGoalImg : jugadorImg);
        }
        return null;
    }

    private ImageView makeImageView(Image img) {
        ImageView iv = new ImageView(img);
        iv.setFitWidth(tileSize);
        iv.setFitHeight(tileSize); //Como hemos cambiado el nombre de la variable lo cambiamos aquí
        iv.setPreserveRatio(false);
        return iv;
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }
}
