package de.htwg.se.malefiz.controller.controllerComponent.controllerMockImpl

import de.htwg.se.malefiz.controller.controllerComponent.ControllerInterface
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

import scala.swing.Publisher

case class Controller(var gameboard: GameboardInterface) extends ControllerInterface with Publisher{
  override def resetGame(): Unit = {}
  override def selectFigure(x: Int): Unit = {}
  override def addPlayer(): Unit = {}
  override def addPlayerName(name: String): Unit = {}
  override def startGame(): Unit = {}
  override def boardToString(): String = ""
  override def rollDice(): Int = 0
  override def checkWin(): Unit = {}
  override def setBlockStrategy(blockStrategy: String): Unit = {}
  override def move(input: String, figurenum: Int): Unit = {}
  override def emptyMan: Unit = {}
  override def undoAll: Unit = {}
  override def undo: Unit = {}
  override def redo: Unit = {}
}
