package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.{Controller, GameStatus}
import de.htwg.se.malefiz.util.Observer

import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {
  controller.add(this)

  var currentState:TUIState = IdleTUIState

  def processing(input: String): Unit = {
    currentState = currentState.processing(input: String)
  }

  override def update: Boolean =  {
    if (controller.gameStatus != GameStatus.IDLE && controller.gameStatus != GameStatus.READY1 && controller.gameStatus != GameStatus.READY2) println(controller.boardToString)
    println(GameStatus.gameMessage(controller.gameStatus))
//    if (controller.gameStatus != GameStatus.IDLE && controller.gameStatus != GameStatus.READY) println(GameStatus.playerMessage(controller.playerStatus))
  true
  }
}
