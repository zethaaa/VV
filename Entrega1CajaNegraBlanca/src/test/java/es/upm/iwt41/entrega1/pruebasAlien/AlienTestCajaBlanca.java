package es.upm.iwt41.entrega1.pruebasAlien;

import main.Commons;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlienTestCajaBlanca {

    Alien alien;
    @Test
    void probarinitAlienCP1(){
        int valorX = 359;
        int valorY = 351;
        alien = new Alien(valorX,valorY);
        boolean resultado = alien.getX() == Commons.BOARD_WIDTH  && alien.getY() == Commons.BOARD_HEIGHT;
        assertTrue(resultado);
    }

    @Test
    void probarinitAlienCP2(){
        int valorX = -1;
        int valorY = 351;
        alien = new Alien(valorX,valorY);
        boolean resultado = alien.getX() == 0  && alien.getY() == valorY;
        assertTrue(resultado);
    }


    @Test
    void probarinitAlienCP3(){
        int valorX = 100;
        int valorY = 351;
        alien = new Alien(valorX,valorY);
        boolean resultado = alien.getX() == valorX  && alien.getY() == Commons.BOARD_HEIGHT;
        assertTrue(resultado);
    }
    ;
    @Test
    void probarinitAlienCP4(){
        int valorX = 100;
        int valorY = -1;
        alien = new Alien(valorX,valorY);
        boolean resultado = alien.getX() == valorX  && alien.getY() == 0;
        assertTrue(resultado);
    }

    @Test
    void probarinitAlienCP5(){
        int valorX = 100;
        int valorY = 100;
        alien = new Alien(valorX,valorY);
        boolean resultado = alien.getX() == valorX  && alien.getY() == valorY;
        assertTrue(resultado);
    }


}
