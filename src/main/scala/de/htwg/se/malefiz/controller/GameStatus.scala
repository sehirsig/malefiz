package de.htwg.se.malefiz.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, READY, PLAYING, TURN = Value

  val map = Map[GameStatus, String](
    IDLE -> "Press p to add players",
    READY -> "Type start or press p to add more players",
    PLAYING -> "Role dice",
    TURN -> "Move")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}