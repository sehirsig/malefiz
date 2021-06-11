package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import com.google.inject.Inject
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.playerComponent.Player
import de.htwg.se.malefiz.util.BlockStrategy

import scala.util.{Failure, Random, Success, Try}

case class Gameboard@Inject()(rows: Vector[Vector[Cell]]) extends GameboardInterface {

  def this(sizex: Int, sizey: Int) = this(Vector.tabulate(sizex, sizey) {
    (row, col) => {
      if (Settings().blockedCells.contains(row, col)) {
        BlockedCell
      } else if (Settings().freeCells.contains(row, col)) {
        FreeCell
      } else if (Settings().secureCells.contains(row, col)) {
        SecureCell
      } else if (Settings().goalCell == (row, col)) {
        GoalCell
      } else if (Settings().start1.contains(row, col)) {
        Start1Cell
      } else if (Settings().start2.contains(row, col)) {
        Start2Cell
      } else if (Settings().start3.contains(row, col)) {
        Start3Cell
      } else if (Settings().start4.contains(row, col)) {
        Start4Cell
      } else {
        InvalidCell
      }
    }
  })


  var blockStrategy: BlockStrategy = BlockReplaceStrategy()

  def setBlockStrategy(blockstrategy: String): Unit = {
    blockstrategy match {
      case "remove" => this.blockStrategy = BlockRemoveStrategy()
      case "replace" => this.blockStrategy = BlockReplaceStrategy()
    }
  }

  def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = {
    blockStrategy.replaceBlock(spielbrett)
  }


  def newGBStandardSize: Gameboard = {
    new Gameboard(Settings().xDim, Settings().yDim)
  }

  def getStandardXYsize: (Int,Int) = {
    (Settings().xDim, Settings().yDim)
  }

  def getStringOfCell(cell:Cell): String = {
    cell match {
      case FreeCell => "FreeCell"
      case BlockedCell => "BlockedCell"
      case Start1Cell => "Start1Cell"
      case Start2Cell =>"Start2Cell"
      case Start3Cell =>"Start3Cell"
      case Start4Cell =>"Start4Cell"
      case SecureCell =>"SecureCell"
      case GoalCell =>"GoalCell"
      case InvalidCell =>"InvalidCell"
      case PlayerCell(1) =>"PlayerCell1"
      case PlayerCell(2) =>"PlayerCell2"
      case PlayerCell(3) =>"PlayerCell3"
      case PlayerCell(4) =>"PlayerCell4"
      case _ => "InvalidCell"
    }
  }

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def cellString(row: Int, col: Int): String = getStringOfCell(rows(row)(col))

  def replaceCell(row: Int, col: Int, cell: Cell): Try[Gameboard] = {
    val tmp = Try(copy(rows.updated(row, rows(row).updated(col, cell))))
    tmp match {
      case Success(v) => Success(v)
      case Failure(e) => Failure(e)
    }
  }

  def movePlayer(coord: (Int, Int), cell: Cell): Gameboard = {
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  def moveCell(coord: (Int, Int), cell: Cell): Gameboard = {
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkUp(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkDown(spielbrett, player, currentCoord, figurenum, walksLeft)
  }
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkLeft(spielbrett, player, currentCoord, figurenum, walksLeft)
  }
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkRight(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  def diceRoll: Int = {
    Dice.diceRoll
  }

  def goDown(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goDown(oldcord)
  }
  def goUp(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goUp(oldcord)
  }
  def goRight(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goRight(oldcord)
  }
  def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goLeft(oldcord)
  }


  def getCell(name: String): Cell = {
    name match {
      case "FreeCell" => FreeCell
      case "BlockedCell" => BlockedCell
      case "Start1Cell" => Start1Cell
      case "Start2Cell" => Start2Cell
      case "Start3Cell" => Start3Cell
      case "Start4Cell" => Start4Cell
      case "SecureCell" => SecureCell
      case "GoalCell" => GoalCell
      case "InvalidCell" => InvalidCell
      case "PlayerCell1" => PlayerCell(1)
      case "PlayerCell2" => PlayerCell(2)
      case "PlayerCell3" => PlayerCell(3)
      case "PlayerCell4" => PlayerCell(4)
    }
  }

  def checkPlayerOnGoal: Boolean = {
    cell(1, 9).isInstanceOf[PlayerCell]
  }

  override def toString: String = {
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}
