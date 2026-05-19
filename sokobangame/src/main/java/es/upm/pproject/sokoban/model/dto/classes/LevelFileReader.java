package es.upm.pproject.sokoban.model.dto.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LevelFileReader {

    public static Level CrearNivel(String archivo){
        PlayableCharacter caracter = null;
        ArrayList<String> lineas = new ArrayList<>();
        try(BufferedReader lector = new BufferedReader(new FileReader(archivo))){

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
                switch (ComienzoNivelArchivo.charAt(j)) {
                    case '+':
                        capaInf[i][j] = new Wall(i,j);
                        break;

                    case '*':
                        capaInf[i][j] = new Goal(i,j);
                        break;
                    
                    case '#':
                        capaSup[i][j] = new Box(null, i, j, 0);
                        capaInf[i][j] = new Square(i, j);
                        break;

                    case 'W':    
                        caracter = new PlayableCharacter(i, j);
                        capaSup[i][j] = caracter;
                        capaInf[i][j] = new Square(i, j);
                        break;
                
                    default:
                        capaInf[i][j] = new Square(i, j);
                        break;
                }

                }
            }
            return new Level(nombre,filas,columnas,capaInf,capaSup,new Score(),caracter);
        }
        
        
    }
