package de.htwg.se.malefiz.model

import org.scalatest._
class CellSpec extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val cell = Cell()
    "have a nice String representation" in {
      cell.toString should be("  ")
    }
    "have cellStatus = O when set free" in {
      cell.setFree().toString() should be("O ")
    }
    "have cellStatus = X when set blocked" in {
      cell.setBlocked().toString() should be("X ")
    }
    "have cellStatus = O when set secure" in {
      cell.setSecure().toString() should be("O ")
    }
    "have cellStatus = G when set to goal" in {
      cell.setGoal().toString() should be("G ")
    }
  }}
}
