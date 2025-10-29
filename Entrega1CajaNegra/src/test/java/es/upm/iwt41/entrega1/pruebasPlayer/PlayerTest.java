package es.upm.iwt41.entrega1.pruebasPlayer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import java.awt.TextField;
import java.awt.event.KeyEvent;

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



    @Test
    public void testKeyPressed() {
        // Instancia del objeto a testear

        // Creación del evento que simula pulsar la flecha derecha
        KeyEvent evento = new KeyEvent(
                new TextField(),              // componente origen del evento
                KeyEvent.KEY_PRESSED,         // tipo
                System.currentTimeMillis(),   // timestamp
                0,                            // modificadores
                KeyEvent.VK_RIGHT,            // código de la tecla
                KeyEvent.CHAR_UNDEFINED
        );

        // Afirmación
        assertEquals(2, player.getdx;


        // Creación del evento que simula pulsar la flecha izquierda
         evento = new KeyEvent(
                new TextField(),              // componente origen del evento
                KeyEvent.KEY_PRESSED,         // tipo
                System.currentTimeMillis(),   // timestamp
                0,                            // modificadores
                KeyEvent.VK_LEFT,            // código de la tecla
                KeyEvent.CHAR_UNDEFINED
        );

        // Afirmación
        assertEquals(-2, player.keyPressed(evento));

        // Creación del evento que simula pulsar la flecha izquierda
        evento = new KeyEvent(
                new TextField(),              // componente origen del evento
                KeyEvent.KEY_PRESSED,         // tipo
                System.currentTimeMillis(),   // timestamp
                0,                            // modificadores
                KeyEvent.VK_UP,            // código de la tecla
                KeyEvent.CHAR_UNDEFINED
        );

        // Afirmación
        assertEquals(0, player.keyPressed(evento));
    }
}

