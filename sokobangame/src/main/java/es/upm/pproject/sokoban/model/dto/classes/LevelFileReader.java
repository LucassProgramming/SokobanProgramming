package es.upm.pproject.sokoban.model.dto.classes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import es.upm.pproject.sokoban.model.exceptions.CajaNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalNotFoundInLevelException;
import es.upm.pproject.sokoban.model.exceptions.GoalsAndBoxesArentEqualsException;
import es.upm.pproject.sokoban.model.exceptions.LevelDoesntExistException;
import es.upm.pproject.sokoban.model.exceptions.PlayableCharacterNotFoundInLevelException;

public class LevelFileReader {

    private LevelFileReader(){}

    public static Level crearNivel(String archivo){
        PlayableCharacter caracter = null;
        ArrayList<String> lineas = new ArrayList<>();
        //Contadores para las distintas validaciones
        int numBoxes = 0;
        int numGoals = 0;
        int numPlayableCharacters = 0;

         //Le ponemos la / manualmente para que lea la ruta de menu controller bien.
        String ruta = archivo.startsWith("/") ? archivo : "/" + archivo;
        InputStream stream = LevelFileReader.class.getResourceAsStream(ruta);

        if (stream == null) {
            throw new LevelDoesntExistException(archivo);
        }

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(stream))) {

            String linea;

            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        
        } catch (Exception e){
            System.out.println("Error en lectura" + e.getMessage());

        }

        String nombre = lineas.get(0);
        String[] division = lineas.get(1).split("\\s+");

        int filas = Integer.parseInt(division[0]);
        int columnas = Integer.parseInt(division[1]);
        


        Square capaInf[][] = new Square[filas][columnas];
        Square capaSup[][] = new Square[filas][columnas];

        for(int i=0; i< filas; i++){
            String ComienzoNivelArchivo = lineas.get(2+i);
            for(int j=0; j< columnas;j++){
                char celda = j < ComienzoNivelArchivo.length() ? ComienzoNivelArchivo.charAt(j) : ' ';
                switch (celda) {
                    case '+':
                        capaInf[i][j] = new Wall(i,j);
                        break;

                    case '*':
                        capaInf[i][j] = new Goal(i,j);

                        
                        numGoals++;

                        break;
                    
                    case '#':
                        capaSup[i][j] = new Box(i, j);
                        capaInf[i][j] = new Square(i, j);


                        numBoxes++;

                        break;

                    case 'W':    
                        caracter = new PlayableCharacter(i, j);
                        capaSup[i][j] = caracter;
                        capaInf[i][j] = new Square(i, j);

                        numPlayableCharacters++;

                        break;
                
                    default:
                        capaInf[i][j] = new Square(i, j);
                        break;
                }

                }
            }
            // Condiciones mínimas del juego
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

            return new Level(nombre,filas,columnas,capaInf,capaSup,new Score(),caracter);
        }
        
    public static ArrayList<Level> cargarTodosLosNiveles(){
        ArrayList<Level> niveles = new ArrayList<>();
        int contador=1;

        while(true){
            String nombre_de_archivo = "/levels/Level_" + contador + ".txt";

        if(LevelFileReader.class.getResourceAsStream(nombre_de_archivo) == null){
            break;
        }

            niveles.add(crearNivel(nombre_de_archivo));
            contador++;
        }
            return niveles;
    }
}