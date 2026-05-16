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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardView extends GridPane {

    private static final int TILE_SIZE = 64;

    private final Image sueloImg;
    private final Image muroImg;
    private final Image goalImg;
    private final Image cajaImg;
    private final Image cajaEnGoalImg;

    public BoardView(ILevel level) {
        sueloImg = loadImage("/images/suelo.jpg");
        muroImg = loadImage("/images/muro.jpg");
        goalImg = loadImage("/images/goal.jpg");
        cajaImg = loadImage("/images/caja.png");
        cajaEnGoalImg = loadImage("/images/cajaengoal.png");
        buildBoard(level);
    }

    public void actualizar(ILevel level) {
        getChildren().clear();
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

                add(cell, j, i);
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
            // TODO: reemplazar con makeImageView(jugadorImg) cuando se añada la imagen del jugador
            Rectangle r = new Rectangle(TILE_SIZE * 0.7, TILE_SIZE * 0.7);
            r.setFill(Color.DODGERBLUE);
            r.setArcWidth(TILE_SIZE * 0.3);
            r.setArcHeight(TILE_SIZE * 0.3);
            return r;
        }
        return null;
    }

    private ImageView makeImageView(Image img) {
        ImageView iv = new ImageView(img);
        iv.setFitWidth(TILE_SIZE);
        iv.setFitHeight(TILE_SIZE);
        iv.setPreserveRatio(false);
        return iv;
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }
}
