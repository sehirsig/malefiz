package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

case class TUIStatePlaying() extends TUIState {

  def processing(input: String): Unit = {
    case "r" => controller.rollDice()
    case "Q" => println("Good Bye!")
    case _ => println("invalid input")
  }
}
