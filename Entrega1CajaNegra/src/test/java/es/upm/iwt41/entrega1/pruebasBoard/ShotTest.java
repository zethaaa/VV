package es.upm.iwt41.entrega1.pruebasBoard;
import main.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;


public class ShotTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasupdateshotcorrecto.csv",
    numLinesToSkip= 1,
    lineSeparator = "\n",
    delimiterString = ",")
    void moverShotSinAlien(int x, int y, int esperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        Field shotField = Board.class.getDeclaredField("shot");
        shotField.setAccessible(true);
        shotField.set(board, newShot);

        var method = Board.class.getDeclaredMethod("update_shots");
        method.setAccessible(true);
        method.invoke(board);

        assertEquals( esperado, board.getShot().getY());

    }


    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasUpdateShotIncorrecto.csv",
            numLinesToSkip= 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void moverShotHaciaAlien(int x, int y, int esperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y); //posición real: 192, 41
        Field shotField = Board.class.getDeclaredField("shot");
        shotField.setAccessible(true);
        shotField.set(board, newShot);


        var method = Board.class.getDeclaredMethod("update_shots");
        method.setAccessible(true);
        method.invoke(board);

        assertEquals(esperado, board.getShot().getY());
        assertFalse(board.getShot().isVisible());
        assertTrue(board.getAliens().get(14).isDying());
        //contador deaths no accesible
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "194, 5, 0",
            "194, 6, 1"})
    void moverShotLimitesTablero(int x, int y, int esperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        Field shotField = Board.class.getDeclaredField("shot");
        shotField.setAccessible(true);
        shotField.set(board, newShot);


        var method = Board.class.getDeclaredMethod("update_shots");
        method.setAccessible(true);
        method.invoke(board);

        assertEquals(esperado, board.getShot().getY());
        assertTrue(board.getShot().isVisible());
    }

    @Test
    void moverShotFueralimites() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(194, 4);
        Field shotField = Board.class.getDeclaredField("shot");
        shotField.setAccessible(true);
        shotField.set(board, newShot);


        var method = Board.class.getDeclaredMethod("update_shots");
        method.setAccessible(true);
        method.invoke(board);

        assertEquals(3, board.getShot().getY());
        assertFalse(board.getShot().isVisible());
    }

}
