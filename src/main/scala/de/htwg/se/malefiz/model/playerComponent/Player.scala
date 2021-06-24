/*
Class: Player.scala

Beschreibung:
Die Klasse für die Spieler.

 */

package de.htwg.se.malefiz.model.playerComponent

import de.htwg.se.malefiz.model.cellComponent.PlayerCell
import de.htwg.se.malefiz.model.gamefigureComponent.Gamefigure

case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
  val cell = PlayerCell(Playerid) //PlayerCell des Spielers bestimmen.
  var figures = Array[Gamefigure]()

  def addFigure(id: Int): Unit = {
    val figure = Gamefigure(figures.length, id, Player.this, startingPos)
    figures = figures :+ figure
  }

  override def toString: String = Playerid + " " + name
}
