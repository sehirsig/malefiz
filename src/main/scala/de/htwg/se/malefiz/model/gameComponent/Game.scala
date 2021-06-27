package de.htwg.se.malefiz.model.gameComponent

import de.htwg.se.malefiz.model.playerComponent.Player

/** Game class. Here the players are stored.
 *
 *  @author sehirsig & franzgajewski
 */
case class Game(var players: Vector[Player]) {
  def addPlayer(player: Player): Game = {
    val newPlayer = Vector(player)
    copy(players ++ newPlayer)
  }

  def getPlayerNumber(): Int = players.size
}