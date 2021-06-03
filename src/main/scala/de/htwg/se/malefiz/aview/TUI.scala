package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.{CellChanged, Controller, GameStatus, SettingUp}

import de.htwg.se.malefiz.util.Observer

import scala.io.StdIn.readLine
import scala.swing.Reactor


case class TUI(controller: Controller) extends Reactor {
  listenTo(controller)
  //controller.add(this)

  var currentState:TUIState = IdleTUIState

  def processing(input: String): Unit = {
    currentState = currentState.processing(input: String)
  }

  reactions += {
    case event: CellChanged => printTui
    case event: SettingUp => printStatus

  }

  def printTui: Unit = {
    println(controller.boardToString())
    println(GameStatus.gameMessage(controller.gameStatus))
  }

  def printStatus: Unit = {
    println(GameStatus.gameMessage(controller.gameStatus))
  }


  /*override def update: Boolean =  {
      if (controller.gameStatus == GameStatus.PLAYING || controller.gameStatus == GameStatus.MOVING) println(controller.boardToString)
        println(GameStatus.gameMessage(controller.gameStatus))
      //    if (controller.gameStatus != GameStatus.IDLE && controller.gameStatus != GameStatus.READY) println(GameStatus.playerMessage(controller.playerStatus))
    true
  }*/
}
