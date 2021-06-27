package de.htwg.se.malefiz.util

/** Undo manager class.
 *
 *  @author sehirsig & franzgajewski
 */
class UndoManager {

  /** List of all doable steps. */
  private var undoStack: List[Command]= Nil

  /** List of all redoable steps. */
  private var redoStack: List[Command]= Nil

  /** Do one step.
   *
   *  @param command Command
   */
  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep
  }

  /** Undo one step. */
  def undoStep: Unit  = {
    undoStack match {
      case  Nil =>
      case head::stack => {
        head.undoStep
        undoStack=stack
        redoStack= head::redoStack
      }
    }
  }

  /** Redo one step. */
  def redoStep: Unit = {
    redoStack match {
      case Nil =>
      case head::stack => {
        head.redoStep
        redoStack=stack
        undoStack=head::undoStack
      }
    }
  }

  /** Empty steps entirely. */
  def emptyStacks: Unit = {
    undoStack = Nil
    redoStack = Nil
  }

  /** Execute all undos at once. */
  def undoAll: Unit = {
    while(undoStack != Nil) {
      undoStep
    }
  }
}
