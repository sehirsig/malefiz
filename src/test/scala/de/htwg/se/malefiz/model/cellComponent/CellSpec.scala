package de.htwg.se.malefiz.model.cellComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere Cell-Klasse.
 *
 *  @author sehirsig & franzgajewski
 */
class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when {
    "Free" should {
    "be displayed as \"  \"" in {
      FreeCell.toString should be("O ")
    }
      "be walkable" in {
        FreeCell.isWalkable should be (true)
      }
  }
    "empty" should {
    "be displayed as \"  \"" in {
      InvalidCell.toString should be("  ")
    }
      "not be walkable" in {
        InvalidCell.isWalkable should be (false)
      }
  }
    "Secure" should {
    "be displayed as \"O \"" in {
      SecureCell.toString should be("O ")
    }
      "be walkable" in {
        SecureCell.isWalkable should be (true)
      }
  }
    "blocked" should {
    "be displayed as \"X \"" in {
      BlockedCell.toString should be("X ")
    }
  }
    "the goal" should {
    "be displayed as \"G \"" in {
      GoalCell.toString should be("G ")
    }
  }
    "the playercell" should {
      "be displayed as \"1 \"" in {
        PlayerCell(1).toString should be("1 ")
      }
      "return the Players Number" in {
        PlayerCell(1).getPlayer should be (1)
      }
    }
  }
}
