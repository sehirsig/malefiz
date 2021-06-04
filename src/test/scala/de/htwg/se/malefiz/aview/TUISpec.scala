package de.htwg.se.malefiz.aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.Gameboard
import de.htwg.se.malefiz.model.properties.Settings

class TUISpec extends AnyWordSpec with Matchers {
  "A Malefiz Tui" should {
    val controller =  Controller(new Gameboard(Settings().xDim, Settings().yDim))
    val tui =  TUI(controller)
    "should be initialy idle" in {
      tui.currentState should be(IdleTUIState)
    }
//    "should process an input" in {
//      tui.processing("p")
//      tui.currentState should be(PlayerNameState)
//      tui.processing("xy")
//      tui.currentState should be(IdleTUIState)
//    }
//    "should react to events" in {
//      controller.selectFigure(1)
//      tui.currentState should be(MovingTUIState)
//    }
//  }
//    "do nothing on input 'welcomeMessage'" in {
//      tui.processing("welcomeMessage")
//    }
//    "add players on input 'p'" in {
//      tui.processing("p")
//      tui.processing("xy")
//      tui.processing("p")
//      tui.processing("yz")
//      tui.currentState should be(IdleTUIState)
//    }
//    "start the game on input 'start'" in {
//      tui.processing("s")
//      tui.currentState should be(PlayingTUIState)
//    }
//    "roll the dice on input 'r'" in {
//      tui.processing("r")
//    }
//    "move up on input 'w'" in {
//      tui.processing("w")
//    }
//    "move left on input 'a'" in {
//      tui.processing("a")
//    }
//    "move down on input 's'" in {
//      tui.processing("s")
//    }
//    "move right on input 'd'" in {
//      tui.processing("d")
//    }
//    "do nothing on invalid inputs" in {
//      tui.processing("krgr")
//    }
  }
}
