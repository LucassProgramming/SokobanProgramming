package es.upm.pproject.sokoban.view;
import es.upm.pproject.sokoban.controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SaveGameView {
    private Stage stage;
    private int selectedSlot = -1; // -1 indica que no hay slot seleccionado
    private Button slot1;
    private Button slot2;
    private Button slot3;
    private MenuController controller;
    private boolean modoGuardar;
    public SaveGameView(Stage stage, MenuController controller, boolean modoGuardar) {
        this.stage = stage;
        this.controller = controller;
        this.modoGuardar = modoGuardar;
        crearVista();
    }
    private void crearVista(){
        Label titulo =  new Label("GUARDAR / CARGAR PARTIDA");
        titulo.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");

        slot1 = crearBotonSlot(1);
        slot2 = crearBotonSlot(2);
        slot3 = crearBotonSlot(3);

        Button guardar = crearBotonAccion("SAVE GAME");
        Button cargar = crearBotonAccion("LOAD GAME");
        Button volver = crearBotonAccion("BACK TO MAIN MENU");

        guardar.setVisible(modoGuardar);
        guardar.setManaged(modoGuardar);

        cargar.setVisible(!modoGuardar);
        cargar.setManaged(!modoGuardar);
        


        // Acciones de los botones
        slot1.setOnAction(e -> seleccionarSlot(1));
        slot2.setOnAction(e -> seleccionarSlot(2));
        slot3.setOnAction(e -> seleccionarSlot(3));
        guardar.setOnAction(e -> {
            if (selectedSlot != -1) {
                controller.guardarPartida(selectedSlot);
                actualizarSlots(); // Actualiza la vista de los slots para reflejar el nuevo estado
            }
        });
        cargar.setOnAction(e -> {
            if (selectedSlot != -1) {
                controller.cargarPartida(selectedSlot);
            }
        });
        volver.setOnAction(e -> new MainMenuView(stage, controller));

        VBox layout = new VBox(18); // Contenedor vertical con 20 px de separación
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setMinWidth(650);
        layout.setStyle("-fx-background-color: #2b2b2b;" + "-fx-border-color: #8B5A2B;" + "-fx-border-width: 6;");
        layout.getChildren().addAll(titulo, slot1, slot2, slot3, guardar, cargar, volver);

        Image fondo = new Image(getClass().getResource("/images/fondopantalladecarga.png").toExternalForm());
        ImageView fondoView = new ImageView(fondo);
        fondoView.setFitWidth(800);
        fondoView.setFitHeight(600);
        layout.setStyle("-fx-background-color: rgba(20,20,20,0.75);"+"-fx-border-color: #8B5A2B;" + "-fx-border-width: 6;");
        StackPane root = new StackPane();
        root.getChildren().addAll(fondoView, layout);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    /* Selecciona un slot y actualiza su estilo */
    private void seleccionarSlot(int slot){
        selectedSlot = slot;
        resetearSlots(); // Resetea el estilo de todos los slots
        if(slot == 1){
            slot1.setStyle(estiloSeleccionado());
        }else if(slot == 2){
            slot2.setStyle(estiloSeleccionado());
        }else if(slot == 3){
            slot3.setStyle(estiloSeleccionado());
        }
    }
    /* Resetea el estilo de los botones de slot a su estado normal.*/
    private void resetearSlots(){
        slot1.setStyle(estiloNormal());
        slot2.setStyle(estiloNormal());
        slot3.setStyle(estiloNormal());
    }
    private String estiloNormal(){
        return "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #3a3a3a; -fx-text-fill: white;" + "-fx-border-color: #777777; -fx-border-width: 3;";
    }
    private String estiloSeleccionado(){
        return "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;" + "-fx-border-color: gold; -fx-border-width: 4;";
    }
    /* Crea un botón para slots 
    * Si el slot tiene una partida guardada, se muestra un icono de cofre.
    * Si está vacío no se muestra el icono.
    */
    private Button crearBotonSlot(int slot){
        boolean existe = controller.existeSlot(slot);
        String texto;
        if (existe){
            texto = "SLOT " + slot + " - OCUPADO";
        } else {
            texto = "SLOT " + slot + " - VACÍO";
        }

        Button boton = new Button(texto);

        if (existe){
            Image chestImg = new Image(getClass().getResource("/images/cofre.png").toExternalForm());

            ImageView chestView = new ImageView(chestImg);
            chestView.setFitWidth(64);
            chestView.setFitHeight(64);

            boton.setGraphic(chestView);
            boton.setContentDisplay(ContentDisplay.RIGHT);
            boton.setGraphicTextGap(20);
        }
        boton.setPrefWidth(550);
        boton.setPrefHeight(85);
        boton.setStyle(estiloNormal());

        return boton;
    }
    /* Crea un botón para acciones principales */
    private Button crearBotonAccion(String texto) {
        Button boton = new Button(texto);
        boton.setPrefWidth(300);
        boton.setPrefHeight(45);
        boton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; ");
        return boton;
    }
    /*
    * Actualiza el texto y el icono de los botones de slot según si el slot tiene una partida guardada o no.
    */
    private void actualizarSlots(){
        slot1.setText(controller.existeSlot(1) ? "SLOT 1 - PARTIDA GUARDADA" : "SLOT 1 - VACÍO");
        slot2.setText(controller.existeSlot(2) ? "SLOT 2 - PARTIDA GUARDADA" : "SLOT 2 - VACÍO");
        slot3.setText(controller.existeSlot(3) ? "SLOT 3 - PARTIDA GUARDADA" : "SLOT 3 - VACÍO");
        actualizarIconoSlot(slot1, 1);
        actualizarIconoSlot(slot2, 2);
        actualizarIconoSlot(slot3, 3);
    }
    /*
    * Añade o elimina el icono del cofre dependiendo de si el slot contiene una partida
    */
    private void actualizarIconoSlot(Button slotButton, int slot){
        if(controller.existeSlot(slot)){
            ImageView chestView = new ImageView(new Image(getClass().getResource("/images/cofre.png").toExternalForm()));
            chestView.setFitWidth(64);
            chestView.setFitHeight(64);
            slotButton.setGraphic(chestView);
            slotButton.setContentDisplay(ContentDisplay.RIGHT);
            slotButton.setGraphicTextGap(20);
        }else{
            // Si no existe el slot, se quita el icono
            slotButton.setGraphic(null);
        }
    }


}
