package de.htwg.se.malefiz.model.playerComponent

case class Game(var players: Vector[Player]) {
  def addPlayer(player: Player): Game = {
    val newPlayer = Vector(player)
    copy(players ++ newPlayer)
  }

  def getPlayerNumber(): Int = players.size
}
