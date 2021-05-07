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
    "do nothing on input 'welcomeMessage'" in {
      tui.processing("welcomeMessage")
    }
    "add players on input 'p'" in {
      tui.processing("p")
      tui.processing("p")
      controller.game.getPlayers() should be (2)
    }
    "start the game on input 'start'" in {
      tui.processing("start")
      controller.gameStatus should be (PLAYING)
      controller.playerStatus should be (PLAYER1)
    }
    "roll the dice on input 'r'" in {
      tui.processing("r")
      controller.moveCounter should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
    }
    "move up on input 'w'" in {
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
