package es.upm.iwt41.entrega2;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class IntegracionAlien {


    Alien alien;
    @BeforeEach
    public void setUp() {
        alien = new Alien(150, 150);
    }

    @Test
    public void constructorAlien(){
        assertEquals(150, alien.getX());
        assertEquals(150, alien.getY());
        assertEquals(150, alien.getBomb().getY());
        assertEquals(150, alien.getBomb().getX());
    }
}
