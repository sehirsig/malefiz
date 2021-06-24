/*
Class: gameboardBaseImpl/Dice.scala

Beschreibung:
Der Würfel für unsere Base-Implementation

 */

package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl


import scala.util.Random

object Dice { //Würfeln, eine Zahl von 1 - 6. Falls es einen Fehler in der Funktion gab, wird ein Fehler geworfen und -1 zurückgegeben.
  def diceRoll: Int = {
    val rnd: Option[Random] = Some(new Random())
    rnd match {
      case Some(x) => x.nextInt(6) + 1
      case None => -1
    }
  }
}