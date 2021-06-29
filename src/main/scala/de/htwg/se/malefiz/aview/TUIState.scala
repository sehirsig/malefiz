package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

/** State pattern for TUI.scala.
 *  Main interface for our states.
 *
 *  @author sehirsig & franzgajewski
 */
trait TUIState {
  def processing(input: String): TUIState
}

/** Initial state. Used to add players and start the game. */
object IdleTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "p" => {
        if(controller.game.getPlayerNumber() < 4) {
          controller.addPlayer()
          PlayerNameState
        }
        else {
          println("game full");IdleTUIState
        }
      }
      case "s" => {
        if(controller.game.getPlayerNumber() > 1) {
          controller.startGame()
          PlayingTUIState
        }
        else {
          println("not enough players");IdleTUIState
        }
      }
        /** A debug-player that starts in front of the goal. */
      case "pDEBUG" => {
        if(controller.game.getPlayerNumber() < 4) {
          controller.addPlayerDEBUGWINTEST("debug")
          IdleTUIState
        }
        else {
          println("game full");IdleTUIState
        }
      }
      case "load" => {
        if(controller.game.getPlayerNumber() > 1) {
          controller.load
          PlayingTUIState
        }
        else {
          println("This game has " + controller.game.getPlayerNumber().toString + " Player/s. You need to add atleast 2 players to load a game!");IdleTUIState
        }
      }
      case "remove" => controller.setBlockStrategy(input);IdleTUIState
      case "replace" => controller.setBlockStrategy(input);IdleTUIState
      case "welcomeMessage" => println("Welcome to Malefiz");IdleTUIState
      case _ => println("invalid input");IdleTUIState
    }
  }
}

/** State for entering player names. */
object PlayerNameState extends TUIState {
  def processing(input: String): TUIState = {
    controller.addPlayerName(input)
    IdleTUIState
  }
}

/** State for running the game. */
object PlayingTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
        /** Current state of the board gets saved to file. */
      case "save" => controller.save;PlayingTUIState
        /** Die roll. */
      case "r" => controller.rollDice();println("You have rolled a: " + controller.moveCounter);ChooseGameFigTUIState
        /** Die roll for debug. Always rolls a 1. */
      case "rDEBUG" => controller.debugDice();println("You have rolled a: " + controller.moveCounter);ChooseGameFigTUIState
      case _ => println("invalid input");PlayingTUIState
    }
  }
}

/** State for selecting a game figure. */
object ChooseGameFigTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "1" | "2" | "3" | "4" | "5" => controller.selectFigure(input.toInt);MovingTUIState
      case _ => println("invalid input");ChooseGameFigTUIState
    }
  }
}

/** State for winning the game. */
object WinnerTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case _ => println("Winner: " + controller.gameWon._2 + "\n");GameResetTUIState
    }
  }
}

/** State for reset after the game was won. */
object GameResetTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "reset" => controller.resetGame();IdleTUIState
      case _ => println("Invalid Input! Enter 'reset' to start a new Game!");GameResetTUIState
    }
  }
}

/** State for moving of game figures. */
object MovingTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "w" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "a" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "s" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "d" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "undo" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "redo" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case "skip" => controller.move(input, controller.selectedFigNum); MovingTUIState
      case _ => println("invalid input"); MovingTUIState
    }
    if (controller.moveCounter == 0) {
      if (controller.gameWon._1){
        GameResetTUIState
      } else {
        PlayingTUIState
      }
    } else {
      println("Moves remaining:" + controller.moveCounter)
      MovingTUIState
    }
  }
}