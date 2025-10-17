package org.example;

import main.Commons;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp(){


    }

    @AfterEach
    void tearDown(){

    }


    @Test
    void updatealiens(){

        int posXalien = 10;
        int posYalien = 200;
        Alien alien = new Alien(posXalien,posYalien);

        Boolean res = alien.getX() > Commons.BORDER_LEFT && alien.getX()<Commons.BORDER_RIGHT;

        assertTrue(res);

    }
}