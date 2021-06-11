package de.htwg.se.malefiz.controller.controllerComponent

import de.htwg.se.malefiz.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.lastSaveInterface
import de.htwg.se.malefiz.model.playerComponent.PlayerBuilder

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var gameStatus: GameStatus
  var playerStatus: PlayerState
  var moveCounter: Int
  val builder: PlayerBuilder
  var game: Game
  var gameWon: (Boolean, String)
  var savedGame: lastSaveInterface
  var selectedFigNum: Int
  def resetGame(): Unit
  def selectFigure(x: Int): Unit
  def addPlayer(): Unit
  def addPlayerName(name: String): Unit
  def startGame(): Unit
  def boardToString(): String
  def rollDice(): Int
  def checkWin(): Unit
  def setBlockStrategy(blockStrategy: String): Unit
  def move(input: String, figurenum: Int): Unit
  def emptyMan: Unit
  def undoAll: Unit
  def undo: Unit
  def redo: Unit
}

import scala.swing.event.Event

class RollDice extends Event
class ChooseFig extends Event
class Moving extends Event
class SettingUp extends Event
class StartUp extends Event
class StartGame extends Event
class WonGame extends Event
class GameReset extends Event