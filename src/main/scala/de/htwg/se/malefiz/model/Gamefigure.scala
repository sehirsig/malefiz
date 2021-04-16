package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, spieler:Player) {
  override def toString:String = spieler.Playerid.toString
}
