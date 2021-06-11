package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl


import scala.util.Random

object Dice {
  def diceRoll: Int = {
    val rnd: Option[Random] = Some(new Random())
    rnd match {
      case Some(x) => x.nextInt(6) + 1
      case None => -1
    }
  }
}