package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import scala.io.StdIn.readLine

object Malefiz {
  val controller = new Controller(new Gameboard(Settings().xDim, Settings().yDim))
  val tui = new TUI(controller)
  controller.notifyObservers

  println("test")

  def main(args: Array[String]): Unit = {
    var input: String = ""
//    tui.initiateGame()

    while (input != "Q") {
      input = readLine()
      tui.processing(input)
    }
  }
}