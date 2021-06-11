package de.htwg.se.malefiz.model.playerComponent

import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.PlayerCell
import de.htwg.se.malefiz.model.{gameboardComponent, playerComponent}

case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
  val cell = PlayerCell(Playerid)
  var figures = Array[Gamefigure]()

  def addFigure(id: Int): Unit = {
    val figure = playerComponent.Gamefigure(figures.length, id, Player.this, startingPos)
    figures = figures :+ figure
  }

  override def toString: String = Playerid + " " + name
}
