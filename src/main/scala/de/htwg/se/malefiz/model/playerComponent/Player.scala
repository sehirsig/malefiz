package de.htwg.se.malefiz.model.playerComponent

import de.htwg.se.malefiz.model.cellComponent.PlayerCell
import de.htwg.se.malefiz.model.gamefigureComponent.Gamefigure

/** Die Klasse für die Spieler.
 *
 *  @author sehirsig & franzgajewski
 */
case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
  /** PlayerCell des Spielers bestimmen. */
  val cell = PlayerCell(Playerid)
  var figures = Array[Gamefigure]()

  def addFigure(id: Int): Unit = {
    val figure = Gamefigure(figures.length, id, Player.this, startingPos)
    figures = figures :+ figure
  }

  override def toString: String = Playerid + " " + name
}
