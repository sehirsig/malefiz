package de.htwg.se.malefiz.model.gameboardComponent.gameboardMockImpl


import de.htwg.se.malefiz.model.cellComponent.InvalidCell
import de.htwg.se.malefiz.model.playerComponent.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameboardSpec extends AnyWordSpec with Matchers {
  "A Mock Gameboard is the playingfield of Malefiz. A Mock Gamebeoard" when { //
    val spielbrett = new Gameboard(2, 2)
    "all functions used properly" should {
      "do nothing" in {
        spielbrett.walkUp(spielbrett, Player.apply("Te",1,(0,0)),(0,0), 1,2)
        spielbrett.walkDown(spielbrett, Player.apply("Te",1,(0,0)),(0,0), 1,2)
        spielbrett.walkLeft(spielbrett, Player.apply("Te",1,(0,0)),(0,0), 1,2)
        spielbrett.walkRight(spielbrett, Player.apply("Te",1,(0,0)),(0,0), 1,2)
        spielbrett.setBlockStrategy("Invalid")
        spielbrett.cell(1,1)
        spielbrett.getStandardXYsize
        spielbrett.diceRoll
        spielbrett.goDown(0,0)
        spielbrett.goUp(0,0)
        spielbrett.goRight(0,0)
        spielbrett.goLeft(0,0)
        spielbrett.checkPlayerOnGoal
        spielbrett.cell(0,0)
        spielbrett.replaceCell(0,0,InvalidCell)
      }
    }
  }
}
