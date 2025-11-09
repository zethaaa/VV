package es.upm.iwt41.entrega1.pruebasAlien;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class AlienTestCajaNegra {
    private Alien alienCorrectoNominal;
    private Alien alienCorrectoMax_x;
    private Alien alienCorrectoMin_x;


    @BeforeEach
    void setUp() {

        alienCorrectoNominal = new Alien(Commons.BOARD_WIDTH/2, Commons.BOARD_HEIGHT/2);
        alienCorrectoMax_x = new Alien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, Commons.BOARD_HEIGHT/2);
        alienCorrectoMin_x = new Alien(Commons.BORDER_LEFT , Commons.BOARD_HEIGHT);
    }

    @Test
    void moverAlienCorrectamenteDerecha() {
        int direccion = 1;
        int x_anterior = alienCorrectoNominal.getX();
        int y_anterior = alienCorrectoNominal.getY();
        alienCorrectoNominal.act(direccion);
        boolean resultado = x_anterior+1  == alienCorrectoNominal.getX() && y_anterior == alienCorrectoNominal.getY();
        assertTrue(resultado);
    }


    @Test
    void moverAlienCorrectamenteIzquierda() {
        int direccion = -1;
        int x_anterior = alienCorrectoNominal.getX();
        int y_anterior = alienCorrectoNominal.getY();
        alienCorrectoNominal.act(direccion);
        boolean resultado = x_anterior-1  == alienCorrectoNominal.getX() && y_anterior == alienCorrectoNominal.getY();
        assertTrue(resultado);
    }

    @Test
    void moverAlienDireccionCorrectaMax() {
        int direccion = 1;
        int x_anterior = alienCorrectoMax_x.getX();
        int y_anterior = alienCorrectoMax_x.getY();
        alienCorrectoMax_x.act(direccion);
        boolean resultado = x_anterior+1 == alienCorrectoMax_x.getX() && y_anterior == alienCorrectoNominal.getY();
        assertTrue(resultado);
    }

    @Test
    void moverAlienDireccionCorrectaMin() {
        int direccion = -1;
        int x_anterior = alienCorrectoMin_x.getX();
        int y_anterior = alienCorrectoMin_x.getY();
        alienCorrectoMin_x.act(direccion);
        boolean resultado = x_anterior-1 == alienCorrectoMin_x.getX() && y_anterior == alienCorrectoMin_x.getY();
        assertTrue(resultado);
    }

    @Test
    void noMoverAlienDireccion() {
        int direccion = 0;
        int x_anterior = alienCorrectoNominal.getX();
        int y_anterior = alienCorrectoNominal.getY();
        alienCorrectoNominal.act(direccion);
        boolean resultado = x_anterior == alienCorrectoNominal.getX() && y_anterior == alienCorrectoNominal.getY();
        assertTrue(resultado);
    }



    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasInitAlien.csv",
            numLinesToSkip= 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void initAlien(int x, int y, int esperado_x, int esperado_y) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Alien newAlien = new Alien(x, y);

        assertEquals(esperado_x, newAlien.getX());
        assertEquals(esperado_y, newAlien.getY());
    }


}
