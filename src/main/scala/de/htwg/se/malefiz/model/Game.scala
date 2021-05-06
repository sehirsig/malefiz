package de.htwg.se.malefiz.model

case class Game() {
  private var players = 0

  def addPlayer(): Unit = players += 1
  def getPlayers(): Int = players
}
