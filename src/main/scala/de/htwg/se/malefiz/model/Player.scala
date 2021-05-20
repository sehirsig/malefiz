package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
   val cell = Cell(Playerid.toString + " ")
   var players = Array[Gamefigure]()
   def addFigure(): Unit = {
      val figure = Gamefigure(1, Player.this, startingPos)
      players :+ figure
   }
   override def toString: String = Playerid + " " + name
}