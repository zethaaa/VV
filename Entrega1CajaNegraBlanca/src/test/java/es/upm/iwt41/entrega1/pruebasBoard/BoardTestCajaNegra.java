package es.upm.iwt41.entrega1.pruebasBoard;
import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;
import space_invaders.sprites.Sprite;

import java.lang.reflect.Method;
import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTestCajaNegra {

    private Alien alien;
    private List<Alien> aliens;
    private Board board;
    private Player player;
    private Method metodo;


    @BeforeEach
    void setUp() {
        try {
            board = new Board();

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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/pruebasupdateshotcorrecto.csv",
            numLinesToSkip = 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void updateShot(int x, int y, int esperadoY, int esperadoX) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        board.setShot(newShot);
        int previous_deaths = board.getDeaths();
        board.update_shots();

        assertEquals(esperadoY, board.getShot().getY());
        assertEquals(previous_deaths, board.getDeaths());
        assertEquals(esperadoX, board.getShot().getX());
        assertFalse(board.getAliens().get(14).isDying());

    }


    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources = "/pruebasUpdateShotIncorrecto.csv",
            numLinesToSkip = 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void updateShotColision(int x, int y, int esperadoY, int esperadoX) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        board.setShot(newShot);

        int previous_deaths = board.getDeaths();
        board.update_shots();

        assertEquals(esperadoY, board.getShot().getY());
        assertEquals(esperadoX, board.getShot().getX());
        assertEquals(previous_deaths + 1, board.getDeaths());
        assertTrue(board.getAliens().get(14).isDying());

    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "194, 5, 0, 200",
            "194, 6, 1, 200"})
    void updateShotLimitesTablero(int x, int y, int yEsperado, int xEsperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(x, y);
        board.setShot(newShot);

        board.update_shots();

        assertEquals(yEsperado, board.getShot().getY());
        assertEquals(xEsperado, board.getShot().getX());
        assertTrue(board.getShot().isVisible());
    }

    @Test
    void updateShotFueraLimites() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shot newShot = new Shot(194, 4);
        board.setShot(newShot);


        board.update_shots();

        assertEquals(3, board.getShot().getY());
        assertEquals(200, board.getShot().getX());
        assertFalse(board.getShot().isVisible());
    }

    @Test
    void gameInit() {
        assertNotEquals(null, this.board.getPlayer());
        assertNotEquals(null, this.board.getShot());
        assertEquals(24, this.board.getAliens().size());
        assertEquals(18, this.board.getAliens().get(1).getX() - this.board.getAliens().get(0).getX());
        assertEquals(18, this.board.getAliens().get(6).getY() - this.board.getAliens().get(0).getY());
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

        if (deaths == 1) {
            assertTrue(board.isInGame());
            assertNotEquals(anterior, board.getAliens().get(0).getX());
        } else {
            assertFalse(board.isInGame());
            assertEquals(anterior, board.getAliens().get(0).getX());
        }
    }


    @Test
    void probarUpdateAliens_TocarBordeDerecho() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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
    void probarUpdateAliens_TocarBordeIzquierdo() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "-1, 100, 303",
            "-1, 100, 302"}
            )

    void probarUpdateAliens_TocarBordeInferior(int direccion, int x, int y) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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

        Alien alienBordeInf = new Alien(x, y);
        listaAliens.add(alienBordeInf);

        Field campoInGame = Board.class.getDeclaredField("inGame");
        campoInGame.setAccessible(true);
        campoInGame.set(board, true);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, direccion);


        metodoUpdateAliens.invoke(board);

        boolean juegoTerminado = (boolean) campoInGame.get(board);
        Field campoMessage = Board.class.getDeclaredField("message");
        campoMessage.setAccessible(true);
        String mensaje = (String) campoMessage.get(board);

        System.out.println("Estado: " + juegoTerminado + "（esperado: false）");
        System.out.println("Mensaje: " + mensaje + "（esperado: Invasion!）");

        if(y == 303) assertTrue(!juegoTerminado && "Invasion!".equals(mensaje));
        if(y == 302) assertTrue(juegoTerminado && !"Invasion!".equals(mensaje));
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "1, 100, 150, 1, 150",
            "-1, 6, 100, -1, 100",
            "1, 327, 100, 1, 100"})
    void probarUpdateAliens_NoTocarBordes(int direccion, int x, int y, int direccionEsperada, int yEsperada) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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
        Alien alienNoBorde = new Alien(x, y);
        listaAliens.add(alienNoBorde);

        Field campoDirection = Board.class.getDeclaredField("direction");
        campoDirection.setAccessible(true);
        campoDirection.set(board, direccion);

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

        assertTrue(direccionEsperada == nuevaDirection && yEsperada == nuevaY);

    }

    @Test
    void update_bombCP1() {
        alien = new Alien(100, 100);
        alien.getBomb().setDestroyed(true);
        alien.die();
        aliens.add(alien);

        try {
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        boolean resultado = alien.getBomb().isDestroyed() == true;
        assertTrue(resultado);
    }

    @Test
    void update_bombCP2() {
        alien = new Alien(100, 100);
        Alien.Bomb bomba = this.alien.getBomb();
        bomba.setDestroyed(false);
        bomba.setY(110);
        aliens.add(alien);

        try {
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        assertFalse(bomba.isDestroyed());
        assertEquals(111, bomba.getY());

        System.out.println(player.isDying());
    }

    @Test
    void update_bombCP3() {
        alien = new Alien(100, 100);
        alien.getBomb().setDestroyed(true);
        aliens.add(alien);

        board.update_bomb_randomCustom(5);

        boolean resultado = alien.getBomb().isDestroyed() == true;
        assertTrue(resultado);
    }


    @Test
    void update_bombCP4() {
        alien = new Alien(100, 100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(true);
        aliens.add(alien);

        board.update_bomb_randomCustom(7);

        boolean resultado = bomba.isDestroyed() == false;
        assertTrue(resultado);
    }

    @Test
    void update_bombCP5 (){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        aliens.add(alien);
        bomba.setDestroyed(false);
        bomba.setY(285);
        bomba.setX(100);

        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }

        boolean resultado = bomba.isDestroyed() == true;
        assertTrue(resultado);
    }

    @Test
    void update_bombCP6(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(false);
        bomba.setY(280);
        bomba.setX(40);
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
    void update_bombCP7(){
        alien = new Alien(100,100);
        Alien.Bomb bomba = alien.getBomb();
        bomba.setDestroyed(false);
        bomba.setY(280);
        bomba.setX(40);
        aliens.add(alien);

        player.setX(35);
        player.setY(280);
        player.die();

        try{
            metodo.invoke(board);
        } catch (IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }

        boolean resultado = bomba.isDestroyed() == false && player.isDying() == false;
        assertTrue(resultado);
    }



}



