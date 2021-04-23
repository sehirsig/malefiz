package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import scala.io.StdIn.readLine

object Malefiz {
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
  input = readLine()

  val controller = new Controller()
  val tui = new TUI(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = { //Viel Weniger Text, alles in Methoden verpacken
    var input: String = ""

    while (input != "q") {
      println("waiting...")
      input = readLine()
      tui.processing(input)
    }

//    val game = new TUI[Cell](spielbrett)
//    game.init
//    while (readLine() != "Q") {
//      println("You have rolled an: " + dice.roll)
//      println(spielbrett.toString)
//    }
  }
}