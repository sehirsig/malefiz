package de.htwg.se.malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class CellSpec extends AnyWordSpec with Matchers {
  "A Cell" when {
    "Free" should {
    "be displayed as \"  \"" in {
      FreeCell.toString should be("O ")
    }
  }
    "empty" should {
    "be displayed as \"  \"" in {
      InvalidCell.toString should be("  ")
    }
  }
    "Secure" should {
    "be displayed as \"O \"" in {
      SecureCell.toString should be("O ")
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

  }
}
