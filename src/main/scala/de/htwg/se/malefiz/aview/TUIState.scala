package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._

trait TUIState {
  def processing(input: String): TUIState
}

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
      case "remove" => controller.setBlockStrategy(input);IdleTUIState
      case "replace" => controller.setBlockStrategy(input);IdleTUIState
      case "welcomeMessage" => println("Welcome to Malefiz");IdleTUIState
      case _ => println("invalid input");IdleTUIState
    }
  }
}

object  PlayerNameState extends  TUIState {
  def processing(input: String): TUIState = {
    controller.addPlayerName(input)
    PlayerColorState
  }
}

object  PlayerColorState extends  TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "1" => controller.addPlayerColor(input.toInt);IdleTUIState
      case "2" => controller.addPlayerColor(input.toInt);IdleTUIState
      case "3" => controller.addPlayerColor(input.toInt);IdleTUIState
      case "4" => controller.addPlayerColor(input.toInt);IdleTUIState
      case _ => println("invalid input");PlayerColorState
    }
  }
}

object PlayingTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "r" => controller.rollDice();MovingTUIState
      case _ => println("invalid input");PlayingTUIState
    }
  }
}

object MovingTUIState extends TUIState {
  def processing(input: String): TUIState = {
    if (controller.moveCounter == 0) {
      PlayingTUIState.processing(input)
    } else {
      input match {
        case "w" => controller.move(input, 0); MovingTUIState //TODO: Select Gamefigures, instead of 1
        case "a" => controller.move(input, 0); MovingTUIState
        case "s" => controller.move(input, 0); MovingTUIState
        case "d" => controller.move(input, 0); MovingTUIState
        case "undo" => controller.move(input, 0); MovingTUIState
        case "redo" => controller.move(input, 0); MovingTUIState
        case _ => println("invalid input"); MovingTUIState
      }
    }
  }
}