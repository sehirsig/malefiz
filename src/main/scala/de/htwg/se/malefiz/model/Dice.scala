package de.htwg.se.malefiz.model

import scala.util.Random

case class Dice(){
  def roll: Int = {
    val rnd = new Random()
    rnd.nextInt(6) + 1
  }
}
