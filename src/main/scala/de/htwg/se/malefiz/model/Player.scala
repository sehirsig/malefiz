package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
   val cell = Cell(Playerid.toString + " ")

   override def toString: String = Playerid + " " + name
}