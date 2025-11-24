package es.upm.iwt41.entrega1.pruebasAlien;

import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Shot;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class AlienTestCajaNegra {

    @BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvFileSource(resources= "/pruebasActALien.csv",
            numLinesToSkip= 1,
            lineSeparator = "\n",
            delimiterString = ",")
    void actAlien(int direccion, int x, int y, int xEsperado, int yEsperado) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Alien newAlien = new Alien(x, y);
        newAlien.act(direccion);
        assertEquals(xEsperado, newAlien.getX());
        assertEquals(yEsperado, newAlien.getY());
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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "150, 150, 150, 150",
            "359, 150, 358, 150",
            "150, 351, 150, 350",
            "-1, 150, 0, 150",
            "150, -1, 150, 0"
    }
    )
    void initBomb(int x, int y, int xEsperado, int yEsperado) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Alien alien = new Alien(150, 150);

        Alien.Bomb bomb = alien.getBomb();

        bomb.initBomb(x,y);

        assertEquals(xEsperado, bomb.getX());
        assertEquals(yEsperado, bomb.getY());


    }


}
