package de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl

import com.google.inject.Guice
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.aview.{GameResetTUIState, MovingTUIState, TUI}
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.controller.controllerComponent.{PlayerState1, PlayerState2, PlayerState3, PlayerState4}
import de.htwg.se.malefiz.model.cellComponent.{BlockedCell, PlayerCell}
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}
import de.htwg.se.malefiz.model.gameboardComponent.lastSaveInterface
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the base implementation of the controller.
 *
 *  @author sehirsig & franzgajewski
 */
class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer with 4 Players" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      "from the offset" in {
        controller.gameStatus should be(WELCOME)
        controller.playerStatus should be(PlayerState1)
        controller.moveCounter should be(0)
      }
      "get the pure Cell" in {
        controller.getpureCell("BlockedCell") should be (BlockedCell)
      }
      "change blockStrategy to replace" in {
        controller.setBlockStrategy("replace")
        controller.gameStatus should be(WELCOME)
      }
      "change blockStrategy to remove" in {
        controller.setBlockStrategy("remove")
        controller.gameStatus should be(WELCOME)
      }
     "when adding player" in {
       controller.addPlayerName("Eins")
       controller.game.getPlayerNumber() should be(1)
       controller.gameStatus should be(IDLE)
     }
     "when adding another player" in {
       controller.addPlayerName("Zwei")
       controller.game.getPlayerNumber() should be(2)
       controller.gameStatus should be(READY1)
     }
     "when adding 4 players" in {
       controller.addPlayerName("Drei")
       controller.addPlayerName("Vier")
       controller.game.getPlayerNumber() should be(4)
       controller.gameStatus should be(READY2)
     }
     "when adding more than 4 players" in {
       controller.addPlayer()
       controller.game.getPlayerNumber() should be(4)
     }
      "when starting game" in {
        controller.startGame()
        controller.gameStatus should be(PLAYING)
        controller.playerStatus should be(PlayerState1)
      }
      "when rolling dice" in {
        controller.rollDice() should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
      "when as Player 1" in {
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState3)
      }
      "when as Player 3" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState4)
      }
      "when as Player 4" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState3)
      }
      "when as Player 3 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState4)
      }
      "when as Player 4 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 6
        controller.move("s", 1)
        controller.move("s", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 1
        controller.move("s", 1)
        controller.playerStatus should be(PlayerState3)
      }
    }
    "observed by an Observer with 3 Players" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      "from the offset" in {
        controller.gameStatus should be(WELCOME)
        controller.playerStatus should be(PlayerState1)
        controller.moveCounter should be(0)
      }
      "change blockStrategy to replace" in {
        controller.setBlockStrategy("replace")
        controller.gameStatus should be(WELCOME)
      }
      "change blockStrategy to remove" in {
        controller.setBlockStrategy("remove")
        controller.gameStatus should be(WELCOME)
      }
      "when adding player" in {
        controller.addPlayerName("Eins")
        controller.game.getPlayerNumber() should be(1)
        controller.gameStatus should be(IDLE)
      }
      "when adding another player" in {
        controller.addPlayerName("Zwei")
        controller.game.getPlayerNumber() should be(2)
        controller.gameStatus should be(READY1)
      }
      "when adding 3 players" in {
        controller.addPlayerName("Drei")
        controller.game.getPlayerNumber() should be(3)
        controller.gameStatus should be(READY1)
      }
      "when starting game" in {
        controller.startGame()
        controller.gameStatus should be(PLAYING)
        controller.playerStatus should be(PlayerState1)
      }
      "when rolling dice" in {
        controller.rollDice() should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
      "when as Player 1" in {
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState3)
      }
      "when as Player 3" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState3)
      }
      "when as Player 3 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("s", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 6
        controller.move("s", 1)
        controller.move("s", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 1
        controller.move("s", 1)
        controller.playerStatus should be(PlayerState3)
      }
    }
    "observed by an Observer with 2 Players" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      "from the offset" in {
        controller.gameStatus should be(WELCOME)
        controller.playerStatus should be(PlayerState1)
        controller.moveCounter should be(0)
      }
      "change blockStrategy to replace" in {
        controller.setBlockStrategy("replace")
        controller.gameStatus should be(WELCOME)
      }
      "change blockStrategy to remove" in {
        controller.setBlockStrategy("remove")
        controller.gameStatus should be(WELCOME)
      }
      "when adding player" in {
        controller.addPlayerName("Eins")
        controller.game.getPlayerNumber() should be(1)
        controller.gameStatus should be(IDLE)
      }
      "when adding another player" in {
        controller.addPlayerName("Zwei")
        controller.game.getPlayerNumber() should be(2)
        controller.gameStatus should be(READY1)
      }
      "when starting game" in {
        controller.startGame()
        controller.gameStatus should be(PLAYING)
        controller.playerStatus should be(PlayerState1)
      }
      "when rolling dice" in {
        controller.rollDice() should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
      "when as Player 1" in {
        controller.selectFigure(1)
        controller.moveCounter = 4
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.move("a", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("d", 1)
        controller.move("undo", 1)
        controller.move("redo", 1)
        controller.move("undo", 1)
        controller.move("a", 1)
        controller.move("a", 1)
        controller.move("w", 1)
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 2nd Move kick" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 1
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 2nd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 5
        controller.move("w", 1)
        controller.move("skip", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when as Player 1 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 6
        controller.move("s", 1)
        controller.move("s", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.move("d", 1)
        controller.playerStatus should be(PlayerState2)
      }
      "when as Player 2 3rd Move" in {
        controller.rollDice()
        controller.selectFigure(1)
        controller.moveCounter = 1
        controller.move("w", 1)
        controller.playerStatus should be(PlayerState1)
      }
      "when Player 2 Wins" in {
        controller.gameboard = controller.gameboard.movePlayer((1,9), PlayerCell(2))
        controller.checkWin()
        controller.gameWon should be (true,"Zwei")
      }
      "when Player 2 Fin but not Won" in {
        controller.gameWon = (false, "")
        controller.moveCounter = 0
        controller.move("p", 1)
      }
      "save a gameboard" in {
        controller.save
      }
      "load a gameboard" in {
        controller.load
      }
      "reset a game" in {
        val injector = Guice.createInjector(new MalefizModule)
        val saGe = injector.instance[lastSaveInterface]
        controller.resetGame()
        controller.gameStatus should be (WELCOME)
        controller.savedGame should be (saGe)
        controller.gameWon should be (false,"")
        controller.selectedFigNum should be (0)
        controller.playerStatus should be (PlayerState1)
      }
      "create Debug Player 1" in {
        controller.addPlayerDEBUGWINTEST("Eins")
      }
      "create Debug Player 2" in {
        controller.addPlayerDEBUGWINTEST("Zwei")
      }
      "create Debug Player 3" in {
        controller.addPlayerDEBUGWINTEST("Drei")
      }
      "create Debug Player 4" in {
        controller.addPlayerDEBUGWINTEST("Vier")
      }
      "Use the debugDice" in {
        controller.debugDice()
        controller.moveCounter should be (1)
      }
    }
  }
}
