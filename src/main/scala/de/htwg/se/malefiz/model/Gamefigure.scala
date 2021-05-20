package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, player: Player, pos: (Int, Int)) {
  def getNumber:Int = number
  def getPlayer:Player = player
  override def toString:String = player.Playerid.toString
}
