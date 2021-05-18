package de.htwg.se.malefiz.model

case class Player(name: String, Playerid: Int, var gf:Gamefigure) {
   override def toString: String = Playerid + " " + name
}