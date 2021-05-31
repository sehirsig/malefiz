package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int, startingPos: (Int, Int)) {
   //val cell = PlayerCell(Playerid)
   var figures = Array[Gamefigure]()
   var hasWon = false;
   def wonTheGame:Unit = {hasWon = true}
   def addFigure(id:Int): Unit = {
      val figure = Gamefigure(1, id, Player.this, startingPos)
      figures = figures :+ figure
   }
   override def toString: String = Playerid + " " + name
}