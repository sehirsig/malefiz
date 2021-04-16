package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.{Gameboard, Player, Gamefigure}

object Malefiz {
  def main(args: Array[String]): Unit = {
    val student1 = Player("Your Name", 1)
    val student2 = Player("Your Name", 2)
    val student3 = Player("Your Name", 3)
    val student4 = Player("Your Name", 4)

    println("Hello, " + student1.name)

    val figures01:List[Gamefigure] = List.tabulate(5)(n => Gamefigure(n + 1, student1))
    val figures02:List[Gamefigure] = List.tabulate(5)(n => Gamefigure(n + 1, student2))
    val figures03:List[Gamefigure] = List.tabulate(5)(n => Gamefigure(n + 1, student3))
    val figures04:List[Gamefigure] = List.tabulate(5)(n => Gamefigure(n + 1, student4))


    println("Print: " + figures01)
    println("Print: " + figures02)
    println("Print: " + figures03)
    println("Print: " + figures04)


    //val spielbrett = Gameboard()

    //spielbrett.initiating()

    //spielbrett.update()
  }
}
