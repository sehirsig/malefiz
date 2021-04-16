package de.htwg.se.malefiz.model

case class Matrix[T](rows: Vector[Vector[T]]) {
  def this(sizex: Int, sizey: Int, filling: T) = this(Vector.tabulate(sizex, sizey) { (row, col) => filling })

  //val size: Int = rows.size

  def cell(row: Int, col: Int): T = rows(row)(col)

  //def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}