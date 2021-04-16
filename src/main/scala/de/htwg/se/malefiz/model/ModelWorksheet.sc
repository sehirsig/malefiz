import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.model.{Cell, Gameboard}

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

def availablePaths(row: Int, col: Int, walkLeft: Int): Array[(Int,Int)] = {
  val a = Array((row, col))
  var d = walkLeft
  while(d != 0) {
    a.foreach(x => {
      (x._1 + 1, x._2) +: a
      (x._1 ,x._2 + 1) +: a
      (x._1 - 1, x._2) +: a
      (x._1, x._2 - 1) +: a
    })
    d = d - 1
  }
  a
}


var e = availablePaths(3, 2, 2)

e.foreach(x => println(x))