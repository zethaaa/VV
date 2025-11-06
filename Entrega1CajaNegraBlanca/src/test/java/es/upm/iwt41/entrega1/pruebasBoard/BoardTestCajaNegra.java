package es.upm.iwt41.entrega1.pruebasBoard;
import main.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTestCajaNegra {

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
        assertFalse(board.getAliens().get(14).isDying());

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

    @Test
    void gameInit(){
        assertNotEquals(null,this.board.getPlayer());
        assertNotEquals(null,this.board.getShot());
        assertEquals(24,this.board.getAliens().size());
        assertEquals(18,this.board.getAliens().get(1).getX()-this.board.getAliens().get(0).getX());
        assertEquals(18,this.board.getAliens().get(6).getY()-this.board.getAliens().get(0).getY());
    }



    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "1, Game Over",
            "24, Game won!"})
    void pruebaUpdate(int deaths, String salida) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        board.setDeaths(deaths);

        int anterior = board.getAliens().get(0).getX();
        board.update();

        assertEquals(salida, board.getMessage());

        if(deaths == 1) {
            assertTrue(board.isInGame());
            assertNotEquals(anterior, board.getAliens().get(0).getX());
        }
        else {assertFalse(board.isInGame());
        assertEquals(anterior, board.getAliens().get(0).getX());}
    }



}
