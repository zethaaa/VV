Feature: Jugar y perder una partida

  Como Jugador
  Quiero poder jugar y perder una partida
  Para poder finalizar una partida

  Conversación:
  Para perder una partida podré hacerlo permitiendo que un objeto 'Bomb' de cualquier alien
  alcance la nave del jugador o, permitiendo que los aliens superen el borde inferior de la
  pantalla de juego.
  En cada caso se mostrará un mensaje de derrota distinto.

  Criterios de aceptación
  CA1: Dado el tablero de juego Space Invaders
  Cuando una bomba de un alien alcanza la nave del jugador
  Entonces la nave del jugador muere (setDying)
  Y se finaliza la partida (inGame == false)
  Y se muestra el mensaje 'Game Over'

  CA2: Dado el tablero de juego Space Invaders
  Cuando un alien alcanza el límite inferior del tablero de juego
  Entonces la nave del jugador muere  (setDying)
  Y se finaliza la partida
  Y se muestra el mensaje 'Invasion!'

  CA3: Dado el tablero de juego Space Invaders
  Cuando no se cumple ninguna de las condiciones de derrota
  Entonces la nave del jugador no muere
  Y no se finaliza la partida


  Rule: Perder partida

    Scenario: Perder partida por impacto de bomba
      Given Dado el tablero del juego Space Invaders
      When una bomba alcanza la posicion del jugador
      Then muere la nave del jugador
      And se muestra el mensaje "Game Over"

    Scenario: Perder la partida por invasion de los aliens
      Given Dado el tablero del juego Space Invaders
      When un alien alcanza el límite inferior del tablero de juego
      Then se finaliza la partida
      And se muestra el mensaje "Invasion!"

  Rule: Continuar la partida

    Scenario: Continuar la partida
      Given Dado el tablero del juego Space Invaders
      When no se cumple ninguna de las condiciones de derrota
      Then la nave del jugador no muere
      And continua la partida

