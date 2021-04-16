
import de.htwg.se.malefiz.model.properties.Settings
case class Cell(cellStatus: String = "  ", secureStatus: Boolean = false) {
  def isWalkable: Boolean = cellStatus != "  "
  override def toString(): String = { cellStatus }
}

val s = Settings();

s.blockedCells;

case class Matrix[T](rows: Vector[Vector[Cell]]) {
  val s = Settings()
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
      } else {
        Cell("  ")
      }
    }
  })

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  //def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: Cell): Matrix[Cell] = copy(rows.updated(row, rows(row).updated(col, cell)))
}

val a = new Matrix(s.xDim, s.yDim)
