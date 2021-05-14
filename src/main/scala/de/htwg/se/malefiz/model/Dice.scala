package de.htwg.se.malefiz.model

import scala.util.Random

object Dice { //Singleton
  def diceRoll: Int = {
    val rnd = new Random()
    rnd.nextInt(6) + 1
  }

}