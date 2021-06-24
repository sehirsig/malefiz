/*
Class: Game.scala

Beschreibung:
Spiel-speicherung. Hier werden die Spieler gespeichert.

 */

package de.htwg.se.malefiz.model.gameComponent

import de.htwg.se.malefiz.model.playerComponent.Player

case class Game(var players: Vector[Player]) {
  def addPlayer(player: Player): Game = {
    val newPlayer = Vector(player)
    copy(players ++ newPlayer)
  }

  def getPlayerNumber(): Int = players.size
}
