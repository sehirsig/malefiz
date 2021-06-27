package de.htwg.se.malefiz.controller.controllerComponent.controllerStubImpl

import de.htwg.se.malefiz.model.cellComponent.InvalidCell
import de.htwg.se.malefiz.model.gameboardComponent.gameboardStubImpl.Gameboard
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the stub implementation of the controller.
 *
 *  @author sehirsig & franzgajewski
 */
class ControllerSpec extends AnyWordSpec with Matchers {
  "A Mock Controller" when {
    var con = new Controller(new Gameboard(2, 2))
    "should do nothing" should {
      "in gameStatus" in {
        con.gameStatus = con.gameStatus
      }
      "in playerStatus" in {
        con.playerStatus = con.playerStatus
      }
      "in moveCounter" in {
        con.moveCounter = 1
      }
      "in builder" in {
        con.builder
      }
      "in game" in {
        con.game = con.game
      }
      "in gameWon" in {
        con.gameWon = (true,"")
      }
      "in savedGame" in {
        con.savedGame = con.savedGame
      }
      "in selectedFigNum" in {
        con.selectedFigNum = 1
      }
      "in resetGame" in {
        con.resetGame()
      }
      "in selectFigure" in {
        con.selectFigure(0)
      }
      "in addPlayer" in {
        con.addPlayer()
      }
      "in addPlayerName" in {
        con.addPlayerName("")
      }
      "in startGame" in {
        con.startGame()
      }
      "in boardToString" in {
        con.boardToString()
      }
      "in rollDice" in {
        con.rollDice()
      }
      "in checkWin" in {
        con.checkWin()
      }
      "in setBlockStrategy" in {
        con.setBlockStrategy("")
      }
      "in emptyMan" in {
        con.emptyMan
      }
      "in undoAll" in {
        con.undoAll
      }
      "in undo" in {
        con.undo
      }
      "in redo" in {
        con.redo
      }
      "in move" in {
        con.move("",0)
      }
      "in getpureCell" in {
        con.getpureCell("") should be (InvalidCell)
      }
      "in setupGame" in {
        con.setupGame()
      }
      "in save" in {
        con.save
      }
      "in load" in {
        con.load
      }
      "in addPlayerDEBUGWINTEST" in {
        con.addPlayerDEBUGWINTEST("")
      }
      "in debugDice" in {
        con.debugDice
      }
    }
  }
}