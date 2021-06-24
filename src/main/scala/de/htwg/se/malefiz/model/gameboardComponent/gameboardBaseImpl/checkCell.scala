/*
Class: gameboardBaseImpl/checkCell.scala

Beschreibung:
Hauptlogik unseres Malefiz-Spiels. Hier werden Züge verwaltet, so wie nachfolgende Zellen angeschaut.

 */

package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.moveTypes._
import de.htwg.se.malefiz.model.playerComponent.Player

import scala.util.{Failure, Success}

object checkCell {
  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goUp(currentCoord), walksLeft, player.Playerid) match { //Test if the Next Cell in this direction is even Walkable
      case true => {
        val currentfigure = player.figures(figurenum) // Choose the current figure of the chosen Player
        val spielbrett2 = spielbrett.movePlayer(goUp(currentCoord), PlayerCell(player.Playerid)) //Move the Players Gamefigure to the next position
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord)) // Reset the Previous block to what it was (when Player walked over it)
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goUp(currentCoord), walksLeft) // Check the cell you're walking on for more options
        player.figures(figurenum) = currentfigure.updatePos(goUp(currentCoord)._1, goUp(currentCoord)._2) // Update the internal coordinates of the game figure.
        (true, spielbrett4) // Return true (It worked), and the new gameboard
      }
      case _ => (false, spielbrett) // return false, (walking NOT possible), and the old gameboard
    }
  }

  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goDown(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goDown(currentCoord), PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goDown(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goDown(currentCoord)._1, goDown(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
  }

  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goLeft(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goLeft(currentCoord), PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goLeft(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goLeft(currentCoord)._1, goLeft(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
  }

  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    isWalkable(spielbrett, goRight(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goRight(currentCoord), PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goRight(currentCoord), walksLeft)
        player.figures(figurenum) = currentfigure.updatePos(goRight(currentCoord)._1, goRight(currentCoord)._2)
        (true, spielbrett4)
      }
      case _ => (false, spielbrett)
    }
  }

  // Anschauen, ob man auf die nächste Zelle laufen kann.
  def isWalkable(x: GameboardInterface, currentCoord: (Int, Int), walksleft: Int, Playerid: Int): Boolean = {
    getCell(x, currentCoord) match {
      case FreeCell => true
      case SecureCell => true
      case PlayerCell(Playerid) => if (walksleft == 1) {
        false
      } else {
        true
      }
      case PlayerCell(1) => true
      case PlayerCell(2) => true
      case PlayerCell(3) => true
      case PlayerCell(4) => true
      case BlockedCell => walksleft == 1
      case GoalCell => walksleft == 1
      case _ => false
    }
  }

  //Erhalte die Zelle eines spezielles Spielbretts aus den Koordinaten.
  def getCell(x: GameboardInterface, currentCoord: (Int, Int)): Cell = {
    x.cell(currentCoord._1, currentCoord._2)
  }

  //Die Nächste zelle bekommen, um zu schauen, ob man auf einer Blocked Cell landet.
  def getNextCell(old: GameboardInterface, next: GameboardInterface, currentCoord: (Int, Int), walksleft: Int): GameboardInterface = {
    getCell(old, currentCoord) match {
      case BlockedCell => {
        if (walksleft == 1) {
          replaceBlock(next)
        } else {
          next
        }
      }
      case _ => next
    }
  }

  //Die Startzelle mit einer neuen Startzelle ersetzen, wenn der Spieler aus der Base läuft.
  def wasStartBlock(x: GameboardInterface, currentCoord: (Int, Int)): Cell = {
    getCell(x, currentCoord) match {
      case Start1Cell => Start1Cell
      case Start2Cell => Start2Cell
      case Start3Cell => Start3Cell
      case Start4Cell => Start4Cell
      case _ => secureORfreeCell(currentCoord)
    }
  }

  //Alte Zelle mit SecureCells oder FreeCells ersetzen, je nachdem was in Settings angegeben wurde. Info: Auf SecureCells dürfen keine Blockaden platziert werden!
  def secureORfreeCell(currentCoord: (Int, Int)): Cell = {
    Settings().secureCells.contains(currentCoord) match {
      case true => SecureCell
      case false => FreeCell
    }
  }

  //Ersetzen des Gameboard, indem eine Zelle ersetzt wird, mit Sicherung. Falls ein Invalider Index gewählt wurde, kommt das alte Gameboard zurück.
  def replaceIt(x: Gameboard, intx:Int, inty:Int): GameboardInterface = {
    var newboard = x
    val returnedBoard = x.replaceCell(intx, inty, BlockedCell)
    returnedBoard match {
      case Success(v) => newboard = v
      case Failure(_) => newboard
    }
    newboard
  }

  //Funktion für das BlockRemove/Replace Strategy Pattern
  def replaceBlock(x: GameboardInterface): GameboardInterface = {
    x.replaceBlocks(x)
  }
}
