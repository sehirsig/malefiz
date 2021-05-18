package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings

trait PlayerState {
  def move(direction:String, spielbrett:Gameboard, spielDaten:Game)
  def nextPlayer(playerCount:Int): PlayerState
  def getCurrentPlayer: Int
}

object PlayerState1 extends PlayerState {
  override def move(direction:String, spielbrett:Gameboard, spielDaten:Game): Gameboard = {
    val tempgf = spielDaten.players(getCurrentPlayer).gf
    direction match {
      case "Up" =>
      case "Down" =>
      case "Left" =>
      case "Right" =>
    }
    val tempgf = spielDaten.players(getCurrentPlayer).gf
    spielDaten.players(getCurrentPlayer).gf = tempgf.setCoordinates(moveTypes.goUp(tempgf.coordx, tempgf.coordy))
    spielbrett.replaceCell(spielDaten.players(getCurrentPlayer).gf.coordx)
  }
  override def nextPlayer(playerCount:Int): PlayerState = PlayerState2
  override def getCurrentPlayer: Int = 1
}

object PlayerState2 extends PlayerState {
  override def nextPlayer(playerCount:Int): PlayerState = {
    if(playerCount < 3) {
      PlayerState1
    } else {
      PlayerState3
    }
  }
  override def getCurrentPlayer: Int = 2
}

object PlayerState3 extends PlayerState {
  override def nextPlayer(playerCount:Int): PlayerState = {
    if(playerCount < 4) {
      PlayerState1
    } else {
      PlayerState4
    }
  }
  override def getCurrentPlayer: Int = 3
}

object PlayerState4 extends PlayerState {
  override def nextPlayer(playerCount:Int): PlayerState = PlayerState1
  override def getCurrentPlayer: Int = 4
}
