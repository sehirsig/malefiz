/*
Class: gameboardBaseImpl/moveTypes.scala

Beschreibung:
Klasse um die Koordinaten schnell auf den der nächsten Zelle zu ändern.

 */

package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

object moveTypes {
  def goDown(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1 + 1, oldcord._2)
  }

  def goUp(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1 - 1, oldcord._2)
  }

  def goRight(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1, oldcord._2 + 1)
  }

  def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1, oldcord._2 - 1)
  }
}
