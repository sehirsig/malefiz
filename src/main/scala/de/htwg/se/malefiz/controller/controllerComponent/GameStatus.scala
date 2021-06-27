package de.htwg.se.malefiz.controller.controllerComponent

/** The status updates of the game.
 *
 *  @author sehirsig & franzgajewski
 */
object GameStatus extends Enumeration {
  type GameStatus = Value
  type PlayerStatus = Value
  val WELCOME, LOADED, SAVED, GAMEWINNER, CHOOSEFIG, IDLE, READY1, READY2, PLAYER0, PLAYER1, PLAYER2, PLAYER3, PLAYER4, PLAYING, MOVING, ENTERNAME = Value

  val gameMap = Map[GameStatus, String](
    WELCOME -> "Welcome to the Malefiz Game 2021!\n'p' to add players!",
    GAMEWINNER -> "We have a Winner! 'reset' to Reset and Play Again!",
    CHOOSEFIG -> "Choose a Figure (1-5)",
    IDLE -> "Press p to add players",
    READY1 -> "Type 's' to start or press p to add more players",
    READY2 -> "Type 's' to start to play the Game",
    PLAYING -> "Press r to roll dice",
    MOVING -> "Press a,w,s,d to move",
    ENTERNAME -> "Please enter your name",
    LOADED -> "Game loaded!",
    SAVED -> "Game saved!"
  )

  val playerMap = Map[PlayerStatus, String](
    PLAYER1 -> "Player 1 press r to roll dice",
    PLAYER2 -> "Player 2 press r to roll dice",
    PLAYER3 -> "Player 3 press r to roll dice",
    PLAYER4 -> "Player 4 press r to roll dice",
    PLAYER0 -> "Player -"
  )

  def gameMessage(gameStatus: GameStatus): String = {
    gameMap(gameStatus)
  }
}