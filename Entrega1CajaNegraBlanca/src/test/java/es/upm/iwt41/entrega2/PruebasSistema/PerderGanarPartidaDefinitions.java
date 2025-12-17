package es.upm.iwt41.entrega2.PruebasSistema;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.Board;
import main.Commons;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PerderGanarPartidaDefinitions {

    private Board board;


    @Given("Dado el tablero del juego Space Invaders")
    public void dado_el_tablero_del_juego_space_invaders() {
        board = new Board();
        assertNotNull(board);
        assertNotNull(board.getPlayer());
        assertNotNull(board.getAliens());
        assertEquals(24, board.getAliens().size());
    }


    @When("una bomba alcanza la posicion del jugador")
    public void una_bomba_alcanza_la_posicion_del_jugador() {
        Alien alien = board.getAliens().get(0);
        Player player = board.getPlayer();
        alien.getBomb().setDestroyed(false);
        alien.getBomb().setX(player.getX());
        alien.getBomb().setY(player.getY());

        board.update_bomb();

        assertEquals(alien.getBomb().getX(), player.getX());
        assertEquals(alien.getBomb().getY(), player.getY());
    }

    @Then("muere la nave del jugador")
    public void muere_la_nave_del_jugador() {
        assertTrue(board.getPlayer().isDying());
    }

    @And("se finaliza la partida")
    public void se_finaliza_la_partida() {
        assertFalse(board.isInGame());
    }

    @And("se muestra el mensaje {string}")
    public void se_muestra_el_mensaje(String string) {
        assertEquals(string, board.getMessage());
    }

    @When("un alien alcanza el límite inferior del tablero de juego")
    public void un_alien_alcanza_el_limite_tablero_de_juego() {
        Alien alien = board.getAliens().get(0);
        alien.setY(Commons.GROUND + Commons.ALIEN_HEIGHT+1);
        alien.setX(150);

        assertEquals(Commons.GROUND + Commons.ALIEN_HEIGHT+1, alien.getY());
        assertTrue(board.isInGame());

        board.update_aliens();

    }

    @When("no se cumple ninguna de las condiciones de derrota")
        public void no_se_cumple_ninguna_de_las_condiciones_de_derrota(){
        for(Alien alien : board.getAliens()){
            alien.setY(150);
            alien.getBomb().setY(150);
            assertEquals(150, alien.getY());
            assertEquals(150, alien.getBomb().getY());
        }
    }

    @Then("la nave del jugador no muere")
    public void la_nave_del_jugador_no_muere() {
        assertFalse(board.getPlayer().isDying());
    }

    @And("continua la partida")
    public void continua_la_partida() {
        assertTrue(board.isInGame());
    }

    @When("se eliminan todos los aliens")
    public void se_eliminan_todos_los_aliens() {

        for(Alien alien : board.getAliens()){
            board.getShot().setX(alien.getX());
            board.getShot().setY(alien.getY());
            board.update_shots();
        }

        assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY, board.getDeaths());

        board.update();
    }

    @When("Queda al menos un alien en el tablero")
    public void queda_al_menos_un_alien_en_el_tablero() {

        for(int i = 0; i < board.getAliens().size()-1; i++){
            board.getShot().setX(board.getAliens().get(i).getX());
            board.getShot().setY(board.getAliens().get(i).getY());
            board.update_shots();
        }
        assertEquals(Commons.NUMBER_OF_ALIENS_TO_DESTROY-1, board.getDeaths());

        board.update();
    }
}
