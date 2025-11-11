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
import java.util.List;

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

        assertTrue(nuevaDirection == -1 && nuevaY == 100 + Commons.GO_DOWN && nuevaX == posXRight - 1);
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

        assertTrue(nuevaDirection == 1 && nuevaY == 100 + Commons.GO_DOWN && nuevaX == Commons.BORDER_LEFT + 1);


    }


    @Test
    void probarUpdateAliens_TocarBordeInferior() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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
        int lineaInferior = Commons.GROUND + Commons.ALIEN_HEIGHT;
        Alien alienBordeInf = new Alien(100, lineaInferior);
        listaAliens.add(alienBordeInf);

        Field campoInGame = Board.class.getDeclaredField("inGame");
        campoInGame.setAccessible(true);
        campoInGame.set(board, true);

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
    void probarUpdateAliens_NoTocarBordes() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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

        assertTrue(nuevaDirection == 1 && nuevaY == 150 && nuevaX == 100 + 1);

    }

}




