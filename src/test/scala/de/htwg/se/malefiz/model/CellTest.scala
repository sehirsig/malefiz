package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CellTest extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val cell = Cell()
    "have a nice String representation" in {
      cell.toString should be("  ")
    }
    "have cellStatus = O when set free" in {
      cell.setFree()
      cell.toString() should be("O ")
    }
    "have cellStatus = B when set blocked" in {
      cell.setBlocked()
      cell.toString() should be("X ")
    }
    "have cellStatus = O when set secure" in {
      cell.setSecure()
      cell.toString() should be("O ")
    }
    "have cellStatus = G when set to goal" in {
      cell.setGoal()
      cell.toString() should be("G ")
    }
  }}
}
