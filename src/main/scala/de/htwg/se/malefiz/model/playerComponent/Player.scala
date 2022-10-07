package de.htwg.se.malefiz.model.playerComponent

import de.htwg.se.malefiz.model.cellComponent.PlayerCell
import de.htwg.se.malefiz.model.gamefigureComponent.Gamefigure

/** Player class.
 *
 *  @author sehirsig & franzgajewski
 */
case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
  /** Define player's cell. */
  def cell(num:Int) = PlayerCell(Playerid, num)
  var figures = Array[Gamefigure]()

  def addFigure(id: Int): Unit = {
    val figure = Gamefigure(figures.length, id, Player.this, startingPos)
    figures = figures :+ figure
  }

  override def toString: String = Playerid + " " + name
}