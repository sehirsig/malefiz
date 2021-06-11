package de.htwg.se.malefiz.model.gameboardComponent.gameboardMockImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{BlockReplaceStrategy, Gameboard}
import de.htwg.se.malefiz.model.playerComponent.Player

import scala.util.{Failure, Success, Try}

case class Gameboard(rows: Vector[Vector[Cell]]) extends GameboardInterface {
  override def setBlockStrategy(blockstrategy: String): Unit = BlockReplaceStrategy()
  override def movePlayer(coord: (Int, Int), cell: Cell): GameboardInterface = this
  override def cell(row: Int, col: Int): Cell = InvalidCell
  override def newGBStandardSize: GameboardInterface = this
  override def getStandardXYsize: (Int,Int) = (0,0)
  override def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = this
  override def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  override def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  override def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  override def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  override def diceRoll: Int = 0
  override def goDown(oldcord: (Int, Int)): (Int, Int) = (0,0)
  override def goUp(oldcord: (Int, Int)): (Int, Int) = (0,0)
  override def goRight(oldcord: (Int, Int)): (Int, Int) = (0,0)
  override def goLeft(oldcord: (Int, Int)): (Int, Int) = (0,0)
  override def getCell(name:String): Cell = InvalidCell
  override def checkPlayerOnGoal: Boolean = false
  override def cellString(row: Int, col: Int): String = ""
  override def replaceCell(row: Int, col: Int, cell: Cell): Try[GameboardInterface] = ???
}
