package de.htwg.se.malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player 1" when { "new" should {
    val player = Player("Your Name", 1, (14,7))
    "have a name"  in {
      player.name should be("Your Name")
    }
    "should unapply" in {
      Player.unapply(player).get should be("Your Name", 1, (14,7))
    }
    "have a nice String representation" in {
      player.toString should be("1 Your Name")
    }
    "have a starting position" in {
      player.startingPos should be((14,7))
    }
    "have no figure" in {
      player.figures should be(Array[Gamefigure]())
      player.figures.length should be(0)
    }
    "have a figure" in {
      player.addFigure()
      player.figures.length should be(1)
      player.figures(0).pos should be((14,7))
    }
    "should update figure position" in {
      player.figures(0) = player.figures(0).updatePos(15,7)
      player.figures(0).pos should be((15,7))
    }
  }}
}
