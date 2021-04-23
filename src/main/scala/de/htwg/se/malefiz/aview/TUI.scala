package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.Observer

case class TUI(controller: Controller) extends Observer {
  controller.add(this)
  override def update: Unit =  println(controller.boardToString)

  def processing(input: String): Unit = {
    var dice = Dice()
    val figurDraussen = true

//    while (scala.io.StdIn.readLine() != "Q") {
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
//      }
//      println("press any key to roll again (Q to exit)")
    }
  }
}
