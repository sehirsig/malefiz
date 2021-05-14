package de.htwg.se.malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when { "no parameter" should {
    val cell = InvalidCell
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
    }
  }
    "empty" should {
    val cell = InvalidCell
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
    }
  }
    "free" should {
    val cell = FreeCell
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
    }
      "walkable" in {
        cell.isWalkable should be(true)
      }
  }
    "secure" should {
    val cell = SecureCell
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
    }
  }
    "blocked" should {
    val cell = BlockedCell
    "be displayed as \"X \"" in {
      cell.toString should be("X ")
    }
  }
    "the goal" should {
    val cell = GoalCell
    "be displayed as \"G \"" in {
      cell.toString should be("G ")
    }
  }
    "if is Walkable" should {
      val cell = FreeCell
      "should be walkable" in {
        cell.isWalkable should be(true)
      }
      val cellnot = InvalidCell
      "should not be walkabe" in {
        cellnot.isWalkable should be(false)
      }
    }
  }
}
