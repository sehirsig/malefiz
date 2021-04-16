package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.{Gameboard, Player, Gamefigure}

object Malefiz {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name", 0)
    println("Hello, " + student.name)

    val figures01:List[Gamefigure] = List.tabulate(5)(n => Gamefigure(n, student))

    println("Print: " + figures01)

    //val spielbrett = Gameboard()

    //spielbrett.initiating()

    //spielbrett.update()
  }
}
