package de.htwg.se.malefiz.util

/** Interface unseres Command-Patterns.
 *
 *  @author sehirsig & franzgajewski
 */
trait Command {

  /** Einen Schritt gehen */
  def doStep:Unit

  /** Einen Schritt zurückgehen */
  def undoStep:Unit

  /** Einen Schritt wiederherstellen */
  def redoStep:Unit
}
