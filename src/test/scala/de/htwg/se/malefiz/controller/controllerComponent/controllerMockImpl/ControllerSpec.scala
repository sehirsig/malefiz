package de.htwg.se.malefiz.controller.controllerComponent.controllerMockImpl

import de.htwg.se.malefiz.model.gameboardComponent.gameboardMockImpl.Gameboard
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Mock Controller" when {
    var con = new Controller(new Gameboard(2, 2))
    "used" should {
      "do nothing" in {
        con.gameStatus = con.gameStatus
        con.playerStatus = con.playerStatus
        con.moveCounter = 1
        con.builder
        con.game = con.game
        con.gameWon = (true,"")
        con.savedGame = con.savedGame
        con.selectedFigNum = 1
        con.resetGame()
        con.selectFigure(0)
        con.addPlayer()
        con.addPlayerName("")
        con.startGame()
        con.boardToString()
        con.rollDice()
        con.checkWin()
        con.setBlockStrategy("")
        con.emptyMan
        con.undoAll
        con.undo
        con.redo
        con.move("",0)
      }
    }
  }
}
