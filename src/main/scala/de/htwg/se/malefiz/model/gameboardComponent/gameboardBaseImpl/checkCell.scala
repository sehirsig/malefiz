package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.moveTypes._
import de.htwg.se.malefiz.model.playerComponent.Player
import scala.util.{Failure, Success}

/** Main logic of the Malefiz game. Here turns are managed and subsequent cells are evaluated.
 *
 *  @author sehirsig & franzgajewski
 */
object checkCell {

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
  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    /** Test if the Next Cell in this direction is even Walkable */
    isWalkable(spielbrett, goUp(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        /** Choose the current figure of the chosen player */
        val currentfigure = player.figures(figurenum)
        /** Move the player's game figure to the next position */
        val spielbrett2 = spielbrett.movePlayer(goUp(currentCoord), PlayerCell(player.Playerid, figurenum + 1))
        /** Reset the previous block to what it was (when player walked over it) */
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        /** Check the cell you are walking on for more options */
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goUp(currentCoord), walksLeft)
        /** Update the internal coordinates of the game figure. */
        player.figures(figurenum) = currentfigure.updatePos(goUp(currentCoord)._1, goUp(currentCoord)._2)
        /** Return true (it worked), and the new game board */
        (true, spielbrett4)
      }
        /** return false, (walking NOT possible), and the old game board */
      case _ => (false, spielbrett)
    }
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
  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goDown(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goDown(currentCoord), PlayerCell(player.Playerid, figurenum + 1))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goDown(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goDown(currentCoord)._1, goDown(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
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
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goLeft(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goLeft(currentCoord), PlayerCell(player.Playerid, figurenum + 1))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goLeft(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goLeft(currentCoord)._1, goLeft(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
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
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goRight(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goRight(currentCoord), PlayerCell(player.Playerid, figurenum + 1))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goRight(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goRight(currentCoord)._1, goRight(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
  }

  /** Check if the targeted cell is walkable. */
  def isWalkable(x: GameboardInterface, currentCoord: (Int, Int), walksleft: Int, Playerid: Int): Boolean = {
    getCell(x, currentCoord) match {
      case FreeCell => true
      case SecureCell => true
      case PlayerCell(Playerid,_) => if (walksleft == 1) {
        false
      } else {
        true
      }
      case PlayerCell(1,_) => true
      case PlayerCell(2,_) => true
      case PlayerCell(3,_) => true
      case PlayerCell(4,_) => true
      case BlockedCell => walksleft == 1
      case GoalCell => walksleft == 1
      case _ => false
    }
  }

  /** Get cell from game board coordinates. */
  def getCell(x: GameboardInterface, currentCoord: (Int, Int)): Cell = {
    x.cell(currentCoord._1, currentCoord._2)
  }

  /** Get the next cell, to check whether it is blocked or not. */
  def getNextCell(old: GameboardInterface, next: GameboardInterface, currentCoord: (Int, Int), walksleft: Int): GameboardInterface = {
    getCell(old, currentCoord) match {
      case BlockedCell => {
        if (walksleft == 1) {
          replaceBlock(next)
        } else {
          next
        }
      }
      case _ => next
    }
  }

  /** Replace the starting cell with a new cell, when a player leaves the base. */
  def wasStartBlock(x: GameboardInterface, currentCoord: (Int, Int)): Cell = {
    getCell(x, currentCoord) match {
      case Start1Cell => Start1Cell
      case Start2Cell => Start2Cell
      case Start3Cell => Start3Cell
      case Start4Cell => Start4Cell
      case _ => secureORfreeCell(currentCoord)
    }
  }

  /** Replace a cell with a FreeCell or SecureCell, depending on the settings class.
   * Info: No Barricades can be placed on SecureCells. They are used for the two lowermost rows and otherwise indistinguishable form FreeCells.
   */
  def secureORfreeCell(currentCoord: (Int, Int)): Cell = {
    Settings().secureCells.contains(currentCoord) match {
      case true => SecureCell
      case false => FreeCell
    }
  }

  /** Replace the game board with a new game board with a new cell.
   * In case the new cell cannot be placed, the old game board is returned.
   */
  def replaceIt(x: Gameboard, intx:Int, inty:Int): GameboardInterface = {
    var newboard = x
    val returnedBoard = x.replaceCell(intx, inty, BlockedCell)
    returnedBoard match {
      case Success(v) => newboard = v
      case Failure(_) => newboard
    }
    newboard
  }

  /** Function for the barricade remove/replace strategy pattern */
  def replaceBlock(x: GameboardInterface): GameboardInterface = {
    x.replaceBlocks(x)
  }
}
