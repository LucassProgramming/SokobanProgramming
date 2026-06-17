package es.upm.pproject.sokoban.model.dto.classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevelRecorderTest {

    private Level level;

    @BeforeEach
    void setUp() {
        // Crear capas simples para el test
        Square[][] capaInf = new Square[3][3];
        Square[][] capaSup = new Square[3][3];

        // Inicializamos con null o mocks simples según tu implementación
        // Si Square es abstracta, usa subclases concretas

        Score puntuacion = new Score(); // Ajusta al constructor real
        puntuacion.setPuntuacion(0);
        PlayableCharacter character = new PlayableCharacter(0, 0); // Ajusta al constructor real
        level = new Level(
                "NivelTest",
                2,
                2,
                capaInf,
                capaSup,
                puntuacion,
                character
        );
        LevelRecorder.reiniciarDeque();
        LevelRecorder.setInicio(level);
    }

    @Test
    void testSaveAndUndo() {
        LevelRecorder.save(level);

        Level previous = LevelRecorder.undo();

        assertNotNull(previous);
        assertEquals(level.getNombre(), previous.getNombre());
        assertNotSame(level, previous); // Debe ser clon
    }

    @Test
    void LevelRecorderTestUndoEmptyStack() {
        Level result = LevelRecorder.undo();

        assertNull(result); //Debe dar null y no excepcion
    }

    @Test
    void LevelRecorderTestRestart() {
        LevelRecorder.setInicio(level);

        level.incrementar();
        
        Level restarted = LevelRecorder.restart();
       
        assertNotNull(restarted);
        assertEquals(level.getNombre(), restarted.getNombre());
        assertNotEquals(restarted.getPuntuacion().getPuntuacion(),
         level.getPuntuacion().getPuntuacion()); //La puntuacion del inicio debe ser cero
    }

    @Test
    void LevelRecordertestReiniciarStack() {
        LevelRecorder.save(level);

        LevelRecorder.reiniciarDeque();

        assertNull(LevelRecorder.undo());
    }

    @Test
    void LevelRecorderTestDiferenteReferencia() {
        LevelRecorder.save(level);

        Level cloned = LevelRecorder.undo();

        assertNotSame(level, cloned);
        assertNotSame(level.getCapaSup(), cloned.getCapaSup());
    }
    @Test
    void LevelRecorderTestMovimientoReiniciado(){
        level.getCapaSup()[0][0] = level.getCharacter();
        LevelRecorder.setInicio(level);

        CharacterManager.moverPersonaje(level, level.getCharacter(), new Direccion(0, 1));
        CharacterManager.moverPersonaje(level, level.getCharacter(), new Direccion(1, 0));

        Level restarted = LevelRecorder.restart();

        assertEquals(restarted.getCapaSup()[0][0].getClass(), restarted.getCharacter().getClass());
        assertEquals(level.getCapaSup()[1][1], level.getCharacter());

    }

    @Test
    void LevelRecorderTestMovimientoUndo(){
        level.getCapaSup()[0][0] = level.getCharacter();
        LevelRecorder.save(level);

        CharacterManager.moverPersonaje(level, level.getCharacter(), new Direccion(0, 1));
        CharacterManager.moverPersonaje(level, level.getCharacter(), new Direccion(1, 0));

        Level undoed = LevelRecorder.undo();

        assertEquals(undoed.getCapaSup()[0][1].getClass(), undoed.getCharacter().getClass()); //Solo debe haber retrocedido un movimiento
        assertEquals(level.getCapaSup()[1][1], level.getCharacter());

    }

    @Test
    void getYSetEstadoNivelPermitenSobrescribirDeque() {
        // Preparamos un deque personalizado y lo establecemos
        java.util.Deque<Level> pila = new java.util.ArrayDeque<>();
        Level other = new Level("Otra", 1, 1, new Square[1][1], new Square[1][1], new Score(), new PlayableCharacter(0,0));
        pila.push(other);
        LevelRecorder.setEstadoNivel(pila);

        // getEstadoNivel debe devolver exactamente la misma instancia
        assertSame(pila, LevelRecorder.getEstadoNivel());
        // undo debe devolver el elemento que pusimos
        Level popped = LevelRecorder.undo();
        assertNotNull(popped);
        assertEquals("Otra", popped.getNombre());
    }

    @Test
    void restartDevuelveNullSiInicioEsNullYLimpiaDeque() {
        // guardamos un estado para que haya algo en la pila
        LevelRecorder.save(level);
        // forzamos inicio a null
        LevelRecorder.setInicio(null);

        Level r = LevelRecorder.restart();
        assertNull(r);

        // restart debe haber reiniciado la pila internamente
        assertNull(LevelRecorder.undo());
    }

    @Test
    void setInicioHaceCopiaProfundaDeLevel() {
        // Modificamos el nivel original tras setInicio y comprobamos que inicio no cambia
        LevelRecorder.setInicio(level);
        Level inicio = LevelRecorder.getInicio();
        assertNotNull(inicio);
        assertNotSame(level, inicio);
        // modificar la capa superior del original no debe alterar la del inicio (clon profundo)
        level.getCapaSup()[0][0] = new Wall(0,0);
        // Si la clonación fue profunda, inicio.capaSup[0][0] debe seguir siendo null o PlayableCharacter
        boolean different = inicio.getCapaSup()[0][0] != level.getCapaSup()[0][0];
        assertTrue(different);
    }

    @Test
    void saveMultipleYUndoSiguenOrdenLIFO() {
        Level a = new Level("A",1,1,new Square[1][1], new Square[1][1], new Score(), new PlayableCharacter(0,0));
        Level b = new Level("B",1,1,new Square[1][1], new Square[1][1], new Score(), new PlayableCharacter(0,0));
        LevelRecorder.reiniciarDeque();
        LevelRecorder.save(a);
        LevelRecorder.save(b);

        Level firstUndo = LevelRecorder.undo();
        assertEquals("B", firstUndo.getNombre());
        Level secondUndo = LevelRecorder.undo();
        assertEquals("A", secondUndo.getNombre());
    }
}