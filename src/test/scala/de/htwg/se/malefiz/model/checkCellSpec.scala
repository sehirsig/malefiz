package de.htwg.se.malefiz.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.malefiz.model.checkCell
import de.htwg.se.malefiz.model.properties.Settings

class checkCellSpec extends AnyWordSpec with Matchers {
  "checkShell" when {
    var gb = new Gameboard(Settings().xDim, Settings().yDim)
    var player1 = Player("Erster", 1, (15, 3))
    var player2 = Player("Zweiter", 2, (14, 5))
    var player3 = Player("Dritter", 3, (14, 6))
    var player4 = Player("Vierter", 4, (14, 7))
    player1.addFigure(1)
    player1.addFigure(2)
    player1.addFigure(3)
    player1.addFigure(4)
    player1.addFigure(5)
    player2.addFigure(1)
    player3.addFigure(1)
    player4.addFigure(1)
    "walking up" should {
      "be true " in {
        checkCell.walkUp(gb, player1, (15, 3), 1, 2) should not be ((false, gb))
      }
      "be false " in {
        checkCell.walkUp(gb, player1, (14, 3), 1, 2) should be ((false, gb))
      }
    }
    "walking left" should {
      "be true " in {
        checkCell.walkLeft(gb, player1, (14, 3), 1, 2) should not be ((false, gb))
      }
      "be false " in {
        checkCell.walkLeft(gb, player1, (1, 1), 1, 2) should be ((false, gb))
      }
    }
    "walking right" should {
      "be true " in {
        checkCell.walkRight(gb, player1, (14, 3), 1, 2) should not be ((false, gb))
      }
      "be false " in {
        checkCell.walkRight(gb, player1, (0, 0), 1, 2) should be ((false, gb))
      }
    }
    "walking down" should {
      "be true " in {
        checkCell.walkDown(gb, player1, (12, 5), 1, 2) should not be ((false, gb))
      }
      "be false " in {
        checkCell.walkDown(gb, player1, (0, 0), 1, 2) should be ((false, gb))
      }
    }
    "is walkable" should {
      "be true " in {
        checkCell.isWalkable(gb, (2,2), 1, 1) should be (true)
        checkCell.isWalkable(gb, (14,3), 1, 1) should be (true)
        checkCell.isWalkable(checkCell.walkUp(gb, player1, (15,3), 1, 1)._2, (14,3), 1, 2) should be (true)
        checkCell.isWalkable(gb, (14,5), 1, 1) should be (true)
        checkCell.isWalkable(gb, (14,6), 1, 1) should be (true)
        checkCell.isWalkable(gb, (14,7), 1, 1) should be (true)
        checkCell.isWalkable(gb, (2,9), 1, 1) should be (true)
        checkCell.isWalkable(gb, (1,9), 1, 1) should be (true)
      }
      "be false " in {
        checkCell.isWalkable(gb, (15,3), 1, 1) should be (false)
      }
    }
    "is getnextcell" should {
      "return the old gameboard" in {
        checkCell.getNextCell(gb, gb, (15,3), 1, 1) should be (gb)
        checkCell.getNextCell(gb, gb, (15,3), 1, 2) should be (gb)
        checkCell.getNextCell(gb, gb, (14,5), 2, 1) should be (gb)
        checkCell.getNextCell(gb, gb, (14,6), 2, 1) should be (gb)
        checkCell.getNextCell(gb, gb, (14,7), 2, 1) should be (gb)
        checkCell.getNextCell(gb, gb, (2,9), 2, 1) should be (gb)
      }
    }
  }
}
