package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import scala.io.StdIn.readLine

object Malefiz {
  val controller = new Controller(new Gameboard(Settings().xDim, Settings().yDim))
  val tui = new TUI(controller)

  def main(args: Array[String]): Unit = {
    var input: String = "welcomeMessage"

//    tui.initiateGame()

    while (input != "Q") {
      tui.processing(input)
      println("waiting for input...")
      input = readLine()
    }
  }
}