package de.htwg.se.malefiz.model

case class Game(players:Int) {
  def addPlayer(): Game = copy(players + 1)
  def getPlayers(): Int = players
}
