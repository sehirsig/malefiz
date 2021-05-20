package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, player: Player, pos: (Int, Int)) {
  def getNumber:Int = number
  def getPlayer:Player = player
  def updatePos(pos: (Int, Int)): Gamefigure = {
    copy(pos = pos)
  }
  override def toString:String = player.Playerid.toString
}
