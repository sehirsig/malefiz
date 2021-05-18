package de.htwg.se.malefiz.model
import de.htwg.se.malefiz.model.properties.Settings

case class Game(number:Int, players:List[Player]) {
  def addPlayer(): Game = {
    val newnum = number + 1
    copy(newnum, Player(newnum.toString, newnum, Gamefigure(newnum, Settings().starts(number)._1, Settings().starts(number)._2)) :: players)
  }
  def getNumber(): Int = players.length
}
