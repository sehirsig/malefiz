package de.htwg.se.malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameSpec extends AnyWordSpec with Matchers {
  "A Game" when { "new" should {
    val player1 = Player("alice", 1, (14,3))
    val player2 = Player("bob", 2, (14,7))
    val game = Game(Vector(player1,player2))
    "have a quantity"  in {
      game.getPlayerNumber() should be(2)
    }
    "have players" in {
      game.players(0).name should be("alice")
    }
  }}
}
