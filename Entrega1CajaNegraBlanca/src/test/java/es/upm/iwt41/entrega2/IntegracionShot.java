package es.upm.iwt41.entrega2;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegracionShot {

    @Test
    public void constructorShot(){
        Shot shot  = new Shot(150, 150);
        assertNotNull(shot.getX());
        assertNotNull(shot.getY());
    }
}
