package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.model.{Cell, Dice, Gameboard, Gamefigure, Player}

object Malefiz {
  val set = Settings();
  var spielbrett = new Gameboard[Cell](set.xDim, set.yDim)
  val game = new TUI[Cell](spielbrett)


  def main(args: Array[String]): Unit = { //Viel Weniger Text, alles in Methoden verpacken
    game.init
//    var input: String = ""
//    println(
//      """Welcome to Malefiz!
//        |Please Enter your Name:
//        |""".stripMargin)
//
//    var namee = readLine()
//
//    val student1 = Player(namee, 1)
//    println("Hello, " + student1.name)
//    println(
//      """
//        |Type anything to Start! Type Q to quit
//        |""".stripMargin)
//    val game = new TUI[Cell](spielbrett)
//    game.init
//    while (readLine() != "Q") {
//      println("You have rolled an: " + dice.roll)
//      println(spielbrett.toString)
//    }
  }
}