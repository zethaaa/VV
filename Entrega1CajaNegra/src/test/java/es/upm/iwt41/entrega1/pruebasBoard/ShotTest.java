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
        board.setShot(newShot);
        board.update_shots();

        assertEquals( esperado, board.getShot().getY());

    }


    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasUpdateShotIncorrecto.csv",
            numLinesToSkip= 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void moverShotHaciaAlien(int x, int y, int esperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        board.setShot(newShot);

        int previous_deaths = board.getDeaths();
        board.update_shots();

        assertEquals(esperado, board.getShot().getY());
        assertEquals(previous_deaths+1, board.getDeaths());
        assertTrue(board.getAliens().get(14).isDying());

    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "194, 5, 0",
            "194, 6, 1"})
    void moverShotLimitesTablero(int x, int y, int esperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        board.setShot(newShot);


        board.update_shots();
        assertEquals(esperado, board.getShot().getY());
        assertTrue(board.getShot().isVisible());
    }

    @Test
    void moverShotFueralimites() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(194, 4);
        board.setShot(newShot);


        board.update_shots();

        assertEquals(3, board.getShot().getY());
        assertFalse(board.getShot().isVisible());
    }

}
