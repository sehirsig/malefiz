package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.model.{Cell, Gamefigure, Gameboard, Player}

object Malefiz {
  def main(args: Array[String]): Unit = {
    val student1 = Player("Your Name", 1)
    println("Hello, " + student1.name)

    val s = Settings();
    val a = new Gameboard(s.xDim, s.yDim)
    a.update()
  }
}
