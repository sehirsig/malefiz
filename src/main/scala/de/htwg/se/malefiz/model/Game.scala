package de.htwg.se.malefiz.model

case class Game(players: Vector[Player]) {
  def addPlayer(player: Player): Game = {
    val newPlayer = Vector(player)
    copy(players ++ newPlayer)
  }
  def getPlayerNumber(): Int = players.size
}
