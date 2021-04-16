package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings

case class Gameboard[T](rows: Vector[Vector[Cell]]) {
  def this(sizex: Int, sizey: Int) = this(Vector.tabulate(sizex, sizey) {
    (row, col) => {
      if(Settings().blockedCells.contains(row, col)) {
        Cell("X ")
      } else if (Settings().freeCells.contains(row, col)) {
        Cell("O ")
      } else if(Settings().secureCells.contains(row, col)) {
        Cell("O ")
      } else if((Settings().goalCell == (row, col))) {
        Cell("G ")
      } else if((Settings().start1 == (row, col))) { //T Wird nicht ausgegeben?
        Cell("T ")
      } else if((Settings().start2 == (row, col))) {
        Cell("T ")
      } else if((Settings().start3 == (row, col))) {
        Cell("T ")
      } else if((Settings().start4 == (row, col))) {
        Cell("T ")
      } else {
        Cell()
      }
    }
  })

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def update():Unit = {
    this.rows.foreach { row => row.foreach(print); println() }
  }

  def replaceCell(row: Int, col: Int, cell: Cell): Gameboard[Cell] = copy(rows.updated(row, rows(row).updated(col, cell)))
}
