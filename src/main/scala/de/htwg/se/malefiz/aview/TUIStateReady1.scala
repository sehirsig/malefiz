package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Malefiz.controller

case class TUIStateReady1() extends TUIState {

  def processing(input: String): Unit = {
    case "p" => controller.addPlayer()
    case "start" => controller.startGame()
    case "Q" => println("Good Bye!")
    case _ => println("invalid input")
  }
}
