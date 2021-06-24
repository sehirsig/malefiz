package de.htwg.se.malefiz.model.gamefigureComponent

import de.htwg.se.malefiz.model.playerComponent.Player

/** Hier werden die Spielfiguren in unserem Spiel deklariert.
 *
 *  @author sehirsig & franzgajewski
 */
case class Gamefigure(number: Int, id: Int, player: Player, pos: (Int, Int)) {
  def getNumber: Int = number

  def getPlayer: Player = player

  def updatePos(posNew: (Int, Int)): Gamefigure = {
    copy(pos = posNew)
  }

  override def toString: String = player.Playerid.toString + " "
}
