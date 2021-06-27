package de.htwg.se.malefiz.model.gameboardComponent

import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.playerComponent.Player
import scala.util.Try

/** Interface for the game board implementation.
 *
 *  @author sehirsig & franzgajewski
 */
trait GameboardInterface {

  /** Game board matrix. */
  val rows:Vector[Vector[Cell]]

  /** Change of the block strategy.
   *
   *  @param blockstrategy "remove" or "replace"
   */
  def setBlockStrategy(blockstrategy: String): Unit

  /** Function for handling barricades according to the block strategy.
   *
   *  @param spielbrett old game board
   *  @return new game board with changed blocking
   */
  def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface

  /** New game board in the dimensions given in the settings class.
   *
   *  @return new game board
   */
  def newGBStandardSize: GameboardInterface

  /** Retrieve default size from the settings class.
   *
   *  @return tupel of the default x and y dimensions of the game board
   */
  def getStandardXYsize: (Int,Int)

  /** Get name of cell (e.g. for JSON/XMY storage).

   *  @param cell
   *
   *  @return string representation of cell
   */
  def getStringOfCell(cell:Cell): String

  /** Returns a cell as coordinates of the game board.
   *
   *  @param row x-coordinate of the matrix
   *  @param col y-coordinate of the matric
   *  @return cell at the given coordinates
   */
  def cell(row: Int, col: Int): Cell

  /** Takes cell coordinates and returns corresponding string representation.
   *
   *  @param row x coordinate
   *  @param col y coordinate
   *
   *  @return string representation of cell
   */
  def cellString(row: Int, col: Int): String

  /** Change a cell on the game board.
   *
   *  @param row x-coordinate of the matrix
   *  @param col y-coordinate of the matric
   *  @param cell cell to be replaced
   *  @return new game board
   */
  def replaceCell(row: Int, col: Int, cell: Cell): Try[GameboardInterface]

  /** Move a player.
   *
   *  @param coord coordinate as an int-tupel of the matrix
   *  @param cell cell to be replaced
   *  @return new game board
   */
  def movePlayer(coord: (Int, Int), cell: Cell): GameboardInterface

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
  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

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
  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

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
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

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
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  /** Die roll.
   *
   *  @return number from 1 to 6
   */
  def diceRoll: Int


  /** Changes coordinates of a tupel as to go down one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  def goDown(oldcord: (Int, Int)): (Int, Int)

  /** Changes coordinates of a tupel as to go up one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  def goUp(oldcord: (Int, Int)): (Int, Int)

  /** Changes coordinates of a tupel as to go right one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  def goRight(oldcord: (Int, Int)): (Int, Int)

  /** Changes coordinates of a tupel as to go left one cell.
   *
   *  @param oldcord tupel of old x and y coordinates
   *
   *  @return tupel of new x and y coordinates
   */
  def goLeft(oldcord: (Int, Int)): (Int, Int)

  /** Takes a string and returns the corresponding cell.
   *
   *  @param name string representation of cell
   *
   *  @return cell
   */
  def getCell(name:String): Cell

  /** Checks if a player is on the goal.
   *
   *  @return boolean
   */
  def checkPlayerOnGoal: Boolean

  /** Get coordinates of the base of the 1. player.
   *
   *  @return tupel of x and y coordinates
   */
  def getP1Base: (Int,Int)

  /** Get coordinates of the base of the 2. player.
   *
   *  @return tupel of x and y coordinates
   */
  def getP2Base: (Int,Int)

  /** Get coordinates of the base of the 3. player.
   *
   *  @return tupel of x and y coordinates
   */
  def getP3Base: (Int,Int)

  /** Get coordinates of the base of the 4. player.
   *
   *  @return tupel of x and y coordinates
   */
  def getP4Base: (Int,Int)

  /** Get coordinates of the goal cell.
   *
   *  @return tupel of x and y coordinates
   */
  def getGoalBase: (Int,Int)
}

/** Interface for the last move of a player.
 *
 *  @author sehirsig & franzgajewski
 */
trait lastSaveInterface {

  /** Last rolled number.
   *
   *  @return Int
   */
  val lastFullDice: Int

  /** Last walked direction.
   *
   *  @return string "w", "s", "a" or "d"
   */
  val lastDirectionOpposite: String

  /** Cell last occupied by a given figure.
   *
   *  @return cell
   */
  val lastCell: Cell

  /** Update last rolled number.
   *
   *  @param newNum newly rolled number
   *  @return new interface
   */
  def updateLastFullDice(newNum: Int): lastSaveInterface

  /** Update last walked direction.
   *
   *  @param newDic new direction
   *  @return new interface
   */
  def updateLastDirection(newDic: String): lastSaveInterface

  /** Update last occupied cell.
   *
   *  @param newCel new cell
   *  @return new interface.
   */
  def updatelastCell(newCel: Cell): lastSaveInterface
}
