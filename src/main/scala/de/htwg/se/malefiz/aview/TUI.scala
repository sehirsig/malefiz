package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.{ChooseFig, Controller, GameReset, GameStatus, Moving, RollDice, SettingUp, StartGame, StartUp, WonGame}

import scala.io.StdIn.readLine
import scala.swing.Reactor


case class TUI(controller: Controller) extends Reactor {
  listenTo(controller)

  var currentState:TUIState = IdleTUIState

  def processing(input: String): Unit = {
    currentState = currentState.processing(input: String)
  }

  reactions += {
    case event: RollDice => printTui; currentState = PlayingTUIState
    case event: Moving => printTui;currentState = MovingTUIState
    case event: ChooseFig => printStatus;currentState = ChooseGameFigTUIState
    case event: SettingUp => printStatus
    case event: StartUp => printStatus
    case event: StartGame => printStatus; currentState = PlayingTUIState
    case event: WonGame => printStatus; currentState = WinnerTUIState
    case event: GameReset => printStatus; currentState = IdleTUIState
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
