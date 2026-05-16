package es.upm.pproject.sokoban.view;
import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView {
    private Stage stage; // Ventana principal
    private MenuController controller;
    public MainMenuView(Stage stage, MenuController controller) {
        this.stage = stage;
        this.controller = controller;
        crearVista();
    }
    private void crearVista(){
        Image tituloImg = new Image(
            getClass().getResource("/images/Titulo.png").toExternalForm()
        );
        ImageView tituloView = new ImageView(tituloImg);
        tituloView.setFitWidth(500);
        tituloView.setPreserveRatio(true);

        Button nuevoJuego = new Button("New Game");
        Button cargarPartida = new Button("Load Game");
        Button salir = new Button("Exit");

        nuevoJuego.setPrefWidth(350);
        nuevoJuego.setPrefHeight(45);
        cargarPartida.setPrefWidth(350);
        cargarPartida.setPrefHeight(45);
        salir.setPrefWidth(350);
        salir.setPrefHeight(45);

        //botones
        nuevoJuego.setOnAction(e -> {
            try { controller.iniciarJuego(); }
            catch (Exception ex) { ex.printStackTrace(); }
        });
        cargarPartida.setOnAction(e -> new SaveGameView(stage, controller));
        salir.setOnAction(e -> controller.cerrarApp());

        VBox layout = new VBox(20); // Contenedor vertical con 20 px de separación
        layout.setAlignment(Pos.CENTER);    // Centra los elementos dentro del VBox
        VBox.setMargin(tituloView, new Insets(0, 0, 40, 0));
        layout.getChildren().addAll(tituloView, nuevoJuego, cargarPartida, salir); // título y botones en el contenedor en ese orden

        Image fondo = new Image(
                getClass().getResource("/images/fondo.png").toExternalForm()
        );

        ImageView fondoView = new ImageView(fondo);
        fondoView.setFitWidth(800);
        fondoView.setFitHeight(600);

        StackPane root = new StackPane();
        root.getChildren().addAll(fondoView, layout);

        Scene scene = new Scene(root, 800, 600); // tam de la ventana
        stage.setTitle("Sokoban");
        stage.setScene(scene);                                   // mete la scene dentro de la ventana
        stage.show();
    }

}
