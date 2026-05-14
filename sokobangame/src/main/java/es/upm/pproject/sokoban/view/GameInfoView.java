package es.upm.pproject.sokoban.view;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class GameInfoView  extends HBox {
    private Label nivelLabel;
    private Label nombreNivelLabel;
    private Label puntuacionesNivelLabel;
    private Label puntuacionTotalLabel;

    public GameInfoView(String nombreNivel, int nivel, int puntuacionNivel, int puntuacionTotal){
        this.nivelLabel = new Label("Level: " + nivel);
        this.nombreNivelLabel = new Label("Level Name: " + nombreNivel);
        this.puntuacionesNivelLabel = new Label("Level Score: " + puntuacionNivel);
        this.puntuacionTotalLabel = new Label("Total Score: " + puntuacionTotal);
        crearVista();
    }
    private void crearVista(){
        this.setSpacing(10); // Espacio entre los elementos
        this.setAlignment(Pos.CENTER); // Centrar los elementos
        this.getChildren().addAll(nivelLabel, nombreNivelLabel, puntuacionesNivelLabel, puntuacionTotalLabel); // Agregar los labels al HBox
    }
    public void actualizarInfo(String nombreNivel, int nivel, int puntuacionNivel, int puntuacionTotal){
        this.nivelLabel.setText("Level: " + nivel);
        this.nombreNivelLabel.setText("Level Name: " + nombreNivel);
        this.puntuacionesNivelLabel.setText("Level Score: " + puntuacionNivel);
        this.puntuacionTotalLabel.setText("Total Score: " + puntuacionTotal);
    }

}
