package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import scala.util.Random

/** Die for the base implementation.
 * Returns a number from 1 to 6. Returns -1 in case of an error.
 *  Implemented as an option monad.
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