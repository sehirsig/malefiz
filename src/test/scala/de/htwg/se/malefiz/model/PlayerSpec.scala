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
    "should have correct color" in {
      player.getColour should be(Console.GREEN)
    }
    "have a nice String representation" in {
      player.toString should be("0 Your Name")
    }
  }}

  "A Player 1" when { "new" should {
    val player = Player("Your Name", 1)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "should unapply" in {
      Player.unapply(player).get should be("Your Name", 1)
    }
    "should have correct color" in {
      player.getColour should be(Console.RED)
    }
    "have a nice String representation" in {
      player.toString should be("1 Your Name")
    }
  }}

  "A Player 2" when { "new" should {
    val player = Player("Your Name", 2)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "should unapply" in {
      Player.unapply(player).get should be("Your Name", 2)
    }
    "should have correct color" in {
      player.getColour should be(Console.BLUE)
    }
    "have a nice String representation" in {
      player.toString should be("2 Your Name")
    }
  }}

  "A Player 3" when { "new" should {
    val player = Player("Your Name", 3)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "should unapply" in {
      Player.unapply(player).get should be("Your Name", 3)
    }
    "should have correct color" in {
      player.getColour should be(Console.YELLOW)
    }
    "have a nice String representation" in {
      player.toString should be("3 Your Name")
    }
  }}


}
