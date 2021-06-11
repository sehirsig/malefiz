package de.htwg.se.malefiz.model.gameboardComponent

import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Cell, Gameboard}
import de.htwg.se.malefiz.model.playerComponent.Player

import scala.util.Try

trait GameboardInterface {
  val rows:Vector[Vector[Cell]]
  def setBlockStrategy(blockstrategy: String): Unit
  def movePlayer(coord: (Int, Int), cell: String): GameboardInterface
  def replaceCell(row: Int, col: Int, cell: String): Try[GameboardInterface]
  def cell(row: Int, col: Int): Cell

  def newGBStandardSize: GameboardInterface
  def getStandardXYsize: (Int,Int)

  def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface

  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)
  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  def diceRoll: Int

  def goDown(oldcord: (Int, Int)): (Int, Int)
  def goUp(oldcord: (Int, Int)): (Int, Int)
  def goRight(oldcord: (Int, Int)): (Int, Int)
  def goLeft(oldcord: (Int, Int)): (Int, Int)

  def getCell(name:String): Cell
  def checkPlayerOnGoal: Boolean
  def cellString(row: Int, col: Int): String
}

trait lastSaveInterface {
  val lastFullDice: Int
  val lastDirectionOpposite: String
  val lastCell: String
  def updateLastFullDice(newNum: Int): lastSaveInterface
  def updateLastDirection(newDic: String): lastSaveInterface
  def updatelastCell(newCel: String): lastSaveInterface
}
