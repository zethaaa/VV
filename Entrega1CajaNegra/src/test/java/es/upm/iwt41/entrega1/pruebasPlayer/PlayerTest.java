package es.upm.iwt41.entrega1.pruebasPlayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;

public class PlayerTest
{
    Player player;
    @BeforeEach
    void setUp(){
        this.player = new Player();
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource(value = {
            "37",
            "39",
            "38"})
    void keyReleased(int e){
        int desplazamientoAnterior = player.get;

    }
}
