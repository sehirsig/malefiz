package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.BlockStrategy

import scala.util.{Failure, Success, Try}

case class Gameboard(rows: Vector[Vector[Cell]]) {
  var blockStrategy: BlockStrategy = BlockReplaceStrategy()

  def setBlockStrategy(blockstrategy: BlockStrategy): Unit = {
    blockStrategy = blockstrategy
  }

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
        Start1Cell
      } else if(Settings().start2.contains(row, col)) {
        Start2Cell
      } else if(Settings().start3.contains(row, col)) {
        Start3Cell
      } else if(Settings().start4.contains(row, col)) {
        Start4Cell
      }  else {
        InvalidCell
      }
    }
  })

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def replaceCell(row: Int, col: Int, cell: Cell): Try[Gameboard] = {
    val tmp = Try(copy(rows.updated(row, rows(row).updated(col, cell))))
    tmp match {
      case Success(v) => Success(v)
      case Failure(e) => Failure(e)
    }
  }

  def movePlayer(coord:(Int,Int), cell: Cell): Gameboard = {
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }


  override def toString: String = {
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}
