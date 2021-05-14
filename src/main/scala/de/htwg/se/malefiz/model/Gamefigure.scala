package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, spieler:Player) {
  def getNumber:Int = number
  def getPlayer:Player = spieler
  override def toString:String = spieler.Playerid.toString
}
