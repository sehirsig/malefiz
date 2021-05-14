package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

trait TUIState {
  def processing(input: String): TUIState
}

object IdleTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "p" => {
        if(controller.game.getPlayers() > 1) {
          controller.addPlayer()
          Ready1TUIState
        }
        else {
          IdleTUIState
        }
      }
      case _ => println("invalid input");IdleTUIState
    }
  }
}

object Ready1TUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "p" => controller.addPlayer();IdleTUIState
      case "s" => controller.startGame();IdleTUIState
      case _ => println("invalid input");Ready1TUIState
    }
  }
}

object Ready2TUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "s" => controller.startGame();IdleTUIState
      case _ => println("invalid input");Ready2TUIState
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