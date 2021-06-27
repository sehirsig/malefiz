package de.htwg.se.malefiz.util

/** Command pattern interface.
 *
 *  @author sehirsig & franzgajewski
 */
trait Command {

  /** Do one step. */
  def doStep:Unit

  /** Undo one step. */
  def undoStep:Unit

  /** Redo one step. */
  def redoStep:Unit
}