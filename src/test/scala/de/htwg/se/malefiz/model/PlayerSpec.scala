package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player 0" when { "new" should {
    val player = Player("Your Name", 0)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "should unapply" in {
      Player.unapply(player).get should be("Your Name", 0)
    }
    "have a nice String representation" in {
      player.toString should be("0 Your Name")
    }
  }}
}
