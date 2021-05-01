package de.htwg.se.malefiz.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, READY, PLAYING, MOVING = Value

  val map = Map[GameStatus, String](
    IDLE -> "Press p to add players",
    READY -> "Type start or press p to add more players",
    PLAYING -> "Press r to roll dice",
    MOVING -> "Press a,w,s,d to move")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}