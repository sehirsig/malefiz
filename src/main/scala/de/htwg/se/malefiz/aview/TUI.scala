package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.util.Observer

case class TUI(controller: Controller) extends Observer {
  controller.add(this)
  override def update: Unit =  println(controller.boardToString)

  def processing(input: String): Unit = {
    val figurDraussen = true

    val roll = controller.rollDice()
    println("You have rolled a: " + roll)
    if(figurDraussen) {
      for (i <- 1 to roll) {
        val move = scala.io.StdIn.readLine()
        move match {
//          case "r" => controller.rollDice() TODO dice rolling by controller
          case "w" => controller.moveUp()
          case "a" => controller.moveLeft()
          case "s" => controller.moveDown()
          case "d" => controller.moveRight()
          case _ => println("invalid input")
        }
      }
    }
  }
}
