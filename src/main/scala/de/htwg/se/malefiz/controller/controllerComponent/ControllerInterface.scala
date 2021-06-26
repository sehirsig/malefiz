package de.htwg.se.malefiz.controller.controllerComponent

import de.htwg.se.malefiz.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._

import scala.swing.Publisher

/** The interface for our controller.
 *
 *  @author sehirsig & franzgajewski
 */
trait ControllerInterface extends Publisher {

  /** Game board. */
  var gameboard: GameboardInterface

  /** Game state for the output. */
  var gameStatus: GameStatus

  /** Player state pattern to determine the next player. */
  var playerStatus: PlayerState

  /** Counter to count the remaining turns. */
  var moveCounter: Int

  /** Player builder with build pattern. */
  val builder: PlayerBuilder

  /** Saving the game including the figures. */
  var game: Game

  /** Saving whether there is a winner and who won. */
  var gameWon: (Boolean, String)

  /** Saving the last game move.*/
  var savedGame: lastSaveInterface

  /** Number of selected figure in the current move. */
  var selectedFigNum: Int

  /** Returns cell from specific string.
   *
   *  @param name pure name of cell
   *  @return pure cell
   */
  def getpureCell(name: String): Cell

  /** Resets the game. */
  def resetGame(): Unit

  /** Chooses one of the 5 game figures.
   *
   *  @param x number [1-5]
   */
  def selectFigure(x: Int): Unit

  /** Adds a player. */
  def addPlayer(): Unit

  /** Adds a name to a player. */
  def addPlayerName(name: String): Unit

  /** Starts the game. */
  def startGame(): Unit

  /** Initializes the game. */
  def setupGame(): Unit

  /** Game board as string representation.
   *
   * @return game board
   */
  def boardToString(): String

  /** Die roll.
   *
   * @return die roll [1-6]
   */
  def rollDice(): Int

  /** Checks whether there is a winner. */
  def checkWin(): Unit

  /** Chooses the block strategy (strategy pattern).
   *
   *  @param blockStrategy "remove" or "replace"
   */
  def setBlockStrategy(blockStrategy: String): Unit

  /** Move the game figures on the game board.
   *
   *  @param input direction
   *  @param figurenum number of game figure
   */
  def move(input: String, figurenum: Int): Unit

  /** Deletes entire undo manager. */
  def emptyMan: Unit

  /** Undoes entire undo manager. */
  def undoAll: Unit

  /** Undoes one step of the undo manager. */
  def undo: Unit

  /** Redoes one step of the undo manager. */
  def redo: Unit

  /** Saves current game state. */
  def save: Unit

  /** Loads current game state. */
  def load: Unit

  /** Adds a player for debug right in front of the goal.
   *
   *  @param name name of debug player
   */
  def addPlayerDEBUGWINTEST(name: String): Unit

  /** Die that rolls only 1. */
  def debugDice(): Unit
}


/** Events, that get called by the Controller to notify listeners. */
import scala.swing.event.Event

class RollDice extends Event
class ChooseFig extends Event
class Moving extends Event
class SettingUp extends Event
class StartUp extends Event
class StartGame extends Event
class WonGame extends Event
class GameReset extends Event
class GameSaved extends Event
class GameLoaded extends Event