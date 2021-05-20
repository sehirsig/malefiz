package de.htwg.se.malefiz.model

object moveTypes {
  def goDown(oldcord:(Int,Int)): (Int,Int) = {
    (oldcord._1 + 1, oldcord._2)
  }
  def goUp(oldcord:(Int,Int)): (Int,Int) = {
    (oldcord._1 - 1, oldcord._2)
  }
  def goRight(oldcord:(Int,Int)): (Int,Int) = {
    (oldcord._1, oldcord._2 + 1)
  }
  def goLeft(oldcord:(Int,Int)): (Int,Int) = {
    (oldcord._1, oldcord._2 - 1)
  }
}
