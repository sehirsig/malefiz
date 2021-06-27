package de.htwg.se.malefiz.controller.controllerComponent

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the PlayerState class.
 *
 *  @author sehirsig & franzgajewski
 */
class PlayerStateSpec extends AnyWordSpec with Matchers {
  "A PlayerState" when {
    "should" should {
      var playerStatus: PlayerState = PlayerState1
      "iterate correctly with 2 Players" in {
        playerStatus.getCurrentPlayer should be (1)
        playerStatus = playerStatus.nextPlayer(2)
        playerStatus.getCurrentPlayer should be (2)
        playerStatus = playerStatus.nextPlayer(2)
        playerStatus.getCurrentPlayer should be (1)
      }
      "iterate correctly with 3 Players" in {
        playerStatus = playerStatus.nextPlayer(3)
        playerStatus.getCurrentPlayer should be (2)
        playerStatus = playerStatus.nextPlayer(3)
        playerStatus.getCurrentPlayer should be (3)
        playerStatus = playerStatus.nextPlayer(3)
        playerStatus.getCurrentPlayer should be (1)
      }
      "iterate correctly with 4 Players" in {
        playerStatus = playerStatus.nextPlayer(4)
        playerStatus.getCurrentPlayer should be (2)
        playerStatus = playerStatus.nextPlayer(4)
        playerStatus.getCurrentPlayer should be (3)
        playerStatus = playerStatus.nextPlayer(4)
        playerStatus.getCurrentPlayer should be (4)
        playerStatus = playerStatus.nextPlayer(4)
        playerStatus.getCurrentPlayer should be (1)
      }
    }
  }
}