/*
Class: UndoManager.scala

Beschreibung:
Klasse für unseren UndoManager.

 */

package de.htwg.se.malefiz.util

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil
  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep
  }
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
  def emptyStacks: Unit = {
    undoStack = Nil
    redoStack = Nil
  }

  def undoAll: Unit = {
    while(undoStack != Nil) {
      undoStep
    }
  }

}
