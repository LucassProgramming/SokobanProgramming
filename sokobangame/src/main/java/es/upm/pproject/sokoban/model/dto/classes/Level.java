package es.upm.pproject.sokoban.model.dto.classes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import es.upm.pproject.sokoban.model.dto.interfaces.ILevel;

public class Level implements ILevel, Serializable {

    private Square [][] capaInf;
    private Square [][] capaSup;
    private Score puntuacion;
    private int filas;
    private String nombre;
    private int columnas;
    private PlayableCharacter character;

public Level(String nombre,int filas,int columnas,Square[][] capaInf,
    Square[][] capaSup,Score puntuacion, PlayableCharacter character){

    this.nombre = nombre;
    this.filas = filas;
    this.columnas = columnas;
    this.capaInf = capaInf;
    this.capaSup = capaSup;
    this.puntuacion = puntuacion; //La puntuacion de este nivel
    this.character = character; //El personaje de este nivel


}
public Level(Square [][] capaInf, Square [][] capaSup){ //Constructor par los test de Character
    this.nombre = null;
    this.filas = 0;
    this.columnas = 0;
    this.capaInf = capaInf;
    this.capaSup = capaSup;
    this.puntuacion = null;
}
public Level(){ //Constructor par los test de Character
    this.nombre = null;
    this.filas = 0;
    this.columnas = 0;
    this.capaInf = new Square [3][3];
    this.capaSup = new Square [3][3];
    this.puntuacion = new Score();
}

@Override
public Square[][] getCapaInf() {
    return this.capaInf;
}

@Override
public Square[][] getCapaSup() {
    return this.capaSup;
}

@Override
public void setCapaInf(Square[][] capaInf) {
     this.capaInf = capaInf;
}

@Override
public void setCapaSup(Square[][] capaSup) {
    this.capaSup = capaSup;
}

@Override
public String getNombre() {
   return this.nombre;
}

@Override
public int getFilas() {
    return this.filas;
}

@Override
public int getColumnas() {
    return this.columnas;
}

@Override
public Score getPuntuacion() {
    return this.puntuacion;
}

@Override
public void setNombre(String nombre) {
    this.nombre = nombre;
}

@Override
public void setFilas(int filas) {
    this.filas = filas;
}

@Override
public void setColumnas(int columnas) {
    this.columnas = columnas;
}

@Override
public void setPuntuacion(Score puntuacion) {
    this.puntuacion = puntuacion;
}

@Override
public void incrementar() {
    puntuacion.incrementar();
    }
@Override
public void moverPersonaje(Direccion direccion) {
//Metodo para mover al personaje invocado por CurrentGameState necesita llamar a CharacterManager
CharacterManager.moverPersonaje(this,character, direccion); //Se pasa a si mismo para que 
// El personaje sepa por donde moverse
}
public PlayableCharacter getCharacter(){
    return this.character;
}
@Override
public boolean estaCompletado(){
    for(int i=0; i<filas; i++){
        for(int j=0; j<columnas; j++){
            if(capaInf[i][j] instanceof Goal && !(capaSup[i][j] instanceof Box)){
                return false;
            }
        }
    }
    return true;
}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Level level)) return false;
        return filas == level.filas && columnas == level.columnas && Objects.deepEquals(capaInf, level.capaInf) && Objects.deepEquals(capaSup, level.capaSup) && Objects.equals(puntuacion, level.puntuacion) && Objects.equals(nombre, level.nombre) && Objects.equals(character, level.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(capaInf), Arrays.deepHashCode(capaSup), puntuacion, filas, nombre, columnas, character);
    }
}
