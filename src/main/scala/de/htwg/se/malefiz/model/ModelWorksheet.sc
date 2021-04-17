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

// Die untere Funktion mit Rekursion machen!
// Pro rekursion wird eine list zurückgegeben, die in die liste vom aufrufer gespeichert wird

def availablePathsRecursive(row: Int, col: Int, walksLeft: Int, spielbrett:Gameboard[Cell]):List[(Int,Int)] = {
  if (walksLeft != 0) {

    val listUp = availablePathsRecursive(row, col, walksLeft - 1, spielbrett)
    val listDown = availablePathsRecursive(row, col, walksLeft - 1, spielbrett)
    val listLeft = availablePathsRecursive(row, col, walksLeft - 1, spielbrett)
    val listRight = availablePathsRecursive(row, col, walksLeft - 1, spielbrett)

  }

}

def availablePaths2(row: Int, col: Int, walksLeft: Int, spielbrett:Gameboard[Cell]): ArrayBuffer[(Int,Int, Boolean, Int, String)] = { // (Int,Int) für b als Ausgabe
  val internList = ArrayBuffer[(Int,Int, Boolean, Int, String)]() // (row, col, Touched Barricade, Welcher Schritt, Richtung)
  val outputList = ArrayBuffer[(Int,Int)]()
  internList += ((row,col, false, -1, "Initiate"))
  var d = walksLeft
  println(spielbrett.cell(14,3))
  println(spielbrett.cell(13,3) == Cell())

  while(d != 0) { // Rekursiv machen? Was machen bei Barikade, wenn nicht direkt drauf?
    internList.foreach(x => {
      val goDown = (x._1 + 1, x._2) // Runter
      val goUp = (x._1 - 1, x._2) // Hoch
      val goRight = (x._1, x._2 + 1) // Rechts
      val goLeft = (x._1, x._2 - 1) // Links
      if(!(spielbrett.cell(goRight._1, goRight._2) == Cell() || spielbrett.cell(goRight._1, goRight._2) == Cell("T "))) {
        if (spielbrett.cell(goRight._1, goRight._2) == Cell("X ")) {
          internList += ((goRight._1, goRight._2, true, walksLeft - d + 1, "Right"))
        } else {
          if (x._3 == true) {
            internList += ((goRight._1, goRight._2, true, walksLeft - d + 1, "Right"))
          } else {
            internList += ((goRight._1, goRight._2, false, walksLeft - d + 1, "Right"))
          }
        }
        if (d == 1) {
          outputList += ((goRight._1, goRight._2))
        }
      }
      if(!(spielbrett.cell(goUp._1, goUp._2) == Cell() || spielbrett.cell(goUp._1, goUp._2) == Cell("T "))) {
        if (spielbrett.cell(goUp._1, goUp._2) == Cell("X ")) {
          internList += ((goUp._1, goUp._2, true, walksLeft - d + 1, "Up"))
        } else {
          if (x._3 == true) {
            internList += ((goUp._1, goUp._2, true, walksLeft - d + 1, "Up"))
          } else {
            internList += ((goUp._1, goUp._2, false, walksLeft - d + 1, "Up"))
          }
        }
        if (d == 1) {
          outputList += ((goUp._1, goUp._2))
        }
      }
      if(!(spielbrett.cell(goLeft._1, goLeft._2) == Cell() || spielbrett.cell(goLeft._1, goLeft._2) == Cell("T "))) {
        if (spielbrett.cell(goLeft._1, goLeft._2) == Cell("X ")) {
          internList += ((goLeft._1, goLeft._2, true, walksLeft - d + 1, "Left"))
        } else {
          if (x._3 == true) {
            internList += ((goLeft._1, goLeft._2, true, walksLeft - d + 1, "Left"))
          } else {
            internList += ((goLeft._1, goLeft._2, false, walksLeft - d + 1, "Left"))
          }
        }
        if (d == 1) {
          outputList += ((goLeft._1, goLeft._2))
        }
      }
      if(!(spielbrett.cell(goDown._1, goDown._2) == Cell() || spielbrett.cell(goDown._1, goDown._2) == Cell("T "))) {
        if (spielbrett.cell(goDown._1, goDown._2) == Cell("X ")) {
          internList += ((goDown._1, goDown._2, true, walksLeft - d + 1, "Down"))
        } else {
          if (x._3 == true) {
            internList += ((goDown._1, goDown._2, true, walksLeft - d + 1, "Down"))
          } else {
            internList += ((goLeft._1, goLeft._2, false, walksLeft - d + 1, "Left"))
          }
        }
        if (d == 1) {
          outputList += ((goDown._1, goDown._2))
        }
      }
    })
    internList -= ((row,col, false, -1, "Initiate")) // Löscht den ersten, damit nicht nach mehreren Würfeln nochmal genommen wird.
    d = d - 1
  }
  internList.distinct //b ist endprodukt, a zum debuggen
}
//DICE 6: If !blockade not touched, delete list with DICE 5 (ALL) Ergebnisse. IF Blockade touched

val e = availablePaths2(15, 3, 6,a)

e.foreach(x => println(x))
