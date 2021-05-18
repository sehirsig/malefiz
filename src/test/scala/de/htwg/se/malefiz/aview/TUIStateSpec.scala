package de.htwg.se.malefiz.aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUIStateSpec extends AnyWordSpec with Matchers {
  "An TUI State Spec" should {
    var currentState:TUIState = IdleTUIState
    "not switch State in " in {
      currentState = currentState.processing("welcomeMessage")
      currentState should be(IdleTUIState)
      currentState = currentState.processing("ded")
      currentState should be(IdleTUIState)
    }
    "switch State in " in {
    }
  }
}
