/*
Class: TUIState.scala

Beschreibung:
State-Pattern für die TUI.scala (Text-User-Interface).
 */

package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

trait TUIState { //Haupt-Interface für unsere States.
  def processing(input: String): TUIState
}

object IdleTUIState extends TUIState { //State während Anfang des Spiels, um Spieler hinzuzufügen, sowie das Spiel zu starten.
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

object PlayerNameState extends TUIState { //State für die Benennung des Spielers.
  def processing(input: String): TUIState = {
    controller.addPlayerName(input)
    IdleTUIState
  }
}

object PlayingTUIState extends TUIState { //State fürs Spielen.
  def processing(input: String): TUIState = {
    input match {
      case "s" => controller.save;PlayingTUIState //In eine File den Spielstand speichern.
      case "l" => controller.load;PlayingTUIState //Aus einer File den Spielstand laden.
      case "r" => controller.rollDice();println("You have rolled a: " + controller.moveCounter);ChooseGameFigTUIState //Würfeln.
      case _ => println("invalid input");PlayingTUIState
    }
  }
}

object ChooseGameFigTUIState extends TUIState { //State für die Spielfigur-Auswahl.
  def processing(input: String): TUIState = {
    input match {
      case "1" | "2" | "3" | "4" | "5" => controller.selectFigure(input.toInt);MovingTUIState
      case _ => println("invalid input");ChooseGameFigTUIState
    }
  }
}

object WinnerTUIState extends TUIState { //State für den Spiel-Gewinn.
  def processing(input: String): TUIState = {
    input match {
      case _ => controller.resetGame();IdleTUIState
    }
  }
}

object MovingTUIState extends TUIState { //State für das bewegen der Spielfigur.
  def processing(input: String): TUIState = {
    if (controller.gameWon._1) { //Check, ob es schon einen Gewinner gibt.
      println("We Have a Winner: " + controller.gameWon._2 + "\n")
      WinnerTUIState.processing(input);
      return WinnerTUIState
    }

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
      if (controller.gameWon._1) {
        MovingTUIState
      } else {
        PlayingTUIState
      }
    } else {
      println("Moves remaining:" + controller.moveCounter)
      MovingTUIState
    }
  }
}