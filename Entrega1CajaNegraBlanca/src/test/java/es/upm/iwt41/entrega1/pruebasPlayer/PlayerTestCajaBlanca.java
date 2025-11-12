package es.upm.iwt41.entrega1.pruebasPlayer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerTestCajaBlanca {
    Player player;


    @BeforeEach
    void setUp() {
        this.player = new Player();
    }


    /**
    @Test
    void pruebaActPrimerCamino() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        player.setX(2);
        KeyEvent evento = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(evento);
        player.act();
        assertEquals(2, player.getX());


    }


    @Test
    void pruebaActSegundoCamino() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        player.setX(6);
        KeyEvent evento = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(evento);
        player.act();
        assertEquals(8, player.getX());
    }


    @Test
    void pruebaActTercerCamino() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        player.setX(356);
        KeyEvent evento = new KeyEvent(new TextField(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyPressed(evento);
        player.act();
        assertEquals(356, player.getX());
    }
*/
    @Test
    void keyReleasedPrimerCamino() {
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(keyEvent);
        assertEquals(0, player.getDx());
    }

    @Test
    void keyReleasedSegundoCamino() {
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(keyEvent);
        assertEquals(0, player.getDx());
    }

    @Test
    void keyReleasedTercerCamino() {
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        int dx_anterior = player.getDx();
        player.keyReleased(keyEvent);
        assertEquals(dx_anterior, player.getDx());
    }
}
