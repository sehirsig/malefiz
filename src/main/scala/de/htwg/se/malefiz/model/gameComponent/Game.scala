package de.htwg.se.malefiz.model.gameComponent

import de.htwg.se.malefiz.model.playerComponent.Player

/** Spiel-Speicherung. Hier werden die Spieler gespeichert.
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
