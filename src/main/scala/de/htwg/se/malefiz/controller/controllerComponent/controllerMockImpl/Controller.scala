package de.htwg.se.malefiz.controller.controllerComponent.controllerMockImpl

import de.htwg.se.malefiz.controller.controllerComponent.ControllerInterface

class Controller extends ControllerInterface{
  def resetGame(): Unit = {}
  def selectFigure(x: Int): Unit = {}
  def addPlayer(): Unit = {}
  def addPlayerName(name: String): Unit = {}
  def startGame(): Unit = {}
  def boardToString(): String = ""
  def rollDice(): Int = 0
  def checkWin(): Unit = {}
  def setBlockStrategy(blockStrategy: String): Unit = {}
  def move(input: String, figurenum: Int): Unit = {}
  def emptyMan: Unit = {}
  def undoAll: Unit = {}
  def undo: Unit = {}
  def redo: Unit = {}
}
