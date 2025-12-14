package es.upm.iwt41.entrega2;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegracionPlayer {


    @Test
    public void constructorPlayer(){
        Player player  = new Player();
        assertEquals(Commons.BOARD_WIDTH/2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
    }
}
