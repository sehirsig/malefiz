package de.htwg.se.malefiz.model

object moveTypes {
  def goDown(oldx:Int, oldy:Int): (Int,Int) = {
    (oldx + 1, oldy)
  }
  def goUp(oldx:Int, oldy:Int): (Int,Int) = {
    (oldx - 1, oldy)
  }
  def goRight(oldx:Int, oldy:Int): (Int,Int) = {
    (oldx, oldy + 1)
  }
  def goLeft(oldx:Int, oldy:Int): (Int,Int) = {
    (oldx, oldy - 1)
  }
}