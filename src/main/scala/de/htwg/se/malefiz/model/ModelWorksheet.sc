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

def availablePaths2(row: Int, col: Int, walkLeft: Int): ArrayBuffer[(Int,Int)] = {
  val a = ArrayBuffer[(Int,Int)]()

  var d = walkLeft
  while(d != 0) {
    a.foreach(x => {
      if(Settings().walkableCells.contains((x._1 + 1, x._2)) && !a.contains((x._1 + 1, x._2))) {
        a += ((x._1 + 1, x._2))
      }
      if(Settings().walkableCells.contains((x._1 ,x._2 + 1)) && !a.contains((x._1, x._2 + 1))) {
        a += ((x._1, x._2 + 1))
      }
      if(Settings().walkableCells.contains((x._1 - 1, x._2)) && !a.contains((x._1 - 1, x._2))) {
        a += ((x._1 - 1, x._2))
      }
      if(Settings().walkableCells.contains((x._1, x._2 - 1)) && !a.contains((x._1, x._2 - 1))) {
        a += ((x._1, x._2 - 1))
      }
    })
    d = d - 1
  }
  a
}


val e = availablePaths2(14, 3, 2)

e.foreach(x => println(x))

println(Settings().blockedCells.contains((2, 9)))