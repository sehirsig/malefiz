package de.htwg.se.malefiz.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for the UndoManager class.
 *
 *  @author sehirsig & franzgajewski
 */
class UndoManagerSpec  extends AnyWordSpec with Matchers {
  "A UndoManager " should {
    val undoManager = new UndoManager
    "do a step in doStep" in {
      val cmd = new testCommand
      undoManager.doStep(cmd)
      cmd.saved should be(1)
    }
    "do a undo in undoStep" in {
      val cmd = new testCommand
      undoManager.doStep(cmd)
      undoManager.undoStep
      cmd.saved should be(0)
    }
    "do a redo in redoStep" in {
      val cmd = new testCommand
      undoManager.doStep(cmd)
      undoManager.undoStep
      undoManager.redoStep
      cmd.saved should be(1)
    }
    "has working Stacks" in {
      val cmd = new testCommand
      undoManager.doStep(cmd)
      undoManager.doStep(cmd)
      undoManager.doStep(cmd)
      undoManager.doStep(cmd)
      undoManager.doStep(cmd)
      undoManager.doStep(cmd)
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.undoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      undoManager.redoStep
      cmd.saved should be(6)
    }
  }
}