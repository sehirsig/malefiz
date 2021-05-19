package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, player: Player) {
  def getNumber:Int = number
  def getPlayer:Player = player
  override def toString:String = player.Playerid.toString
}
