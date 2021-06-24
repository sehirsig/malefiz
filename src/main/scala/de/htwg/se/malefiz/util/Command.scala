/*
Class: Command.scala

Beschreibung:
Interface unseres Command-Patterns.

 */

package de.htwg.se.malefiz.util

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}
