<<<<<<< HEAD:src/main/scala/de/htwg/se/malefiz/model/Malefiz.scala
package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.Player

object Malefiz {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
=======
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
>>>>>>> 747a01e9de917030fd1831df73cc28ab62cd112b:src/main/scala/de/htwg/se/malefiz/Malefiz.scala
