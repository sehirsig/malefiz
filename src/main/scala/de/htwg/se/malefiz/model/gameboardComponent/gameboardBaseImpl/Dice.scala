package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import scala.util.Random

/** Der Würfel für unsere Base-Implementation
 *  eine Zahl von 1 - 6. Falls es einen Fehler in der Funktion gab, wird ein Fehler geworfen und -1 zurückgegeben.
 *  Option-Monade.
 *
 *  @author sehirsig & franzgajewski
 */
object Dice {
  def diceRoll: Int = {
    val rnd: Option[Random] = Some(new Random())
    rnd match {
      case Some(x) => x.nextInt(6) + 1
      case None => -1
    }
  }
}