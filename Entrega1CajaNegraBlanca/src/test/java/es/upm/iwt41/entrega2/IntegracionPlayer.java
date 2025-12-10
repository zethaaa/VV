package es.upm.iwt41.entrega2;

import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracionPlayer {

    @Test
    void testInvocaInitPlayer() {

        Player player = new Player();

        //al llamar al constructor de player, se crea un objeto player con las posiciones x e y especificadas

        assertEquals(Commons.BOARD_WIDTH / 2,player.getX(),"El jugador debe estar centrado en el tablero");
        assertEquals(Commons.GROUND - 10,player.getY(),"El jugador debe estar 10px por encima del ground");

    }
}

