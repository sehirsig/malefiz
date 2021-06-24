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
    "Give a correct game full warning" in {
      tui.currentState = IdleTUIState
      tui.currentState = tui.processing("pDEBUG")
      tui.currentState = tui.processing("pDEBUG")
      tui.currentState = tui.processing("pDEBUG")
      tui.currentState = tui.processing("pDEBUG")
      tui.currentState = tui.processing("pDEBUG")
    }
    "Not Reset the game" in {
      tui.currentState = GameResetTUIState

    }
  }
}