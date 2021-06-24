package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUIStateSpec extends AnyWordSpec with Matchers {
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
    "Add more Players" in {
      tui.processing("pDEBUG")
      tui.processing("pDEBUG")
      tui.processing("pDEBUG")
      tui.processing("pDEBUG")
    }
    "Not Reset the game" in {
      tui.currentState = GameResetTUIState
      tui.currentState should be (GameResetTUIState)
    }
  }
}