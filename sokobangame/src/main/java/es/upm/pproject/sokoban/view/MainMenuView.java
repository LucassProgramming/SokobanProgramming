package es.upm.pproject.sokoban.view;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView {
    private Stage stage; // Ventana principal
    public MainMenuView(Stage stage) {
        this.stage = stage;
        crearVista();
    }
    private void crearVista(){
        Label titulo = new Label("SOKOBAN");
        Button nuevoJuego = new Button("New Game");
        Button cargarPartida = new Button("Load Game");
        Button salir = new Button("Exit");

        VBox layout = new VBox(20); // Contenedor vertical con 20 px de separación
        layout.setAlignment(Pos.CENTER);    // Centra los elementos dentro del VBox
        layout.getChildren().addAll(titulo, nuevoJuego, cargarPartida, salir); // título y botones en el contenedor en ese orden

        Scene scene = new Scene(layout, 800, 600); // tam de la ventana
        stage.setTitle("Sokoban");
        stage.setScene(scene);                                   // mete la scene dentro de la ventana
        stage.show();
    }

}
