package de.htwg.se.malefiz.model

import scala.{:+, ::}

case class PathFinder(dice:Int,spieler_id:Int, spielbrett:Gameboard[Cell]) {
  def availablePaths(row: Int, col: Int, walkLeft: Int): List[(Int,Int)] = {
    val a = List((row, col))
    while(walkLeft != 0) {
        a.foreach(x => {
          (x._1 + 1,x._2) :: (x._1 ,x._2 + 1) :: (x._1 - 1,x._2) :: (x._1,x._2 - 1) :: a
        })
      }
    a
    }
}


