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
    "switch back to IdleTUIState " in {
      currentState = currentState.processing("invalid")
      currentState = currentState.processing("1")
      currentState should be(IdleTUIState)
    }
    "not start the Game" in {
      currentState = currentState.processing("s")
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
    }
    "switch to PlayingTUIState" in {
      currentState = currentState.processing("s")
      currentState should be(PlayingTUIState)
    }
    "do nothing with Invalid input" in {
      currentState = currentState.processing("invalid")
      currentState should be(PlayingTUIState)
    }
    "switch to ChooseGameFigTUIState" in {
      currentState = currentState.processing("r")
      currentState should be(ChooseGameFigTUIState)
    }
    "switch to MovingTUIState" in {
      currentState = currentState.processing("1")
    }
    "move the Gamefigure" in {
      currentState = currentState.processing("invalid")
      currentState = currentState.processing("w")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
      currentState = currentState.processing("d")
    }
    "skip the turn" in {
      currentState = currentState.processing("skip")
    }
    "undo the Gamefigure" in {
      currentState = currentState.processing("undo")
    }
    "redo the Gamefigure" in {
      currentState = currentState.processing("redo")
    }
    "switch to Winner if won" in {
      currentState = WinnerTUIState
      currentState should be(WinnerTUIState)
      currentState = currentState.processing("Anything")
    }
  }
}