package es.upm.iwt41.entrega2;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegracionShot {

    final int V_SPACE = 1;
    final int H_SPACE = 6;

    @Test
    public void constructorShot(){
        Shot shot  = new Shot(150, 150);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(150 - V_SPACE, shot.getY());
    }
}
