package de.htwg.se.malefiz.aview.GUI

import de.htwg.se.malefiz.Malefiz.controller

trait GUIState {
  def processing(input: String): GUIState
}

object IdleGUIState extends GUIState {

  def processing(input: String): GUIState = {
    input match {
      case "p" => {
        if(controller.game.getPlayerNumber() < 4) {
          controller.addPlayer()
          PlayerNameState
        }
        else {
          println("game full");IdleGUIState
        }
      }
      case "s" => {
        if(controller.game.getPlayerNumber() > 1) {
          controller.startGame()
          PlayingGUIState
        }
        else {
          println("not enough players");IdleGUIState
        }
      }
      case "remove" => controller.setBlockStrategy(input);IdleGUIState
      case "replace" => controller.setBlockStrategy(input);IdleGUIState
      case "welcomeMessage" => println("Welcome to Malefiz");IdleGUIState
      case _ => println("invalid input");IdleGUIState
    }
  }
}

object PlayerNameState extends GUIState {
  def processing(input: String): GUIState = {
    controller.addPlayerName(input)
    IdleGUIState
  }
}

object PlayingGUIState extends GUIState {
  def processing(input: String): GUIState = {
    input match {
      case "r" => controller.rollDice();ChooseGameFigGUIState
      case _ => println("invalid input");PlayingGUIState
    }
  }
}

object ChooseGameFigGUIState extends GUIState {
  def processing(input: String): GUIState = {
    input match {
      case "1" | "2" | "3" | "4" | "5" => controller.selectFigure(input.toInt);MovingGUIState
      case _ => println("invalid input");ChooseGameFigGUIState
    }
  }
}

object WinnerGUIState extends GUIState {
  def processing(input: String): GUIState = {
    input match {
      case _ => IdleGUIState
    }
  }
}

object MovingGUIState extends GUIState {
  def processing(input: String): GUIState = {
    if (controller.moveCounter == 0) {
      if (controller.checkWin()) {
        WinnerGUIState.processing(input)
      } else {
        PlayingGUIState.processing(input)
      }
    } else {
      input match {
        case "w" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "a" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "s" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "d" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "undo" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "redo" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case "skip" => controller.move(input, controller.selectedFigNum); MovingGUIState
        case _ => println("invalid input"); MovingGUIState
      }
    }
  }
}