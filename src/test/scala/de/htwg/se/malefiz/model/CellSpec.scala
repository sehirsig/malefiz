package de.htwg.se.malefiz.model

import org.scalatest._
class CellSpec extends WordSpec with Matchers {
  "A Cell" when { "no parameter" should {
    val cell = Cell()
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
    }
  }
    "empty" should {
    val cell = Cell("  ")
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
    }
  }
    "free" should {
    val cell = Cell("O ")
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
    }
      "walkable" in {
        cell.isWalkable should be(true)
      }
  }
    "secure" should {
    val cell = Cell("O ")
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
    }
  }
    "blocked" should {
    val cell = Cell("X ")
    "be displayed as \"X \"" in {
      cell.toString should be("X ")
    }
  }
    "the goal" should {
    val cell = Cell("G ")
    "be displayed as \"G \"" in {
      cell.toString should be("G ")
    }
  }
  }
}
