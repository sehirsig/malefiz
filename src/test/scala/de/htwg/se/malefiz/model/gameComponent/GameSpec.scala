package de.htwg.se.malefiz.model.gameComponent

import de.htwg.se.malefiz.model.playerComponent.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the Game class.
 *
 *  @author sehirsig & franzgajewski
 */
class GameSpec extends AnyWordSpec with Matchers {
  "A Game" when {
    "new" should {
      val player1 = Player("alice", 1, (14,3))
      val player2 = Player("bob", 2, (14,7))
      val game = Game(Vector(player1,player2))
      "have a quantity"  in {
        game.getPlayerNumber() should be(2)
      }
      "have players" in {
        game.players(0).name should be("alice")
      }
      "add player" in {
        val player3 = Player("charly", 3, (14,11))
        val game2 = game.addPlayer(player3)
        game2.getPlayerNumber() should be(3)
      }
    }
  }
}