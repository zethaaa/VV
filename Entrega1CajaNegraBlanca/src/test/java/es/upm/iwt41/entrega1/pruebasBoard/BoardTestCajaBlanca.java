package es.upm.iwt41.entrega1.pruebasBoard;

import main.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestCajaBlanca {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }


    @Test
    void moverShotCaminoBase(){
        Shot newShot = new Shot(186, 48);
        board.setShot(newShot);
        int previous_deaths = board.getDeaths();



        board.update_shots();
        assertEquals(43, board.getShot().getY());
        assertTrue(board.getAliens().get(14).isDying());
        assertEquals(previous_deaths+1, board.getDeaths());

    }

    @Test
    void moverShotSegundoCamino(){
        Shot newShot = new Shot(194, 56);
        board.setShot(newShot);
        board.getShot().setVisible(false);


        board.update_shots();
        assertEquals(55, board.getShot().getY());

    }

    @Test
    void moverShotTercerCamino(){
        Shot newShot = new Shot(196, 56);
        board.setShot(newShot);
        int previous_deaths = board.getDeaths();


        board.update_shots();
        assertEquals(51, board.getShot().getY());
        assertFalse(board.getAliens().get(14).isDying());
        assertEquals(previous_deaths, board.getDeaths());

    }

    @Test
    void moverShotCuartoCamino(){
        Shot newShot = new Shot(196, 4);
        board.setShot(newShot);


        board.update_shots();
        assertEquals(3, board.getShot().getY());
        assertFalse(board.getShot().isVisible());
    }

    @Test
    void moverShotQuintoCamino(){
        Shot newShot = new Shot(186, 48);
        board.setShot(newShot);
        int previous_deaths = board.getDeaths();

        board.getAliens().forEach(alien -> alien.setVisible(false));

        board.update_shots();
        assertEquals(43, board.getShot().getY());
        assertFalse(board.getAliens().get(14).isDying());
        assertEquals(previous_deaths, board.getDeaths());
    }

    @Test
    void moverShotSextoCamino(){
        Shot newShot = new Shot(186, 48);
        board.setShot(newShot);
        int previous_deaths = board.getDeaths();

        board.getAliens().clear();

        board.update_shots();
        assertEquals(43, board.getShot().getY());
        assertEquals(previous_deaths, board.getDeaths());
    }


    @Test
    void gameInitPrimerCamino(){
        this.board = new Board();
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
    void pruebaUpdatePrimerSegundoCamino(int deaths, String salida) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
