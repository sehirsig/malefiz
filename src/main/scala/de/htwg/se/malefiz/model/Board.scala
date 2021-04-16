package de.htwg.se.malefiz.model

case class Board(cells: Matrix[Cell]) {
  def this(sizex: Int, sizey: Int) = this(new Matrix[Cell](sizex, sizey, Cell("  ")))
}
