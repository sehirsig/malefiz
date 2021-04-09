package de.htwg.se.malefiz.model

import org.scalatest._
class CellSpec extends WordSpec with Matchers {
  "A Cell" when { "no parameter" should {
    val cell = Cell()
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
      cell.cellStatusValidation should be(1)
      cell.secureStatus should be(false)
    }
  }}
  "A Cell" when { "empty" should {
    val cell = Cell("  ")
    "be displayed as \"  \"" in {
      cell.toString should be("  ")
      cell.cellStatusValidation should be(1)
      cell.secureStatus should be(false)
    }
  }}
  "A Cell" when { "free" should {
    val cell = Cell("O ")
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
      cell.cellStatusValidation should be(2)
      cell.secureStatus should be(false)
    }
  }}
  "A Cell" when { "secure" should {
    val cell = Cell("O ", true)
    "be displayed as \"O \"" in {
      cell.toString should be("O ")
      cell.cellStatusValidation should be(2)
      cell.secureStatus should be(true)
    }
  }}
  "A Cell" when { "blocked" should {
    val cell = Cell("X ")
    "be displayed as \"X \"" in {
      cell.toString should be("X ")
      cell.cellStatusValidation should be(3)
      cell.secureStatus should be(false)
    }
  }}
  "A Cell" when { "the goal" should {
    val cell = Cell("G ")
    "be displayed as \"G \"" in {
      cell.toString should be("G ")
      cell.cellStatusValidation should be(4)
      cell.secureStatus should be(false)
    }
  }}
  "A Cell" when { "invalid" should {
    val cell = Cell("abc")
    "be displayed as \"not a valid cell\"" in {
      cell.toString should be("not a valid cell")
      cell.cellStatusValidation should be(0)
      cell.secureStatus should be(false)
    }
  }}
}
