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
import space_invaders.sprites.AlienMock;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IntegracionBoard {

    @Mock
    private Player player;
    @Mock
    private Shot shot;


    private Board board;

    @Mock
    private List<Alien> aliensMocks;
    @Mock
    private Alien alien1;
    @Mock
    private Alien alien2;


    @BeforeEach
    public void setUp() {
         board = Mockito.spy(new Board());


        MockitoAnnotations.openMocks(this); // inicializa los mocks
    }


    @Test
    public void iniciarJuego() throws NoSuchFieldException, IllegalAccessException {
        Board board = Mockito.spy(new Board());

         ArrayList aliensMock = new ArrayList<AlienMock>();

        Field field = Board.class.getDeclaredField("aliens");
        field.setAccessible(true);
        field.set(board, aliensMock);

        assertEquals(24, aliensMock.size());


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

        //comprobar que no se llamó al método act y métodos update
        verify(player, never()).act();
        verify(board, never()).update_shots();
        verify(board, never()).update_aliens();
        verify(board, never()).update_bomb();

    }


    @Test
    public void updateAliens() throws NoSuchFieldException, IllegalAccessException {

        aliensMocks = new ArrayList<Alien>();
        aliensMocks.add(alien1);
        aliensMocks.add(alien2);

        Field field = Board.class.getDeclaredField("aliens");
        field.setAccessible(true);
        field.set(this.board, aliensMocks);

        this.board.update_aliens();
        verify(alien1).act(-1);




    }

}

