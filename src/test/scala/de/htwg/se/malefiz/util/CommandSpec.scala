package de.htwg.se.malefiz.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class testCommand extends Command {
  var saved:Int = 0

  override def doStep: Unit = saved += 1

  override def undoStep: Unit = saved -= 1

  override def redoStep: Unit = doStep
}

/** Test-Klasse für unsere Command Klasse.
 *
 *  @author sehirsig & franzgajewski
 */
class CommandSpec  extends AnyWordSpec with Matchers {
  "A Command " should {
    "do a step in doStep" in {
      val cmd = new testCommand
      cmd.doStep
      cmd.saved should be(1)
    }
    "do a undo in undoStep" in {
      val cmd = new testCommand
      cmd.doStep
      cmd.undoStep
      cmd.saved should be(0)
    }
    "do a redo in redoStep" in {
      val cmd = new testCommand
      cmd.doStep
      cmd.undoStep
      cmd.redoStep
      cmd.saved should be(1)
    }
  }
}
