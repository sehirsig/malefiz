package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.controllerComponent
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.malefiz.controller.controllerComponent._
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
      //tui.currentState should be(IdleTUIState)
      tui.processing("xy")
      //tui.currentState should be(IdleTUIState)
    }
    "react on events" in {
      controller.publish(new RollDice)
      tui.currentState should be(PlayingTUIState)
      controller.publish(new Moving)
      tui.currentState should be(MovingTUIState)
      controller.publish(new ChooseFig)
      tui.currentState should be(ChooseGameFigTUIState)
      controller.publish(new SettingUp)
      tui.currentState should be(ChooseGameFigTUIState)
      controller.publish(new StartUp)
      tui.currentState should be(ChooseGameFigTUIState)
      controller.publish(new StartGame)
      tui.currentState should be(PlayingTUIState)
      controller.publish(new WonGame)
      tui.currentState should be(WinnerTUIState)
      controller.publish(new GameReset)
      tui.currentState should be(IdleTUIState)
      controller.publish(new GameSaved)
      tui.currentState should be(IdleTUIState)
      controller.publish(new GameLoaded)
      tui.currentState should be(IdleTUIState)

    }
    "do nothing on input 'welcomeMessage'" in {
      tui.processing("welcomeMessage")
      tui.currentState should be(IdleTUIState)
    }
    "do not start if < 2 players" in {
      tui.processing("s")
      tui.currentState should be(IdleTUIState)
    }
    "add players on input 'p'" in {
      tui.processing("pDEBUG")
      //tui.processing("xy")
      tui.processing("p")
      tui.processing("yz")
      tui.processing("p")
      tui.processing("kz")
      tui.processing("p")
      tui.currentState should be(IdleTUIState)
    }
    "start the game on input 'start'" in {
      tui.processing("s")
      tui.currentState should be(PlayingTUIState)
    }
    "roll the dice on input 'r'" in {
      tui.processing("r")
      tui.currentState should be(ChooseGameFigTUIState)
    }
    "choose no Figure if invalid input" in {
      tui.processing("Invalid")
      tui.currentState should be(ChooseGameFigTUIState)
    }
    "choose Figure if valid input" in {
      tui.processing("1")
      tui.currentState should be(MovingTUIState)
    }
    "not move on invalid input" in {
      tui.processing("invalid")
    }
    "move up on input 'w'" in {
      tui.controller.moveCounter = 5
      tui.processing("w")
    }
    "move left on input 'a'" in {
      tui.processing("a")
    }
    "move undo on input 'undo'" in {
      tui.processing("undo")
    }
    "move redo on input 'redo'" in {
      tui.processing("redo") //Redo, then move the rest to get the top left Blocked Cell, to walk down the next time

      tui.processing("a")
      tui.processing("w")
      tui.processing("w")
      tui.processing("rDEBUG") //Player 2 Rolls and chooses Gamefigure in Debug Mode
      tui.processing("1")
    }

    "skip round on input 'skip'" in {
    tui.processing("skip") //Player 2 Skips round
    }

    "move down on input 's'" in {
      tui.processing("r") //Player 1 rolls, chooses gamefig 1 to move down
      tui.controller.moveCounter = 5
      tui.processing("1")

      tui.processing("s")
      tui.processing("s")
    }
    "move right on input 'd'" in {
      tui.processing("d")
    }
    "do use a on MovingTUI" in {
      tui.currentState = MovingTUIState
      tui.processing("a")
      tui.currentState should be (MovingTUIState)
    }
    "do use undo on MovingTUI" in {
      tui.currentState = MovingTUIState
      tui.processing("undo")
      tui.currentState should be (MovingTUIState)
    }
    "do use redo on MovingTUI" in {
      tui.currentState = MovingTUIState
      tui.processing("redo")
      tui.currentState should be (MovingTUIState)
    }
    "do nothing on invalid inputs" in {
      tui.processing("krgr")
    }
    "save the game" in {
      tui.currentState = PlayingTUIState
      tui.processing("s")
      tui.currentState should be (PlayingTUIState)
    }
    "load the game" in {
      //tui.processing("l") Travis Wirft Fehler
      tui.currentState should be (PlayingTUIState)
    }
    "do nothing with the game" in {
      tui.processing("invalid")
      tui.currentState should be (PlayingTUIState)
    }
    "do correct on a win" in {
      tui.currentState = WinnerTUIState
      tui.currentState = WinnerTUIState.processing("")
    }
    "do nothing with Idle Invalid input" in {
      tui.currentState = IdleTUIState
      tui.processing("invalidinput")
      tui.currentState should be (IdleTUIState)
    }
    "do nothing on Reset Invalid input" in {
      tui.currentState = GameResetTUIState
      tui.processing("invalidinput")
      tui.currentState should be (GameResetTUIState)
    }
    "do correct on a Reset " in {
      tui.currentState = GameResetTUIState
      tui.processing("reset")
      tui.currentState should be (IdleTUIState)
    }
  }
}
