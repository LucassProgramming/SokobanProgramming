package es.upm.pproject.sokoban.model.dto.classes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.upm.pproject.sokoban.model.exceptions.CajaNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalsAndBoxesArentEqualsException;
import es.upm.pproject.sokoban.model.exceptions.LevelDoesntExistException;
import es.upm.pproject.sokoban.model.exceptions.PlayableCharacterNotFoundInLevelException;

public class LevelFileReader {

    private static final Logger logger = LoggerFactory.getLogger(LevelFileReader.class);

    private LevelFileReader(){}

    public static Level crearNivel(String archivo) {

    String ruta = archivo.startsWith("/") ? archivo : "/" + archivo;
    InputStream stream = LevelFileReader.class.getResourceAsStream(ruta);

    if (stream == null) {
        throw new LevelDoesntExistException(archivo);
    }

    List<String> lineas = leerLineas(stream);

    String nombre = lineas.get(0);
    String[] division = lineas.get(1).split("\\s+");

    int filas = Integer.parseInt(division[0]);
    int columnas = Integer.parseInt(division[1]);

    Square[][] capaInf = new Square[filas][columnas];
    Square[][] capaSup = new Square[filas][columnas];

    DatosNivel datos = new DatosNivel();

    for (int i = 0; i < filas; i++) {
        String fila = lineas.get(i + 2);

        for (int j = 0; j < columnas; j++) {
            char celda = j < fila.length() ? fila.charAt(j) :' ';

            procesarCelda(celda, i, j, capaInf, capaSup, datos);
        }
    }

    validarNivel(nombre, datos.numGoals, datos.numBoxes, datos.numPlayableCharacters);

    return new Level(nombre, filas, columnas, capaInf, capaSup, new Score(), datos.caracter);
}
    public static List<Level> cargarTodosLosNiveles(){
        List<Level> niveles = new ArrayList<>();
        int contador=1;

        while(true){
            String nombreDeArchivo = "/levels/Level_" + contador + ".txt";

        if(LevelFileReader.class.getResourceAsStream(nombreDeArchivo) == null){
            break;
        }

            niveles.add(crearNivel(nombreDeArchivo));
            contador++;
        }
            return niveles;
    }

    private static List<String> leerLineas(InputStream stream) {

    List<String> lineas = new ArrayList<>();

    try (BufferedReader lector =
                 new BufferedReader(new InputStreamReader(stream))) {

        String linea;

        while ((linea = lector.readLine()) != null) {
            lineas.add(linea);
        }

    } catch (Exception e) {
        logger.info("Error en lectura {}", e.getMessage());
    }

    return lineas;
}

    private static void validarNivel(String nombre,int numGoals,int numBoxes, int numPlayableCharacters){
            
            if(numBoxes == 0){
                throw new CajaNotFoundInLevelException(nombre);
            }

            if(numGoals == 0){
                throw new GoalNotFoundInLevelException(nombre);
            }

            if (numPlayableCharacters != 1) {
                throw new PlayableCharacterNotFoundInLevelException(nombre);
            }

            if(numBoxes != numGoals){
                throw new GoalsAndBoxesArentEqualsException(nombre);
            }

    }

    private static void procesarCelda(char celda, int i, int j,Square[][] capaInf,Square[][] capaSup,DatosNivel datos){
        
        switch (celda) {

        case '+':
            capaInf[i][j] = new Wall(i, j);
            break;

        case '*':
            capaInf[i][j] = new Goal(i, j);
            datos.numGoals++;
            break;

        case '#':
            capaSup[i][j] = new Box(i, j);
            capaInf[i][j] = new Square(i, j);
            datos.numBoxes++;
            break;

        case 'W':
            datos.caracter = new PlayableCharacter(i, j);
            capaSup[i][j] = datos.caracter;
            capaInf[i][j] = new Square(i, j);
            datos.numPlayableCharacters++;
            break;

        default:
            capaInf[i][j] = new Square(i, j);
            break;
        }
    }

    private static class DatosNivel {

    int numBoxes;
    int numGoals;
    int numPlayableCharacters;
    PlayableCharacter caracter;
}

}