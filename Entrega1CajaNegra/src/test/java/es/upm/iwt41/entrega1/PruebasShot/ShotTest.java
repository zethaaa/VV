package es.upm.iwt41.entrega1.PruebasShot;

import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShotTest {
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    @Test
    void initShotCorrecto()
    {
        int x = Commons.BOARD_WIDTH/2;
        int y = Commons.BOARD_HEIGHT - Commons.GROUND;

        Shot shot = new Shot(x, y);

        boolean resultado = shot.getX() == x+H_SPACE  && shot.getY() == y-V_SPACE;
        assertTrue(resultado);
    }


    @Test
    void initShotCorrectoFueraLimitesX()
    {
        int x = -1;
        int y = 50;
        int H_SPACE = 6;
        int V_SPACE = 1;
        Shot shot = new Shot(x, y);

        boolean resultado = shot.getX() == x+H_SPACE  && shot.getY() == y-V_SPACE;
        assertTrue(resultado);
    }

    @Test
    void initShotCorrectoFueraLimitesY()
    {
        int x = 50;
        int y = -1;
        int H_SPACE = 6;
        int V_SPACE = 1;
        Shot shot = new Shot(x, y);

        boolean resultado = shot.getX() == x+H_SPACE  && shot.getY() == y-V_SPACE;
        assertTrue(resultado);
    }
}
