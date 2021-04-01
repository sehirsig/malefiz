package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.Player
import scala.io.StdIn.readLine

object Malefiz {
  def main(args: Array[String]): Unit = {
    print("Enter your name: ")
    val name = readLine();
    val student = Player(name)
    println("Hello, " + student.name)
  }
}
