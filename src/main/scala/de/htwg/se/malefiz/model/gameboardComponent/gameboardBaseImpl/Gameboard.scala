package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import com.google.inject.Inject
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.playerComponent.Player
import de.htwg.se.malefiz.util.BlockStrategy
import scala.util.{Failure, Success, Try}

/** Base implementation of the game board.
 *
 *  @author sehirsig & franzgajewski
 */
case class Gameboard(rows: Vector[Vector[Cell]]) extends GameboardInterface {

  /** Replace all cells according the the settings class. */
  @Inject
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

  /** Initialization of the block strategy. */
  var blockStrategy: BlockStrategy = BlockReplaceStrategy()

  /** Change of the block strategy.
   *
   *  @param blockstrategy "remove" or "replace"
   */
  override def setBlockStrategy(blockstrategy: String): Unit = {
    blockstrategy match {
      case "remove" => this.blockStrategy = BlockRemoveStrategy()
      case "replace" => this.blockStrategy = BlockReplaceStrategy()
    }
  }

  /** Function for handling barricades according to the block strategy.
   *
   *  @param spielbrett old game board
   *  @return new game board with changed blocking
   */
  override def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = {
    blockStrategy.replaceBlock(spielbrett)
  }

  /** New game board in the dimensions given in the settings class.
   *
   *  @return new game board
   */
  override def newGBStandardSize: Gameboard = {
    new Gameboard(Settings().xDim, Settings().yDim)
  }

  /** Retrieve default size from the settings class.
   *
   *  @return tupel of the default x and y dimensions of the game board
   */
  override def getStandardXYsize: (Int,Int) = {
    (Settings().xDim, Settings().yDim)
  }

  /** Get name of cell (e.g. for JSON/XMY storage).

   *  @param cell
   *
   *  @return string representation of cell
   */
  override def getStringOfCell(cell:Cell): String = {
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

  /** Returns a cell as coordinates of the game board.
   *
   *  @param row x-coordinate of the matrix
   *  @param col y-coordinate of the matric
   *  @return cell at the given coordinates
   */
  override def cell(row: Int, col: Int): Cell = rows(row)(col)


  /** Takes cell coordinates and returns corresponding string representation.
   *
   *  @param row x coordinate
   *  @param col y coordinate
   *
   *  @return string representation of cell
   */
  override def cellString(row: Int, col: Int): String = getStringOfCell(rows(row)(col)) //Bekomme die Stringdarstellung aus den Koordinaten.


  /** Change a cell on the game board.
   *
   *  @param row x-coordinate of the matrix
   *  @param col y-coordinate of the matric
   *  @param cell cell to be replaced
   *  @return new game board
   */
  override def replaceCell(row: Int, col: Int, cell: Cell): Try[Gameboard] = { //Eine Zelle ersetzen mit Fehlerbehandlung. Try-Monade. Für Falsche Indexierung.
    val tmp = Try(copy(rows.updated(row, rows(row).updated(col, cell))))
    tmp match {
      case Success(v) => Success(v)
      case Failure(e) => Failure(e)
    }
  }

  /** Move a player.
   *
   *  @param coord coordinate as an int-tupel of the matrix
   *  @param cell cell to be replaced
   *  @return new game board
   */
  override def movePlayer(coord: (Int, Int), cell: Cell): Gameboard = {
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  /** Move a cell.
   *
   * @param coord x and y coordinates
   * @param cell cell to be moved
   * @return new game board
   */
  def moveCell(coord: (Int, Int), cell: Cell): Gameboard = {
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  /** Check, if walking up is legal and do so if possible.
   *
   *  @param spielbrett old game board
   *  @param player current player
   *  @param currentCoord coordinates of current figure
   *  @param figurenum number of figure
   *  @param walksLeft number of remaining movements
   *
   *  @return tuple of boolean to indicate whether the move was successful and the new game board
   */
  override def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkUp(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Check, if walking down is legal and do so if possible.
   *
   *  @param spielbrett old game board
   *  @param player current player
   *  @param currentCoord coordinates of current figure
   *  @param figurenum number of figure
   *  @param walksLeft number of remaining movements
   *
   *  @return tuple of boolean to indicate whether the move was successful and the new game board
   */
  override def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkDown(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Check, if walking left is legal and do so if possible.
   *
   *  @param spielbrett old game board
   *  @param player current player
   *  @param currentCoord coordinates of current figure
   *  @param figurenum number of figure
   *  @param walksLeft number of remaining movements
   *
   *  @return tuple of boolean to indicate whether the move was successful and the new game board
   */
  override def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkLeft(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Check, if walking right is legal and do so if possible.
   *
   *  @param spielbrett old game board
   *  @param player current player
   *  @param currentCoord coordinates of current figure
   *  @param figurenum number of figure
   *  @param walksLeft number of remaining movements
   *
   *  @return tuple of boolean to indicate whether the move was successful and the new game board
   */
  override def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkRight(spielbrett, player, currentCoord, figurenum, walksLeft)
  }


  /** Die roll.
   *
   *  @return number from 1 to 6
   */
  override def diceRoll: Int = {
    Dice.diceRoll
  }


  /** Changes coordinates of a tupel as to go down one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  override def goDown(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goDown(oldcord)
  }

  /** Changes coordinates of a tupel as to go up one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  override def goUp(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goUp(oldcord)
  }

  /** Changes coordinates of a tupel as to go right one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  override def goRight(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goRight(oldcord)
  }

  /** Changes coordinates of a tupel as to go left one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  override def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goLeft(oldcord)
  }

  /** Takes a string and returns the corresponding cell.
   *
   *  @param name string representation of cell
   *
   *  @return cell
   */
  override def getCell(name: String): Cell = {
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

  /** Checks if a player is on the goal.
   *
   *  @return boolean
   */
  def checkPlayerOnGoal: Boolean = {
    cell(Settings().goalCell._1, Settings().goalCell._2).isInstanceOf[PlayerCell]
  }


  /** Get coordinates of the base of the 1. player.
   *
   *  @return tupel of x and y coordinates
   */
  override def getP1Base: (Int,Int) = {
    Settings().start1.head
  }

  /** Get coordinates of the base of the 2. player.
   *
   *  @return tupel of x and y coordinates
   */
  override def getP2Base: (Int,Int) = {
    Settings().start2.head
  }

  /** Get coordinates of the base of the 3. player.
   *
   *  @return tupel of x and y coordinates
   */
  override def getP3Base: (Int,Int) = {
    Settings().start3.head
  }

  /** Get coordinates of the base of the 4. player.
   *
   *  @return tupel of x and y coordinates
   */
  override def getP4Base: (Int,Int) = {
    Settings().start4.head
  }

  /** Get coordinates of the goal cell.
   *
   *  @return tupel of x and y coordinates
   */
  override def getGoalBase: (Int,Int) = {
    Settings().goalCell
  }

  /** String representation of the game board.
   *
   * @return game board string
   */
  override def toString: String = {
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}