package de.htwg.se.malefiz.model

import scala.util.Random

object Dice { //Singleton
  def diceRoll: Int = {
    val rnd: Option[Random] = Some(new Random())
    rnd match {
      case Some(x) => x.nextInt(6) + 1
      case None => -1
    }
  }
}