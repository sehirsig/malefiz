package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

/** State-Pattern für die TUI.scala (Text-User-Interface).
 *  Haupt-Interface für unsere States.
 *
 *  @author sehirsig & franzgajewski
 */
trait TUIState {
  def processing(input: String): TUIState
}

/** State während Anfang des Spiels, um Spieler hinzuzufügen, sowie das Spiel zu starten. */
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
        /** Debug, der einen Spieler vor dem Ziel platziert. */
      case "pDEBUG" => {
        if(controller.game.getPlayerNumber() < 4) {
          controller.addPlayerDEBUGWINTEST("debug")
          IdleTUIState
        }
        else {
          println("game full");IdleTUIState
        }
      }
      case "remove" => controller.setBlockStrategy(input);IdleTUIState
      case "replace" => controller.setBlockStrategy(input);IdleTUIState
      case "welcomeMessage" => println("Welcome to Malefiz");IdleTUIState
      case _ => println("invalid input");IdleTUIState
    }
  }
}

/** State für die Benennung des Spielers. */
object PlayerNameState extends TUIState {
  def processing(input: String): TUIState = {
    controller.addPlayerName(input)
    IdleTUIState
  }
}

/** State fürs Spielen. */
object PlayingTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
        /** In eine File den Spielstand speichern. */
      case "s" => controller.save;PlayingTUIState
        /** Aus einer File den Spielstand laden. */
      case "l" => controller.load;PlayingTUIState
        /** Würfeln. */
      case "r" => controller.rollDice();println("You have rolled a: " + controller.moveCounter);ChooseGameFigTUIState
        /** Debug Würfeln. (Immer 1) */
      case "rDEBUG" => controller.debugDice();println("You have rolled a: " + controller.moveCounter);ChooseGameFigTUIState
      case _ => println("invalid input");PlayingTUIState
    }
  }
}

/** State für die Spielfigur-Auswahl. */
object ChooseGameFigTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "1" | "2" | "3" | "4" | "5" => controller.selectFigure(input.toInt);MovingTUIState
      case _ => println("invalid input");ChooseGameFigTUIState
    }
  }
}

/** State für den Spiel-Gewinn. */
object WinnerTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case _ => println("Winner: " + controller.gameWon._2 + "\n");GameResetTUIState
    }
  }
}

/** State für den Spiel-Reset nach Gewinn. */
object GameResetTUIState extends TUIState {
  def processing(input: String): TUIState = {
    input match {
      case "reset" => controller.resetGame();IdleTUIState
      case _ => println("Invalid Input! Enter 'reset' to start a new Game!");GameResetTUIState
    }
  }
}

/** State für das bewegen der Spielfigur. */
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