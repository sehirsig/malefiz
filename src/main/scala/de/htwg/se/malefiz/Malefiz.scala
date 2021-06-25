package de.htwg.se.malefiz

import com.google.inject.Guice
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

/** Hauptfile des Malefiz Spiels.
 *
 *  @author sehirsig & franzgajewski
 */
object Malefiz {
  val injector = Guice.createInjector(new MalefizModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)
  controller.setupGame()

  def main(args: Array[String]): Unit = {
    var input: String = ""

    while (input != "Q") {
      input = readLine()
      tui.processing(input)
    }
  }
}