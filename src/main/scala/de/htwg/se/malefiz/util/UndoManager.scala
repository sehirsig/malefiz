package de.htwg.se.malefiz.util

/** Klasse für unseren UndoManager.
 *
 *  @author sehirsig & franzgajewski
 */
class UndoManager {

  /** Liste aller Steps die undoable sind */
  private var undoStack: List[Command]= Nil

  /** Liste aller Steps die redoable sind */
  private var redoStack: List[Command]= Nil

  /** Einen schritt gehen
   *
   *  @param command Befehl
   */
  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep
  }

  /** Einen schritt zurückgehen */
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

  /** Einen schritt wiederherstellen */
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

  /** Die Stacks komplett leeren*/
  def emptyStacks: Unit = {
    undoStack = Nil
    redoStack = Nil
  }

  /** Alle Undos auf einmal ausführen */
  def undoAll: Unit = {
    while(undoStack != Nil) {
      undoStep
    }
  }

}
