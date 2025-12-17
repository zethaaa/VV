Feature: Jugar y ganar una partida

  Como Jugador
  Quiero poder jugar y ganar una partida
  Para poder finalizar una partida

  Conversación:
  Una partida se gana cuando el jugador consigue destruir todos los aliens del tablero antes de que se produzca cualquiera de las condiciones de derrota.
  Al finalizar la partida con éxito, el sistema debe mostrar un mensaje de victoria.

  Criterios de aceptación:
  CA1: Dado el tablero de juego Space Invaders, cuando un jugador elimina mediante disparos a todos los aliens presentes en el tablero, gana la partida

  CA2: Dado el tablero de juego Space Invaders
  Cuando no se cumple ninguna condición de victoria ni de derrota
  Entonces la nave del jugador no muere
  Y no se finaliza la partida



  Rule: ganar partida

    Scenario: ganar partida
      Given Dado el tablero del juego Space Invaders
      When se eliminan todos los aliens
      Then se muestra el mensaje "Game won!"
      And se finaliza la partida

  Rule: continuar partida

    Scenario: continuar partida
      Given Dado el tablero del juego Space Invaders
      When Queda al menos un alien en el tablero
      Then continua la partida

