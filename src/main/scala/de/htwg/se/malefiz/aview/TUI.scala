/*
Class: TUI.scala

Beschreibung:
Malefiz als "Text-User-Interface". Spielausgabe in der Konsole

 */


package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.controllerComponent._

import scala.swing.Reactor


case class TUI(controller: ControllerInterface) extends Reactor {
  listenTo(controller) //Auf den Controller hören, um auf Events zu reagieren.

  var currentState:TUIState = IdleTUIState //Initialisierung des momentanen State.

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
    case event: WonGame => printTui; currentState = WinnerTUIState; currentState.processing("")
    case event: GameReset => printStatus; currentState = IdleTUIState
    case event: GameSaved => printStatus;
    case event: GameLoaded => printStatus
  }


  def printTui: Unit = { //Printet das Spielfeld.
    println(controller.boardToString())
    println(GameStatus.gameMessage(controller.gameStatus))
  }

  /** Prints the Status.
   *
   */
  def printStatus: Unit = { //Printet den Spielstatus.
    println(GameStatus.gameMessage(controller.gameStatus))
  }

}
