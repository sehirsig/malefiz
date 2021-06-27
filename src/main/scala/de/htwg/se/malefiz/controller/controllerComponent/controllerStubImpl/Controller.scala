package de.htwg.se.malefiz.controller.controllerComponent.controllerStubImpl

import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameComponent._
import de.htwg.se.malefiz.model.gameboardComponent
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._

import scala.swing.Publisher

/** A stub implementation of our Controller.
 *  Descriptions of the function in the base implementation.
 *
 *  @author sehirsig & franzgajewski
 */
case class Controller(var gameboard: GameboardInterface) extends ControllerInterface with Publisher{
  override var gameStatus: GameStatus = IDLE
  override var playerStatus: PlayerState = PlayerState1
  override var moveCounter: Int = 0
  override val builder: PlayerBuilder = PlayerBuilderImp()
  override var game: Game = Game(Vector[Player]())
  override var gameWon: (Boolean, String) = (false, "")
  override var savedGame: lastSaveInterface = gameboardComponent.lastSave(0, "", InvalidCell)
  override var selectedFigNum: Int = 0;
  override def getpureCell(name: String): Cell = InvalidCell
  override def resetGame(): Unit = {}
  override def selectFigure(x: Int): Unit = {}
  override def addPlayer(): Unit = {}
  override def addPlayerName(name: String): Unit = {}
  override def startGame(): Unit = {}
  override def setupGame(): Unit = {}
  override def boardToString(): String = ""
  override def rollDice(): Int = 0
  override def checkWin(): Unit = {}
  override def setBlockStrategy(blockStrategy: String): Unit = {}
  override def move(input: String, figurenum: Int): Unit = {}
  override def emptyMan: Unit = {}
  override def undoAll: Unit = {}
  override def undo: Unit = {}
  override def redo: Unit = {}
  override def save: Unit = {}
  override def load: Unit = {}
  override def addPlayerDEBUGWINTEST(name: String): Unit = {}
  override def debugDice(): Unit = {}
}