package es.upm.iwt41.entrega1.PruebasShot;

import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShotTest {
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;


    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasInitShot.csv",
            numLinesToSkip= 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void construirShotCorrecto(int x, int y)
    {
        Shot shot = new Shot(x, y);

        boolean resultado = shot.getX() == x+H_SPACE  && shot.getY() == y-V_SPACE;
        assertTrue(resultado);
    }

}
