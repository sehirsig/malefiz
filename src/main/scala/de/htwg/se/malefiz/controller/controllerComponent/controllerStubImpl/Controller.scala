package de.htwg.se.malefiz.controller.controllerComponent.controllerStubImpl

import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameComponent._
import de.htwg.se.malefiz.model.gameboardComponent
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSave, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._

import scala.swing.Publisher //Mock -> Stub

case class Controller(var gameboard: GameboardInterface) extends ControllerInterface with Publisher{
  var gameStatus: GameStatus = IDLE
  def createNewGameboard: Unit = {}
  var playerStatus: PlayerState = PlayerState1
  var moveCounter: Int = 0
  val builder: PlayerBuilder = PlayerBuilderImp()
  var game: Game = Game(Vector[Player]())
  var gameWon: (Boolean, String) = (false, "")
  var savedGame: lastSaveInterface = gameboardComponent.lastSave(0, "", InvalidCell)
  var selectedFigNum: Int = 0;
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
}
