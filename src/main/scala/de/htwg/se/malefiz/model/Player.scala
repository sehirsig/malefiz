package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int) {
   override def toString: String = Playerid + " " + name
}