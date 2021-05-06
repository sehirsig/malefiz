package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model.Gameboard
import de.htwg.se.malefiz.model.properties.Settings
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.malefiz.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val set = Settings()
      val gameboard = new Gameboard(set.xDim, set.yDim)
      val controller = Controller(gameboard)
      val observer = new Observer {
        var updated: Boolean = false
        override def update: Unit = updated = true
      }
      controller.add(observer)
      "when rolling dice" in {
        controller.rollDice() should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
//      "when moving up" in {
//        controller.moveUp()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving down" in {
//        controller.moveDown()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving left" in {
//        controller.moveLeft()
//        observer.updated should be(true)
//        observer.updated = false
//      }
//      "when moving right" in {
//        controller.moveRight()
//        observer.updated should be(true)
//        observer.updated = false
//      }
    }
  }
}
