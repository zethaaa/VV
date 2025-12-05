package es.upm.iwt41.entrega2;

import main.Commons;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class IntegracionAlien {

    @Test
    public void testInicializarAlienDentroLimites(){
        int x = Commons.BOARD_WIDTH /2;
        int y = Commons.BOARD_HEIGHT /2;

        Alien alien = new Alien(x, y);

        //al llamar al constructor de alien, se crea un objeto alien con las posiciones x e y especificadas
        assertEquals(x, alien.getX());
        assertEquals(y, alien.getY());
        //al llamar al constructor de alien, se crea un objeto bomba con las posiciones x e y especificadas
        assertNotNull(alien.getBomb());
        assertEquals(x, alien.getBomb().getX());
        assertEquals(y, alien.getBomb().getY());
    }

    /** No sé si estos son necesarios
    @Test
    public void testInicializarAlienFueraLimiteXSuperior(){
        int x = Commons.BOARD_WIDTH + 1;
        int y = Commons.BOARD_HEIGHT /2;

        Alien alien = new Alien(x, y);

        assertEquals(Commons.BOARD_WIDTH, alien.getX());
        assertEquals(y, alien.getY());
        assertNotNull(alien.getBomb());
        assertEquals(Commons.BOARD_WIDTH, alien.getBomb().getX());
        assertEquals(y, alien.getBomb().getY());
    }


    @Test
    public void testInicializarAlienFueraLimiteYSuperior(){
        int x = Commons.BOARD_WIDTH / 2;
        int y = Commons.BOARD_HEIGHT + 1;

        Alien alien = new Alien(x, y);

        assertEquals(x, alien.getX());
        assertEquals(Commons.BOARD_HEIGHT, alien.getY());
        assertNotNull(alien.getBomb());
        assertEquals(x, alien.getBomb().getX());
        assertEquals(Commons.BOARD_HEIGHT, alien.getBomb().getY());
    }
    */
}
