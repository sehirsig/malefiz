package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.model.{Cell, Dice, Gameboard, Gamefigure, Player}

object Malefiz {
  val set = Settings();
  var spielbrett = new Gameboard[Cell](set.xDim, set.yDim)
  var dice = Dice()

  def main(args: Array[String]): Unit = {
    var input: String = ""
    println(
      """Welcome to Malefiz!
        |Please Enter your Name:
        |""".stripMargin)

    var namee = readLine()

    val student1 = Player(namee, 1)
    println("Hello, " + student1.name)
    println(
      """
        |Type anything to Start! Type Q to quit
        |""".stripMargin)
    while (readLine() != "Q") {
      println("You have rolled an: " + dice.roll)
      spielbrett.update()
    }
  }
}