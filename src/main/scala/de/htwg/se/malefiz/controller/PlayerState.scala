package de.htwg.se.malefiz.controller

trait PlayerState {
  def nextPlayer(playerCount:Int): PlayerState
  def getCurrentPlayer: Int
}

object PlayerState1 extends PlayerState {
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
