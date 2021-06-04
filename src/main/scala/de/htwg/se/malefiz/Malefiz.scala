package de.htwg.se.malefiz

import de.htwg.se.malefiz.aview.GUI.SwingGui
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.{Controller, SettingUp, StartUp}
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import scala.io.StdIn.readLine

object Malefiz {
  val controller = new Controller(new Gameboard(Settings().xDim, Settings().yDim))
  val tui = new TUI(controller)
  val gui = new SwingGui(controller)
  //controller.notifyObservers
  controller.publish(new StartUp)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    while (input != "Q") {
      input = readLine()
      tui.processing(input)
    }
  }
}