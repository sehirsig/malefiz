
class Cell {
  var cellStatus = "  "

  def setFree(): Unit = cellStatus = "O "
  def setBlocked(): Unit = cellStatus = "X "
  def setSecure(): Unit = cellStatus = "O "
  def setGoal(): Unit = cellStatus = "G "

  override def toString(): String = "" + cellStatus
}


val xDim = 19
val yDim = 16

val gameBoard = Array.ofDim[Cell](yDim,xDim)

for(i<-0 to 15; j<- 0 to 18) {
  val cell = new Cell
  gameBoard(i)(j) = cell
}


val freeCells = Array((2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(3,1),(3,17),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,10),(4,11),(4,12),(4,13),(4,14),(4,15),(4,16),(4,17),(6,7),(6,8),(6,10),(6,11),(7,7),(7,11),(8,5),(8,6),(8,8),(8,9),(8,10),(8,12),(8,13),(9,5),(9,13),(10,3),(10,4),(10,5),(10,6),(10,7),(10,8),(10,9),(10,10),(10,11),(10,12),(10,13),(10,14),(10,15),(11,3),(11,7),(11,11),(11,15),(12,2),(12,3),(12,4),(12,6),(12,7),(12,8),(12,10),(12,11),(12,12),(12,14),(12,15),(12,16))
val blockedCells = Array((2,9),(4,9),(5,9),(6,9),(8,7),(8,11),(12,1),(12,5),(12,9),(12,13),(12,17))
val secureCells = Array((13,1),(13,5),(13,9),(13,13),(13,17),(14,1),(14,2),(14,3),(14,4),(14,5),(14,6),(14,7),(14,8),(14,9),(14,10),(14,11),(14,12),(14,13),(14,14),(14,15),(14,16),(14,17))
val goalCell = (1,9)

freeCells.foreach { tuple => gameBoard(tuple._1)(tuple._2).setFree()}
blockedCells.foreach { tuple => gameBoard(tuple._1)(tuple._2).setBlocked()}
secureCells.foreach { tuple => gameBoard(tuple._1)(tuple._2).setSecure()}
gameBoard(goalCell._1)(goalCell._2).setGoal()


gameBoard.foreach { row => row.foreach(print); println() }

//val row14 = Array(".",".",".",".",".",".",".",".","G",".",".",".",".",".",".",".",".")
//val row13 = Array("O","O","O","O","O","O","O","O","B","O","O","O","O","O","O","O","O")
//val row12 = Array("O",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".","O")
//val row11 = Array("O","O","O","O","O","O","O","O","B","O","O","O","O","O","O","O","O")
//val row10 = Array(".",".",".",".",".",".",".",".","B",".",".",".",".",".",".",".",".")
//val row09 = Array(".",".",".",".",".",".","O","O","B","O","O",".",".",".",".",".",".")
//val row08 = Array(".",".",".",".",".",".","O",".",".",".","O",".",".",".",".",".",".")
//val row07 = Array(".",".",".",".","O","O","B","O","O","O","B","O","O",".",".",".",".")
//val row06 = Array(".",".",".",".","O",".",".",".",".",".",".",".","O",".",".",".",".")
//val row05 = Array(".",".","O","O","O","O","O","O","O","O","O","O","O","O","O",".",".")
//val row04 = Array(".",".","O",".",".",".","O",".",".",".","O",".",".",".","O",".",".")
//val row03 = Array("B","O","O","O","B","O","O","O","B","O","O","O","B","O","O","O","B")
//val row02 = Array("S",".",".",".","S",".",".",".","S",".",".",".","S",".",".",".","S")
//val row01 = Array("S","S","S","S","S","S","S","S","S","S","S","S","S","S","S","S","S")
///*
//print(row14.mkString(" "))
//print(row13.mkString(" "))
//print(row12.mkString(" "))
//print(row11.mkString(" "))
//print(row10.mkString(" "))
//print(row09.mkString(" "))
//print(row08.mkString(" "))
//print(row07.mkString(" "))
//print(row06.mkString(" "))
//print(row05.mkString(" "))
//print(row04.mkString(" "))
//print(row03.mkString(" "))
//print(row02.mkString(" "))
//print(row01.mkString(" "))
//*/
//
////test
//
//val spielbrett =  List(row14, row13, row12, row11, row10, row09, row08, row07, row06, row05, row04, row03, row02, row01)
//
//def update(): Unit = {
//  spielbrett.foreach(x => println(x.mkString(" ")))
//}
//update()