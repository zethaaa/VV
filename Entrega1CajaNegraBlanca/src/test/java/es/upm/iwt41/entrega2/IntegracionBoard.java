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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IntegracionBoard {

    @Mock
    private Player player;
    @Mock
    private Shot shot;

    private Board board;

    @Mock
    private List<Alien> aliensMocks;


    @BeforeEach
    public void setUp() {
        board = Mockito.spy(new Board());
        MockitoAnnotations.openMocks(this); // inicializa los mocks
    }


    @Test
    public void iniciarJuego() throws NoSuchFieldException, IllegalAccessException {

        board = Mockito.spy(new Board());

        assertAll(
            () -> assertEquals(
                    Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS,
                    board.getAliens().size()),
            () -> assertNotNull(board.getPlayer()),
            () -> assertNotNull(board.getShot())
        );

    }

    @Test
    public void updateJuego() throws NoSuchFieldException, IllegalAccessException {

        board = Mockito.spy(new Board());

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

        board = Mockito.spy(new Board());

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
        aliensMocks = new ArrayList<>();

        //sustituir el campo aliens de la clase board por una nueva lista de aliens de tipo mock
        Field field = Board.class.getDeclaredField("aliens");
        field.setAccessible(true);
        field.set(board, aliensMocks);

        for(int i  = 0; i < Commons.NUMBER_OF_ALIENS_TO_DESTROY; i++){
            //crear 24 aliens de tipo mock y establecer un valor esperado para su coordenada en X y su visibilidad
            Alien alienMock = mock(Alien.class);
            aliensMocks.add(alienMock);
            when(alienMock.getX()).thenReturn(150);
            when(alienMock.isVisible()).thenReturn(true);
        }

        board.update_aliens();
        //se esperan 24 llamadas a act
        aliensMocks.forEach(alien -> verify(alien).act(-1));

    }


    @Test
    public void updateAliensNoDesplazamiento() throws NoSuchFieldException, IllegalAccessException {
        aliensMocks = new ArrayList<>();

        //sustituir el campo aliens de la clase board por una nueva lista de aliens de tipo mock
        Field field = Board.class.getDeclaredField("aliens");
        field.setAccessible(true);
        field.set(board, aliensMocks);

        for(int i  = 0; i < Commons.NUMBER_OF_ALIENS_TO_DESTROY; i++){
            //crear 24 aliens de tipo mock y establecer un valor esperado para su coordenada en X y su visibilidad
            Alien alienMock = mock(Alien.class);
            aliensMocks.add(alienMock);
            when(alienMock.getX()).thenReturn(150);
            //no son visibles, por lo que no se debe llamar a act()
            when(alienMock.isVisible()).thenReturn(false);
        }

        board.update_aliens();
        //se esperan 0 llamadas a act
        aliensMocks.forEach(alien -> verify(alien, never()).act(-1));

    }

}

