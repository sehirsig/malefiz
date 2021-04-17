import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.model.{Cell, Gameboard}

import scala.collection.mutable.ArrayBuffer

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
val s = Settings();

val a = new Gameboard[Cell](s.xDim, s.yDim)
a.update()

def availablePaths2(row: Int, col: Int, walksLeft: Int, spielbrett:Gameboard[Cell]): ArrayBuffer[(Int,Int)] = {
  val a = ArrayBuffer[(Int,Int)]()
  val b = ArrayBuffer[(Int,Int)]()
  a += ((row,col))
  var d = walksLeft
  while(d != 0) {
    a.foreach(x => {
      println("He")
      if(!(spielbrett.cell(x._1 + 1, x._2) == Cell())) {
        a += ((x._1 + 1, x._2))
        if (d == 1) {
          b += ((x._1 + 1, x._2))
        }
      }
      if(!(spielbrett.cell(x._1, x._2 + 1) == Cell())) {
        a += ((x._1, x._2 + 1))
        if (d == 1) {
          b += ((x._1, x._2 + 1))
        }
      }
      if(!(spielbrett.cell(x._1 - 1, x._2) == Cell())) {
        a += ((x._1 - 1, x._2))
        if (d == 1) {
          b += ((x._1 - 1, x._2))
        }
      }
      if(!(spielbrett.cell(x._1, x._2 - 1) == Cell())) {
        a += ((x._1, x._2 - 1))
        if (d == 1) {
          b += ((x._1, x._2 - 1))
        }
      }
    })
    d = d - 1
  }
  b.distinct
}


val e = availablePaths2(14, 3, 2,a)

e.foreach(x => println(x))
