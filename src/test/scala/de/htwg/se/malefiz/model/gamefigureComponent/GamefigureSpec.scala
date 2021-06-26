package de.htwg.se.malefiz.model.gamefigureComponent

import de.htwg.se.malefiz.model.playerComponent.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere Gamefigure Klasse.
 *
 *  @author sehirsig & franzgajewski
 */
class GamefigureSpec extends AnyWordSpec with Matchers {
  "A Gamefigure" when {
    "new Player 1" should {
      val player = Player("Your Name", 1, (1,1))
      val figur = Gamefigure(1, 1, player, player.startingPos)
      "should unapply" in {
        Gamefigure.unapply(figur).get should be(1, 1, Player("Your Name", 1, (1,1)), player.startingPos)
      }
      "have a nice String representation" in {
        figur.toString should be("1 ")
      }
      "should return the Player" in {
        figur.getPlayer should be(player)
      }
      "should return the number" in {
        figur.getNumber should be(1)
      }
      "should give position" in {
        figur.pos should be((1,1))
      }
      "should update position" in {
        val figur2 = figur.updatePos((2,2))
        figur2.pos should be ((2,2))
      }
    }
  }
}
