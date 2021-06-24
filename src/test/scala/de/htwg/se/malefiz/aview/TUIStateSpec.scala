package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUIStateSpec extends AnyWordSpec with Matchers {
  /* Disabled because of TRAVIS
  "A Text-UI State" when {
    "A TUI State" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      val tui = TUI(controller)
      "Add one player" in {
        tui.currentState = IdleTUIState
        tui.processing("pDEBUG")
      }
      "Do not start" in {
        tui.processing("s")
      }
      "Add more Debug players" in {
        tui.processing("pDEBUG")
        tui.processing("pDEBUG")
        tui.processing("pDEBUG")
        tui.processing("pDEBUG")
        tui.currentState should be (IdleTUIState)
      }
      "Should not add more Debug Players" in {
        tui.processing("pDEBUG")
        tui.currentState should be (IdleTUIState)
      }
      "Start the Game Players" in {
        tui.processing("s")
        tui.currentState should be (PlayingTUIState)
      }
      "Roll the Debug Dice Players" in {
        tui.processing("rDEBUG")
        tui.currentState should be (ChooseGameFigTUIState)
      }
      "Choose a Fig" in {
        tui.processing("1")
        tui.currentState should be (MovingTUIState)
      }
      "Win The Game" in {
        tui.processing("w")
        tui.currentState should be (GameResetTUIState)
      }
      "Not Reset the game" in {
        tui.processing("invalid")
      }
      "Reset the game" in {
        tui.processing("reset")
      }
    }
  }
  */
}