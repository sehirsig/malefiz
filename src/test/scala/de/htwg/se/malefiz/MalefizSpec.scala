package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl
import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

import java.io.ByteArrayInputStream

/** Test class for the Malefiz main class.
 *
 *  @author sehirsig & franzgajewski
 */
class MalefizSpec extends AnyWordSpec with Matchers {
  "The Malefiz main class" should {
    val controller = new Controller(new Gameboard(Settings().xDim, Settings().yDim))
    val tui = new TUI(controller)
    "run normally and Quit" in {
      val in = new ByteArrayInputStream("Q".getBytes)
      Console.withIn(in) { Malefiz.main(Array()) }
    }
    "create the controller " in {
      controller should be (controllerBaseImpl.Controller(new Gameboard(Settings().xDim, Settings().yDim)))
    }
    "create the tui" in {
      tui should be (TUI(controllerBaseImpl.Controller(new Gameboard(Settings().xDim, Settings().yDim))))
    }
  }
}