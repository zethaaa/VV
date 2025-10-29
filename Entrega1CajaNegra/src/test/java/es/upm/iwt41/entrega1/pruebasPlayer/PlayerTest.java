package es.upm.iwt41.entrega1.pruebasPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;

import java.awt.event.KeyEvent;
import java.awt.TextField;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerTest
{
    Player player;
    @BeforeEach
    void setUp(){
        this.player = new Player();
    }

   @Test
    void keyReleasedRight(){
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(keyEvent);
        assertEquals(0, player.getDx());
    }

    @Test
    void keyReleasedLeft(){
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        player.keyReleased(keyEvent);
        assertEquals(0, player.getDx());
    }

    @Test
    void keyReleasedUp(){
        KeyEvent keyEvent = new KeyEvent(new TextField(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        int dx_anterior = player.getDx();
        player.keyReleased(keyEvent);
        assertEquals(dx_anterior, player.getDx());
    }
}
