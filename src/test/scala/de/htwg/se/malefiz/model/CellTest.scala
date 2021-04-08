package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CellTest extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val cell = Cell("")
    "have a nice String representation" in {
      cell.toString should be("  ")
    }
    "bla" in {
      cell.setFree() should be("O ")
    }
  }}
}
