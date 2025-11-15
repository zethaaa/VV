package es.upm.iwt41.entrega1.pruebasBoard;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;
import space_invaders.sprites.Sprite;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestCajaBlanca {

    private Alien alien;
    private List<Alien> aliens;
    private Player player;
    private Method metodo;
    private Board board;

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


    /**
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
    void update_aliensPrimerCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        // para el metodo privado de update_aliens
        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);

        // para no dar error de player nulo
        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);

        // lista de aliens
        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();
        Alien alienBordeIzq = new Alien(Commons.BORDER_LEFT, 100);
        listaAliens.add(alienBordeIzq);

        // inicializar moviendose a izq y toca borde（-1）
        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, -1);


        metodoUpdateAliens.invoke(board);

        int nuevaDirection = (int) campoDirection.get(board);
        Field campoYAlien = Sprite.class.getDeclaredField("y");
        campoYAlien.setAccessible(true);
        int nuevaY = (int) campoYAlien.get(alienBordeIzq);
        Field campoXAlien = Sprite.class.getDeclaredField("x");
        campoXAlien.setAccessible(true);
        int nuevaX = (int) campoXAlien.get(alienBordeIzq);

        System.out.println("direccion: " + nuevaDirection + "（esperado: 1）");
        System.out.println("y: " + nuevaY + "（esperado: " + (100 + Commons.GO_DOWN) + "）");
        System.out.println("x: " + nuevaX + "（esperado: " + (Commons.BORDER_LEFT + 1) + "）");

        assertTrue(nuevaDirection == 1 && nuevaY == 100 + Commons.GO_DOWN);


    }
    @Test
    void update_aliensCuartoCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);


        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);


        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();

        int posXRight = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT;
        Alien alienBordeDer = new Alien(posXRight, 100);
        listaAliens.add(alienBordeDer);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, 1);

        metodoUpdateAliens.invoke(board);

        int nuevaDirection = (int) campoDirection.get(board);

        Field campoYAlien = Sprite.class.getDeclaredField("y");
        campoYAlien.setAccessible(true);
        int nuevaY = (int) campoYAlien.get(alienBordeDer);

        Field campoXAlien = Sprite.class.getDeclaredField("x");
        campoXAlien.setAccessible(true);
        int nuevaX = (int) campoXAlien.get(alienBordeDer);

        System.out.println("direccion: " + nuevaDirection + "（esperado: -1）");
        System.out.println("pos y: " + nuevaY + "（esperado: " + (100 + Commons.GO_DOWN) + "）");
        System.out.println("pos x: " + nuevaX + "（esperado: " + (posXRight - 1) + "）");

        assertTrue(-1 == nuevaDirection);
        assertEquals(100 + Commons.GO_DOWN, nuevaY);
        assertEquals(posXRight - 1, nuevaX); //se prueba? es responsabilidad de act
    }

    @Test
    void update_aliensTercerCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);

        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);

        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();
        Alien alienNoBorde = new Alien(100, 150);
        listaAliens.add(alienNoBorde);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, 1);

        metodoUpdateAliens.invoke(board);

        int nuevaDirection = (int) campoDirection.get(board);
        Field campoYAlien = Sprite.class.getDeclaredField("y");
        campoYAlien.setAccessible(true);
        int nuevaY = (int) campoYAlien.get(alienNoBorde);
        Field campoXAlien = Sprite.class.getDeclaredField("x");
        campoXAlien.setAccessible(true);
        int nuevaX = (int) campoXAlien.get(alienNoBorde);

        System.out.println("direccion: " + nuevaDirection + "（esperado: 1）");
        System.out.println("y: " + nuevaY + "（esperado: 150）");
        System.out.println("x: " + nuevaX + "（esperado: 101）");

        assertTrue(1 == nuevaDirection && 150 == nuevaY);

    }

    @Test
    void update_aliensSextoCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);

        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);

        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();

        Alien alienBordeInf = new Alien(100, 303);
        listaAliens.add(alienBordeInf);

        Field campoInGame = Board.class.getDeclaredField("inGame");
        campoInGame.setAccessible(true);
        campoInGame.set(board, true);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, -1);


        metodoUpdateAliens.invoke(board);

        boolean juegoTerminado = (boolean) campoInGame.get(board);
        Field campoMessage = Board.class.getDeclaredField("message");
        campoMessage.setAccessible(true);
        String mensaje = (String) campoMessage.get(board);

        System.out.println("Estado: " + juegoTerminado + "（esperado: false）");
        System.out.println("Mensaje: " + mensaje + "（esperado: Invasion!）");
        assertTrue(!juegoTerminado && "Invasion!".equals(mensaje));
    }

    @Test
    void update_aliensSegundoCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);

        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);

        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();
        Alien alienNoBorde = new Alien(100, 150);
        listaAliens.add(alienNoBorde);
        alienNoBorde.setVisible(false);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, 1);

        metodoUpdateAliens.invoke(board);

    }

    @Test
    void update_aliensQuintoCamino() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Method metodoUpdateAliens = Board.class.getDeclaredMethod("update_aliens");
        metodoUpdateAliens.setAccessible(true);

        Field campoPlayer = Board.class.getDeclaredField("player");
        campoPlayer.setAccessible(true);
        Player test = new Player();
        campoPlayer.set(board, test);

        Field campoAliens = Board.class.getDeclaredField("aliens");
        campoAliens.setAccessible(true);
        List<Alien> listaAliens = (List<Alien>) campoAliens.get(board);
        listaAliens.clear();

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, 1);

        metodoUpdateAliens.invoke(board);

    }

*/


    @Test
    void pruebaUpdate_bombCP1(){
        alien = new Alien(40,280);
        Alien.Bomb bomba = alien.getBomb();
        aliens.clear();
        aliens.add(alien);
        player.setX(35);
        player.setY(280);

        board.update_bomb_randomCustom(7);

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
        alien.setVisible(false);

        board.update_bomb_randomCustom(7);

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

        board.update_bomb_randomCustom(7);

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

        board.update_bomb_randomCustom(5);

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
