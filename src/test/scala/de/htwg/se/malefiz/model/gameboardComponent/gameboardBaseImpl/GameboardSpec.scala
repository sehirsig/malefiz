package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

/** Test-Klasse für unsere Gameboard Klasse der Base-Implementierung.
 *
 *  @author sehirsig & franzgajewski
 */
class GameboardSpec extends AnyWordSpec with Matchers {
  "A Gameboard is the playingfield of Malefiz. A Gamebeoard" when {

    val settings = new Settings
    val spielbrett = new Gameboard(settings.yDim, settings.xDim)
    "created properly" should {
      val freeCells = Vector((2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 15),
        (2, 16), (2, 17), (3, 1), (3, 17), (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 10), (4, 11), (4, 12), (4, 13), (4, 14),
        (4, 15), (4, 16), (4, 17), (6, 7), (6, 8), (6, 10), (6, 11), (7, 7), (7, 11), (8, 5), (8, 6), (8, 8), (8, 9), (8, 10), (8, 12), (8, 13), (9, 5),
        (9, 13), (10, 3), (10, 4), (10, 5), (10, 6), (10, 7), (10, 8), (10, 9), (10, 10), (10, 11), (10, 12), (10, 13), (10, 14), (10, 15), (11, 3),
        (11, 7), (11, 11), (11, 15), (12, 2), (12, 3), (12, 4), (12, 6), (12, 7), (12, 8), (12, 10), (12, 11), (12, 12), (12, 14), (12, 15), (12, 16))

      val blockedCells = Vector((2, 9), (4, 9), (5, 9), (6, 9), (8, 7), (8, 11), (12, 1), (12, 5), (12, 9), (12, 13), (12, 17))

      val secureCells = Vector((13, 1), (13, 5), (13, 9), (13, 13), (13, 17), (14, 1), (14, 2), (14, 3), (14, 4), (14, 5), (14, 6), (14, 7),
        (14, 8), (14, 9), (14, 10), (14, 11), (14, 12), (14, 13), (14, 14), (14, 15), (14, 16), (14, 17))

      val goalCell = (1, 9)
      "should unapply" in {
        Gameboard.unapply(spielbrett).get should be(Vector.tabulate(Settings().yDim, Settings().xDim) {
          (row, col) => {
            if(Settings().blockedCells.contains(row, col)) {
              BlockedCell
            } else if (Settings().freeCells.contains(row, col)) {
              FreeCell
            } else if(Settings().secureCells.contains(row, col)) {
              SecureCell
            } else if((Settings().goalCell == (row, col))) {
              GoalCell
            } else if((Settings().start1.contains(row, col))) {
              Start1Cell
            } else if((Settings().start2.contains(row, col))) {
              Start2Cell
            } else if((Settings().start3.contains(row, col))) {
              Start3Cell
            } else if((Settings().start4.contains(row, col))) {
              Start4Cell
            }  else {
              InvalidCell
            }
          }
        })
      }
      "read the Cells with our String method" in {
        spielbrett.cell(1, 1) should be(InvalidCell)
        spielbrett.cell(2, 2) should be(FreeCell)
        spielbrett.cell(2, 9) should be(BlockedCell)
        spielbrett.cell(13, 1) should be(SecureCell)
        spielbrett.cell(1, 9) should be(GoalCell)
        spielbrett.cell(15, 15) should be(Start4Cell)
        spielbrett.cell(15, 11) should be(Start3Cell)
        spielbrett.cell(15, 7) should be(Start2Cell)
        spielbrett.cell(15, 3) should be(Start1Cell)
      }
      "read the Cells with Cells to String method" in {
        spielbrett.rows(1)(1).toString() should be("  ")
        spielbrett.rows(2)(2).toString() should be("O ")
        spielbrett.rows(2)(9).toString() should be("X ")
        spielbrett.rows(13)(1).toString() should be("O ")
        spielbrett.rows(1)(9).toString() should be("G ")
        spielbrett.rows(15)(15).toString() should be("T ")

      }
      "replace a Cell correctly and return a new Board" in {
        var newboard = spielbrett
        val returnedBoard = spielbrett.replaceCell(0, 0, BlockedCell)
        returnedBoard match {
          case Success(v) => newboard = v
          case Failure(f) => newboard
        }
        spielbrett.cell(0, 0) should be(InvalidCell)
        newboard.cell(0, 0) should be(BlockedCell)
      }
      "replace a Cell correctly with Tuple Coordinatesand return a new Board" in {
        val returnedBoard = spielbrett.movePlayer((0,0), BlockedCell)
        spielbrett.cell(0, 0) should be(InvalidCell)
        returnedBoard.cell(0, 0) should be(BlockedCell)
      }

      "build the exact String" in {
        spielbrett.toString should be
        ("""
           |                  G
           |  O O O O O O O O X O O O O O O O O
           |  O                               O
           |  O O O O O O O O X O O O O O O O O
           |                  X
           |              O O X O O
           |              O       O
           |          O O X O O O X O O
           |          O               O
           |      O O O O O O O O O O O O O
           |      O       O       O       O
           |  X O O O X O O O X O O O X O O O X
           |  O       O       O       O       O
           |  O O O O O O O O O O O O O O O O O
           |      T       T       T       T
           |      T       T       T       T
           |    T T T   T T T   T T T   T T T
           |
           |""".stripMargin)
      }
    }
    "used in getStringOfCell Function" should {
      "return FreeCell" in {
        spielbrett.getStringOfCell(FreeCell) should be ("FreeCell")
      }
      "return BlockedCell" in {
        spielbrett.getStringOfCell(BlockedCell) should be ("BlockedCell")
      }
      "return Start1Cell" in {
        spielbrett.getStringOfCell(Start1Cell) should be ("Start1Cell")
      }
      "return Start2Cell" in {
        spielbrett.getStringOfCell(Start2Cell) should be ("Start2Cell")
      }
      "return Start3Cell" in {
        spielbrett.getStringOfCell(Start3Cell) should be ("Start3Cell")
      }
      "return Start4Cell" in {
        spielbrett.getStringOfCell(Start4Cell) should be ("Start4Cell")
      }
      "return SecureCell" in {
        spielbrett.getStringOfCell(SecureCell) should be ("SecureCell")
      }
      "return GoalCell" in {
        spielbrett.getStringOfCell(GoalCell) should be ("GoalCell")
      }
      "return InvalidCell" in {
        spielbrett.getStringOfCell(InvalidCell) should be ("InvalidCell")
      }
      "return PlayerCell1" in {
        spielbrett.getStringOfCell(PlayerCell(1)) should be ("PlayerCell1")
      }
      "return PlayerCell2" in {
        spielbrett.getStringOfCell(PlayerCell(2)) should be ("PlayerCell2")
      }
      "return PlayerCell3" in {
        spielbrett.getStringOfCell(PlayerCell(3)) should be ("PlayerCell3")
      }
      "return PlayerCell4" in {
        spielbrett.getStringOfCell(PlayerCell(4)) should be ("PlayerCell4")
      }
      "return Broke InvalidCell" in {
        spielbrett.getStringOfCell(PlayerCell(5)) should be ("InvalidCell")
      }
    }

    "used in other Functions" should {
      "cellString()" in {
        spielbrett.cellString(0,0) should be ("InvalidCell")
      }
      "replaceCell()" in {
        spielbrett.replaceCell(43,44, InvalidCell) should not be (spielbrett)
      }
      "moveCell()" in {
        spielbrett.moveCell((0,0), InvalidCell) should be (spielbrett)
      }
      "newGBStandardSize()" in {
        spielbrett.newGBStandardSize should be (new Gameboard(18,19))
      }
      "in getP1Base" in {
        spielbrett.getP1Base should be (Settings().start1.head)
      }
      "in getP2Base" in {
        spielbrett.getP2Base should be (Settings().start2.head)
      }
      "in getP3Base" in {
        spielbrett.getP3Base should be (Settings().start3.head)
      }
      "in getP4Base" in {
        spielbrett.getP4Base should be (Settings().start4.head)
      }
      "in getGoalBase" in {
        spielbrett.getGoalBase should be (Settings().goalCell)
      }
      "in getCell(P1)" in {
        spielbrett.getCell("PlayerCell1") should be (PlayerCell(1))
      }
      "in getCell(P2)" in {
        spielbrett.getCell("PlayerCell2")  should be (PlayerCell(2))
      }
      "in getCell(P3)" in {
        spielbrett.getCell("PlayerCell3")  should be (PlayerCell(3))
      }
      "in getCell(P4)" in {
        spielbrett.getCell("PlayerCell4")  should be (PlayerCell(4))
      }

    }
  }
}

