package es.upm.pproject.sokoban.view;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class GameInfoView {
    private final HBox hbox;
    private Label nivelLabel;
    private Label nombreNivelLabel;
    private Label puntuacionesNivelLabel;
    private Label puntuacionTotalLabel;

    public GameInfoView(String nombreNivel, int nivel, int puntuacionNivel, int puntuacionTotal){
        hbox = new HBox();
        this.nivelLabel = new Label("Level: " + nivel);
        this.nombreNivelLabel = new Label("Level Name: " + nombreNivel);
        this.puntuacionesNivelLabel = new Label("Level Score: " + puntuacionNivel);
        this.puntuacionTotalLabel = new Label("Total Score: " + puntuacionTotal);
        crearVista();
    }

    public HBox getRoot() {
        return hbox;
    }

    private void crearVista(){
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(nivelLabel, nombreNivelLabel, puntuacionesNivelLabel, puntuacionTotalLabel);
    }

    public void actualizarInfo(String nombreNivel, int nivel, int puntuacionNivel, int puntuacionTotal){
        this.nivelLabel.setText("Level: " + nivel);
        this.nombreNivelLabel.setText("Level Name: " + nombreNivel);
        this.puntuacionesNivelLabel.setText("Level Score: " + puntuacionNivel);
        this.puntuacionTotalLabel.setText("Total Score: " + puntuacionTotal);
    }

}
