package de.htwg.se.malefiz.model.gameboardComponent.gameboardStubImpl

import de.htwg.se.malefiz.model.cellComponent.InvalidCell
import de.htwg.se.malefiz.model.playerComponent.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the stub implementation of the Gameboard class.
 *
 *  @author sehirsig & franzgajewski
 */
class GameboardSpec extends AnyWordSpec with Matchers {
  "A Mock Gameboard is the playingfield of Malefiz. A Mock Gameboard" when { //
    val spielbrett = new Gameboard(2, 2)
    val player = Player.apply("Te", 1, (0, 0))
    "all functions do nothing" should {
      "in walkUp" in {
        spielbrett.walkUp(spielbrett, player, (0,1), 1, 2)
      }
      "in walkDown" in {
        spielbrett.walkDown(spielbrett, player,(0,0), 1,2)
      }
      "in walkLeft" in {
      spielbrett.walkLeft(spielbrett, player,(0,0), 1,2)
      }
      "in walkRight" in {
      spielbrett.walkRight(spielbrett, player,(0,0), 1,2)
      }
      "in setBlockStrategy" in {
      spielbrett.setBlockStrategy("Invalid")
      }
      "in getStandardXYsize" in {
      spielbrett.getStandardXYsize
      }
      "in diceRoll" in {
      spielbrett.diceRoll
      }
      "in goDown" in {
      spielbrett.goDown(0,0)
      }
      "in goUp" in {
      spielbrett.goUp(0,0)
      }
      "in goRight" in {
      spielbrett.goRight(0,0)
      }
      "in getCell" in {
      spielbrett.getCell("")
      }
      "in cellString" in {
      spielbrett.cellString(0,0)
      }
      "in goLeft" in {
      spielbrett.goLeft(0,0)
      }
      "in checkPlayerOnGoal" in {
      spielbrett.checkPlayerOnGoal
      }
      "in cell" in {
      spielbrett.cell(0,0)
      }
      "in replaceCell" in {
      spielbrett.replaceCell(0,0,InvalidCell)
      }
      "newGBStandardSize()" in {
        spielbrett.newGBStandardSize should be (spielbrett)
      }
      "in replaceCell Fail" in {
        spielbrett.replaceCell(0,-1,InvalidCell)
      }
      "in getStringOfCell" in {
        spielbrett.getStringOfCell(InvalidCell) should be ("")
      }
      "in getP1Base" in {
        spielbrett.getP1Base should be ((0,0))
      }
      "in getP2Base" in {
        spielbrett.getP2Base should be ((0,0))
      }
      "in getP3Base" in {
        spielbrett.getP3Base should be ((0,0))
      }
      "in getP4Base" in {
        spielbrett.getP4Base should be ((0,0))
      }
      "in getGoalBase" in {
        spielbrett.getGoalBase should be ((0,0))
      }
    }
  }
}
