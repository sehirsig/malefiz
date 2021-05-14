package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings

case class Gameboard(rows: Vector[Vector[Cell]]) {
  def this(sizex: Int, sizey: Int) = this(Vector.tabulate(sizex, sizey) {
    (row, col) => {
      if(Settings().blockedCells.contains(row, col)) {
        BlockedCell
      } else if (Settings().freeCells.contains(row, col)) {
        FreeCell
      } else if(Settings().secureCells.contains(row, col)) {
        SecureCell
      } else if(Settings().goalCell == (row, col)) {
        GoalCell
      } else if(Settings().start1.contains(row, col)) {
        StartCell
      } else if(Settings().start2.contains(row, col)) {
        StartCell
      } else if(Settings().start3.contains(row, col)) {
        StartCell
      } else if(Settings().start4.contains(row, col)) {
        StartCell
      } else {
        InvalidCell
      }
    }
  })

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def replaceCell(row: Int, col: Int, cell: Cell): Gameboard = copy(rows.updated(row, rows(row).updated(col, cell)))

  override def toString: String = {
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}
