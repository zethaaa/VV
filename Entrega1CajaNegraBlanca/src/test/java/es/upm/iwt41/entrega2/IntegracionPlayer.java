package es.upm.iwt41.entrega2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegracionPlayer {


    @Test
    public void constructorPlayer(){
        Player player  = new Player();
        assertNotNull(player.getX());
        assertNotNull(player.getY());
    }
}
