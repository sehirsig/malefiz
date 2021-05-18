package de.htwg.se.malefiz.model
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GamefigureSpec extends AnyWordSpec with Matchers {
  "A Gamefigure" when { "new Player 1" should {
    val player = Player("Your Name", 1)
    val figur = Gamefigure(1, player)
    "should unapply" in {
      Gamefigure.unapply(figur).get should be(1, Player("Your Name", 1))
    }
    "have a nice String representation" in {
      figur.toString should be("1")
    }
    "should return the Player" in {
      figur.getPlayer should be(player)
    }
    "should return the number" in {
      figur.getNumber should be(1)
    }

  }}
}
