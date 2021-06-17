package de.htwg.se.malefiz

import com.google.inject.Guice
import de.htwg.se.malefiz.aview.GUI.SwingGui
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Malefiz {
  val injector = Guice.createInjector(new MalefizModule)
  val controller = injector.getInstance(classOf[ControllerInterface])//new Controller(new Gameboard(Settings().xDim, Settings().yDim))
  val tui = new TUI(controller)
  val gui = new SwingGui(controller)
  controller.setupGame()

  def main(args: Array[String]): Unit = {
    var input: String = ""

    while (input != "Q") {
      input = readLine()
      tui.processing(input)
    }
  }
}