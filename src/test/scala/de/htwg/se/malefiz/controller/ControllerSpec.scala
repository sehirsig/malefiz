package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.{Game, Gameboard}
import de.htwg.se.malefiz.model.properties.Settings
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.malefiz.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "from the offset" in {
        controller.gameStatus should be(IDLE)
        controller.playerStatus should be(PLAYER0)
        controller.moveCounter should be(0)
      }
      "when adding player" in {
        controller.addPlayer()
        controller.game.getPlayers() should be(1)
        controller.gameStatus should be(IDLE)
      }
      "when adding another player" in {
        controller.addPlayer()
        controller.game.getPlayers() should be(2)
        controller.gameStatus should be(READY1)
      }
      "when adding 4 players" in {
        controller.addPlayer()
        controller.addPlayer()
        controller.game.getPlayers() should be(4)
        controller.gameStatus should be(READY2)
      }
      "when starting game" in {
        controller.startGame()
        controller.gameStatus should be(PLAYING)
        controller.playerStatus should be(PLAYER1)
      }
      "when rolling dice" in {
        controller.rollDice() should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
      "when moving" in {

      }
      "when iterating through" in {
        controller.playerStatus = PLAYER1
        controller.nextPlayer()
        controller.playerStatus should be(PLAYER2)
        controller.nextPlayer()
        controller.playerStatus should be(PLAYER3)
        controller.nextPlayer()
        controller.playerStatus should be(PLAYER4)
        controller.nextPlayer()
        controller.playerStatus should be(PLAYER1)
        controller.playerStatus = PLAYER0
        controller.nextPlayer()
        controller.playerStatus should be(PLAYER1)
      }
//      "when moving up" in {
//        controller.moveUp()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving down" in {
//        controller.moveDown()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving left" in {
//        controller.moveLeft()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving right" in {
//        controller.moveRight()
//        observer.updated should be(true)
//        observer.updated = false
//      }
    }
  }
}
