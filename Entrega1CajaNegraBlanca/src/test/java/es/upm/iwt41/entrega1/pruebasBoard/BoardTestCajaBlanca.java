package es.upm.iwt41.entrega1.pruebasBoard;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestCajaBlanca {

    private Board board;
    private Alien alien;
    private List<Alien> aliens;
    private Player player;
    private Method metodo;

    @BeforeEach
    void setUp() {
        board = new Board();
        try {
            Field aliensField = Board.class.getDeclaredField("aliens");
            aliensField.setAccessible(true);
            aliens = (List<Alien>) aliensField.get(board);

            Field playerField = Board.class.getDeclaredField("player");
            playerField.setAccessible(true);
            player = (Player) playerField.get(board);

            metodo = Board.class.getDeclaredMethod("update_bomb");
            metodo.setAccessible(true);

        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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




    @Test
    void pruebaUpdate_bombCP1(){
        alien = new Alien(40,280);
        Alien.Bomb bomba = alien.getBomb();
        aliens.clear();
        aliens.add(alien);
        player.setX(35);
        player.setY(280);

        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
        boolean resultado = bomba.isDestroyed() == true && player.isDying() == true;
        assertTrue(resultado);
    }


    @Test
    void pruebaUpdate_bombCP2(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(false);
        bomba.setY(Commons.GROUND-Commons.BOMB_HEIGHT+1);
        aliens.clear();
        aliens.add(alien);
        player.setX(35);
        player.setY(280);

        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
        boolean resultado = bomba.isDestroyed() == true && player.isDying() == false;
        assertTrue(resultado);

    }


    @Test
    void pruebaUpdate_bombCP3(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(false);
        bomba.setY(Commons.GROUND-Commons.BOMB_HEIGHT+1);
        aliens.clear();
        aliens.add(alien);
        player.setX(35);
        player.setY(280);
        player.die();

        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
        boolean resultado = bomba.isDestroyed() == true && player.isDying() == false;
        assertTrue(resultado);


    }


    @Test
    void pruebaUpdate_bombCP4(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(false);
        int valorY = bomba.getY();
        aliens.clear();
        aliens.add(alien);
        player.die();
        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
        boolean resultado = bomba.isDestroyed() == false && bomba.getY() == valorY+1;
        assertTrue(resultado);

    }


    @Test
    void pruebaUpdate_bombCP5(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        aliens.clear();
        aliens.add(alien);
        board.update_bomb_randomCustom(5);
        boolean resultado = bomba.isDestroyed() == true;
        assertTrue(resultado);

    }


    @Test
    void pruebaupdate_bombCP6(){
        aliens.clear();
        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }


















}
