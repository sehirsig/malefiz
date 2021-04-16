package de.htwg.se.malefiz.model
import org.scalatest._

class GamefigureSpec extends WordSpec with Matchers {
  "A Gamefigure" when { "new Player 1" should {
    val player = Player("Your Name", 1)
    val figur = Gamefigure(1, player)
    "should unapply" in {
      Gamefigure.unapply(figur).get should be(1, Player("Your Name", 1))
    }
    "have a nice String representation" in {
      figur.toString should be("1")
    }
  }}
}
