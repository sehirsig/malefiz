/*
Class: gameboardBaseImpl/Gameboard.scala

Beschreibung:
Base Implementierung unseres Spielbretts.

 */

package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import com.google.inject.Inject
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.playerComponent.Player
import de.htwg.se.malefiz.util.BlockStrategy

import scala.util.{Failure, Random, Success, Try}

case class Gameboard(rows: Vector[Vector[Cell]]) extends GameboardInterface {
  @Inject
  def this(sizex: Int, sizey: Int) = this(Vector.tabulate(sizex, sizey) { //Ersetzen aller Zellen, je nachdem was in Settings angegeben wurde.
    (row, col) => {
      if (Settings().blockedCells.contains(row, col)) {
        BlockedCell
      } else if (Settings().freeCells.contains(row, col)) {
        FreeCell
      } else if (Settings().secureCells.contains(row, col)) {
        SecureCell
      } else if (Settings().goalCell == (row, col)) {
        GoalCell
      } else if (Settings().start1.contains(row, col)) {
        Start1Cell
      } else if (Settings().start2.contains(row, col)) {
        Start2Cell
      } else if (Settings().start3.contains(row, col)) {
        Start3Cell
      } else if (Settings().start4.contains(row, col)) {
        Start4Cell
      } else {
        InvalidCell
      }
    }
  })

  var blockStrategy: BlockStrategy = BlockReplaceStrategy() //Initialisierung der Block-Strategy.

  override def setBlockStrategy(blockstrategy: String): Unit = { //Block-Strategy ändern.
    blockstrategy match {
      case "remove" => this.blockStrategy = BlockRemoveStrategy()
      case "replace" => this.blockStrategy = BlockReplaceStrategy()
    }
  }

  override def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = { //Block-Strategy Funktion.
    blockStrategy.replaceBlock(spielbrett)
  }


  override def newGBStandardSize: Gameboard = { //Neues Spielbrett ausgeben, mit der Settings-Standard Größe
    new Gameboard(Settings().xDim, Settings().yDim)
  }

  override def getStandardXYsize: (Int,Int) = { //Die Standardgröße aus Settings bekommen.
    (Settings().xDim, Settings().yDim)
  }

  override def getStringOfCell(cell:Cell): String = { //Eine StringVariante aus der Zelle bekommen (z.B. für XML / JSON)
    cell match {
      case FreeCell => "FreeCell"
      case BlockedCell => "BlockedCell"
      case Start1Cell => "Start1Cell"
      case Start2Cell =>"Start2Cell"
      case Start3Cell =>"Start3Cell"
      case Start4Cell =>"Start4Cell"
      case SecureCell =>"SecureCell"
      case GoalCell =>"GoalCell"
      case InvalidCell =>"InvalidCell"
      case PlayerCell(1) =>"PlayerCell1"
      case PlayerCell(2) =>"PlayerCell2"
      case PlayerCell(3) =>"PlayerCell3"
      case PlayerCell(4) =>"PlayerCell4"
      case _ => "InvalidCell"
    }
  }

  override def cell(row: Int, col: Int): Cell = rows(row)(col) //Bekomme eine Zelle aus Koordinaten.

  override def cellString(row: Int, col: Int): String = getStringOfCell(rows(row)(col)) //Bekomme die Stringdarstellung aus den Koordinaten.

  override def replaceCell(row: Int, col: Int, cell: Cell): Try[Gameboard] = { //Eine Zelle ersetzen mit Fehlerbehandlung. Try-Monade. Für Falsche Indexierung.
    val tmp = Try(copy(rows.updated(row, rows(row).updated(col, cell))))
    tmp match {
      case Success(v) => Success(v)
      case Failure(e) => Failure(e)
    }
  }

  override def movePlayer(coord: (Int, Int), cell: Cell): Gameboard = { //Einen Spieler auf dem Spielbrett bewegen.
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  def moveCell(coord: (Int, Int), cell: Cell): Gameboard = { //Eine Zelle auf dem Spielbrett bewegen.
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  //Implementierungen, damit der Controller auf diese Funktionen indirekt zugreifen kann.
  override def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkUp(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  override def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkDown(spielbrett, player, currentCoord, figurenum, walksLeft)
  }
  override def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkLeft(spielbrett, player, currentCoord, figurenum, walksLeft)
  }
  override def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkRight(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  override def diceRoll: Int = {
    Dice.diceRoll
  }

  override def goDown(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goDown(oldcord)
  }
  override def goUp(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goUp(oldcord)
  }
  override def goRight(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goRight(oldcord)
  }
  override def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goLeft(oldcord)
  }


  override def getCell(name: String): Cell = { //Eine Zelle aus der StringVariante bekommen (z.B. für XML / JSON)
    name match {
      case "FreeCell" => FreeCell
      case "BlockedCell" => BlockedCell
      case "Start1Cell" => Start1Cell
      case "Start2Cell" => Start2Cell
      case "Start3Cell" => Start3Cell
      case "Start4Cell" => Start4Cell
      case "SecureCell" => SecureCell
      case "GoalCell" => GoalCell
      case "InvalidCell" => InvalidCell
      case "PlayerCell1" => PlayerCell(1)
      case "PlayerCell2" => PlayerCell(2)
      case "PlayerCell3" => PlayerCell(3)
      case "PlayerCell4" => PlayerCell(4)
    }
  }

  def checkPlayerOnGoal: Boolean = { //Überprüfen, ob ein Spieler im Ziel ist.
    cell(1, 9).isInstanceOf[PlayerCell]
  }


  //get*Base, damit der Controller auf die Basis Koordinaten zugreifen kann.
  override def getP1Base: (Int,Int) = {
    Settings().start1.head
  }
  override def getP2Base: (Int,Int) = {
    Settings().start2.head
  }
  override def getP3Base: (Int,Int) = {
    Settings().start3.head
  }
  override def getP4Base: (Int,Int) = {
    Settings().start4.head
  }
  override def getGoalBase: (Int,Int) = {
    Settings().goalCell
  }


  override def toString: String = { //Spielbrett als String ausgeben.
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}
