package es.upm.iwt41.entrega2;

import main.Board;
import main.Commons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class IntegracionBoard {

    @Mock
    private Player player;
    @Mock
    private Shot shot;
    @Mock
    private Alien alien;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // inicializa los mocks
    }


    @Test
    public void iniciarJuego(){

    }

    @Test
    public void updateJuego() throws NoSuchFieldException, IllegalAccessException {

        Board board = Mockito.spy(new Board());

        //Intercambiar el atributo player de Board por el objeto mock
        Field field = Board.class.getDeclaredField("player");
        field.setAccessible(true);
        field.set(board, player);

        //doNothing porque son void. Únicamente interesa comprobar que se ejecutan, no su comportamiento
        doNothing().when(board).update_shots();
        doNothing().when(board).update_aliens();
        doNothing().when(board).update_bomb();

        board.setDeaths(0);
        board.update();

        //comprobar que se llamó al método act y métodos update
        verify(player).act();
        verify(board).update_shots();
        verify(board).update_aliens();
        verify(board).update_bomb();

    }

    @Test
    public void updateJuegoFinalizar() throws NoSuchFieldException, IllegalAccessException {

        Board board = Mockito.spy(new Board());

        //Intercambiar el atributo player de Board por el objeto mock
        Field field = Board.class.getDeclaredField("player");
        field.setAccessible(true);
        field.set(board, player);

        //doNothing porque son void. Únicamente interesa comprobar que se ejecutan, no su comportamiento
        doNothing().when(board).update_shots();
        doNothing().when(board).update_aliens();
        doNothing().when(board).update_bomb();

        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        board.update();

        //comprobar que se llamó al método act y métodos update
        verify(player, never()).act();
        verify(board, never()).update_shots();
        verify(board, never()).update_aliens();
        verify(board, never()).update_bomb();

    }

}
