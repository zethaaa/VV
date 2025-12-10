package es.upm.iwt41.entrega2;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracionShot {

    @Test
    void testInvocaInitShot() {

        int xEntrada = 100;
        int yEntrada = 50;
        int xEsperado = xEntrada + 6;
        int yEsperado = yEntrada - 1;

        Shot shot = new Shot(xEntrada, yEntrada);

        assertEquals(xEsperado,shot.getX(),"La X del disparo debe sumar H_SPACE (6) a la entrada");

        assertEquals(yEsperado,shot.getY(),"La Y del disparo debe restar V_SPACE (1) a la entrada");

    }
}