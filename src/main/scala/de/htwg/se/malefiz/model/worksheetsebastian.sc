import de.htwg.se.malefiz.model._

case class Board(cells: Matrix[Cell]) {
  def this(sizex: Int, sizey: Int) = this(new Matrix[Cell](sizex, sizey, Cell("  ")))
}


//println(spielbrett.gameboard(2)(1))