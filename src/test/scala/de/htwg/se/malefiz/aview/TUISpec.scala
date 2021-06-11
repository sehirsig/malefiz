package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.controllerComponent
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl
import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}

class TUISpec extends AnyWordSpec with Matchers {
  "A Malefiz Tui" should {
    val controller = controllerBaseImpl.Controller(new Gameboard(Settings().xDim, Settings().yDim))
    val tui = TUI(controller)
    "should be initialy idle" in {
      tui.currentState should be(IdleTUIState)
    }
    "should process an input" in {
      tui.processing("remove")
      tui.processing("replace")
      tui.processing("p")
      tui.currentState should be(PlayerNameState)
      tui.processing("xy")
      tui.currentState should be(IdleTUIState)
    }

    "do nothing on input 'welcomeMessage'" in {
      tui.processing("welcomeMessage")
    }
    "add players on input 'p'" in {
      tui.processing("p")
      tui.processing("xy")
      tui.processing("p")
      tui.processing("yz")
      tui.processing("p")
      tui.processing("yz")
      tui.currentState should be(IdleTUIState)
    }
    "start the game on input 'start'" in {
      tui.processing("s")
      tui.currentState should be(PlayingTUIState)
    }
    "roll the dice on input 'r'" in {
      tui.processing("r")
      tui.processing("1")
    }
    "move up on input 'w'" in {
      tui.controller.moveCounter = 6
      tui.processing("1")
      tui.processing("w")
    }
    "move left on input 'a'" in {
      tui.processing("a")
    }
    "move down on input 's'" in {
      tui.processing("s")
    }
    "move right on input 'd'" in {
      tui.processing("d")
    }
    "do nothing on invalid inputs" in {
      tui.processing("krgr")
    }
  }
}
