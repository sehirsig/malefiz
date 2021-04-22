package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.util.Observer

case class TUI(controller: Controller) extends Observer {
  controller.add(this)
  override def update: Unit =  println(controller.boardToString)


  def processing(input: String): Unit = {
    var dice = Dice()
    val figurDraussen = true

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
    while (scala.io.StdIn.readLine() != "Q") { // TODO ich glaub die while muss noch raus
      val roll = dice.roll
      println("You have rolled an: " + roll)
//      println(spielbrett.toString)
      if(figurDraussen) {
        for (i <- 1 to roll) {
          val move = scala.io.StdIn.readLine()
          move match {
            case "w" => controller.moveUp()
            case "a" => controller.moveLeft()
            case "s" => controller.moveDown()
            case "d" => controller.moveRight()
            case _ => println("invalid input")
          }
        }
      }
      println("press any key to roll again (Q to exit)")
    }
  }
}
