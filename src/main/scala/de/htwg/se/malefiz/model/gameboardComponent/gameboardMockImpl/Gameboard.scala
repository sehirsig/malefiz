package de.htwg.se.malefiz.model.gameboardComponent.gameboardMockImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.BlockReplaceStrategy
import de.htwg.se.malefiz.model.playerComponent.Player

import scala.util.Try

class Gameboard(rows: Vector[Vector[Cell]]) extends GameboardInterface {
  def setBlockStrategy(blockstrategy: String): Unit = BlockReplaceStrategy()
  def movePlayer(coord: (Int, Int), cell: Cell): GameboardInterface = this
  def replaceCell(row: Int, col: Int, cell: Cell): Try[GameboardInterface] = Try[this]
  def cell(row: Int, col: Int): Cell = InvalidCell
  def newGBStandardSize: GameboardInterface = this
  def getStandardXYsize: (Int,Int) = (0,0)
  def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = this
  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = (false, this)

  def diceRoll: Int = 0

  def goDown(oldcord: (Int, Int)): (Int, Int) = (0,0)
  def goUp(oldcord: (Int, Int)): (Int, Int) = (0,0)
  def goRight(oldcord: (Int, Int)): (Int, Int) = (0,0)
  def goLeft(oldcord: (Int, Int)): (Int, Int) = (0,0)

  def getCell(name:String): Cell = InvalidCell
  def checkPlayerOnGoal: Boolean = false
  def cellString(row: Int, col: Int): String = ""
}
