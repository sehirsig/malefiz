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
        if(controller.game.getPlayers() < 4) {
          controller.gameStatus = ENTERNAME
          controller.notifyObservers
          PlayerNameState
        }
        else {
          println("game full");IdleTUIState
        }
      }
      case "s" => {
        if(controller.game.getPlayers() > 1) {
          controller.startGame()
          PlayingTUIState
        }
        else {
          println("not enough players");IdleTUIState
        }
      }
      case "welcomeMessage" => println("Welcome to Malefiz");IdleTUIState
      case _ => println("invalid input");IdleTUIState
    }
  }
}

object  PlayerNameState extends  TUIState {
  def processing(input: String): TUIState = {
    controller.gameStatus = ENTERCOLOR
    controller.notifyObservers
    PlayerColorState
  }
}

object  PlayerColorState extends  TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "1" => IdleTUIState
      case "2" => IdleTUIState
      case "3" => IdleTUIState
      case "4" => IdleTUIState
      case _ => println("invalid input");PlayerColorState
//        controller.gameStatus = READY1
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
    input match {
      case "w" => controller.move(input);MovingTUIState
      case "a" => controller.move(input);MovingTUIState
      case "s" => controller.move(input);MovingTUIState
      case "d" => controller.move(input);MovingTUIState
      case _ => println("invalid input");MovingTUIState
    }
  }
}