package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int) {

   /*def getColour: String = Playerid match {
      case 0 => Console.GREEN;
      case 1 => Console.RED;
      case 2 => Console.BLUE;
      case 3 => Console.YELLOW;
   }
*/
   override def toString: String = Playerid + " " + name
}