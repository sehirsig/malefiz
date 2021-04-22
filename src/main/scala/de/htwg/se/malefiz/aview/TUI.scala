package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._

case class TUI[T](gameboard: Gameboard[T]) {
  def init(): Unit = {
    var dice = Dice()
    val figurDraussen = true
    val controller = new Controller()

    println(
      """Welcome to Malefiz!
        |Please Enter your Name:
        |""".stripMargin)
    var namee = scala.io.StdIn.readLine()

    val student1 = Player(namee, 1)
    println("Hello, " + student1.name)
    println(
      """
        |Type anything to Start! Type Q to quit
        |""".stripMargin)
    while (scala.io.StdIn.readLine() != "Q") {
      val roll = dice.roll
      println("You have rolled an: " + roll)
      println(gameboard.toString)
      if(figurDraussen) {
        for (i <- 1 to roll) {
          val move = scala.io.StdIn.readLine()
          move match {
            case w => controller.moveUp()
            case a => controller.moveUp()
            case s => controller.moveUp()
            case d => controller.moveUp()
            case _ => controller.moveUp()
          }
        }
      }
    }
  }
}
