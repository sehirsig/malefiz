package de.htwg.se.malefiz.model
import org.scalatest._

class GamefigureSpec extends WordSpec with Matchers {
  "A Gamefigure" when { "new Player 0" should {
    val player = Player("Your Name", 0)
    val figur = Gamefigure(0, player)
    "should unapply" in {
      Gamefigure.unapply(figur).get should be(0, Player("Your Name", 0))
    }
    "have a nice String representation" in {
      figur.toString should be("0")
    }
  }}
}
