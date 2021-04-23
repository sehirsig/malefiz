package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.Player

import scala.io.StdIn.readLine

object Malefiz {
  println(
    """Welcome to Malefiz!
      |Please Enter your Name:
      |""".stripMargin)

  var namee = readLine()

  val student1 = Player(namee, 1)

  val controller = new Controller()
  val tui = new TUI(controller)
  var input: String = ""

  def main(args: Array[String]): Unit = { //Viel Weniger Text, alles in Methoden verpacken
    println("Hello, " + student1.name)
    println(
      """
        |Type anything to Start! Type 'Q' to quit
        |""".stripMargin)
    input = readLine()

    while (input != "Q") {
      controller.notifyObservers // TODO remove this here. call from controller at start of tui processing maybe
      tui.processing(input)
      println("waiting for input... ('Q' to quit)")
      input = readLine()
    }

//    val game = new TUI[Cell](spielbrett)
//    game.init
//    while (readLine() != "Q") {
//      println("You have rolled an: " + dice.roll)
//      println(spielbrett.toString)
//    }
  }
}