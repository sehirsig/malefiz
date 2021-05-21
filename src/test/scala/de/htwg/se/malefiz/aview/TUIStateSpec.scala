package de.htwg.se.malefiz.aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUIStateSpec extends AnyWordSpec with Matchers {
  "An TUI State Spec" should {
    var currentState:TUIState = IdleTUIState
    "not switch IdleTUIState" in {
      currentState = currentState.processing("welcomeMessage")
      currentState should be(IdleTUIState)
      currentState = currentState.processing("ded")
      currentState should be(IdleTUIState)
    }
    "switch to PlayerNameState" in {
      currentState = currentState.processing("p")
      currentState should be(PlayerNameState)
    }
    "switch to PlayerColourState" in {
      currentState = currentState.processing("Player1")
      currentState should be(PlayerColorState)
    }
    "switch back to IdleTUIState " in {
      currentState = currentState.processing("invalid")
      currentState = currentState.processing("1")
      currentState should be(IdleTUIState)
    }
    "not start ghte Game" in {
      currentState = currentState.processing("s")
      currentState should be(IdleTUIState)
    }
    "add 4 Players" in {
      currentState = currentState.processing("p")
      currentState = currentState.processing("Player2")
      currentState = currentState.processing("2")
      currentState = currentState.processing("p")
      currentState = currentState.processing("Player3")
      currentState = currentState.processing("3")
      currentState = currentState.processing("p")
      currentState = currentState.processing("Player4")
      currentState = currentState.processing("4")
    }
    "add no more players" in {
      currentState = currentState.processing("p")
      currentState should be(IdleTUIState)
    }
    "switch to PlayingTUIState" in {
      currentState = currentState.processing("s")
      currentState should be(PlayingTUIState)
    }
    "do nothing with Invalid input" in {
      currentState = currentState.processing("invalid")
      currentState should be(PlayingTUIState)
    }
    "switch to MovingTUIState" in {
      currentState = currentState.processing("r")
      currentState should be(MovingTUIState)
    }
    "move the Gamefigure" in {
      currentState = currentState.processing("invalid")
      currentState = currentState.processing("w")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState should be(PlayingTUIState)
    }
    "undo the Gamefigure" in {
      currentState = currentState.processing("r")
      currentState = currentState.processing("r")
      currentState = currentState.processing("d")
      currentState = currentState.processing("undo")
    }
    "redo the Gamefigure" in {
      currentState = currentState.processing("redo")
    }
  }
}
