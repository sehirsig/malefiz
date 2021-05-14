package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

case class TUIStateMoving() extends TUIState {

  def processing(input: String): Unit = {
    case "w" => controller.move(input)
    case "a" => controller.move(input)
    case "s" => controller.move(input)
    case "d" => controller.move(input)
    case "Q" => println("Good Bye!")
    case _ => println("invalid input")
  }
}
