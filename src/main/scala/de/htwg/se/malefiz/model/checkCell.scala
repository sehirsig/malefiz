package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.moveTypes._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.BlockStrategy

object checkCell {
  def walkUp(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int, walksLeft:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goUp(currentCoord), walksLeft, player.Playerid) match { //Test if the Next Cell in this direction is even Walkable
      case true => {
        val currentfigure = player.figures(figurenum) // Choose the current figure of the chosen Player
        val spielbrett2 = spielbrett.movePlayer(goUp(currentCoord),PlayerCell(player.Playerid)) //Move the Players Gamefigure to the next position
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord)) // Reset the Previous block to what it was (when Player walked over it)
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goUp(currentCoord), walksLeft, player.Playerid) // Check the cell you're walking on for more options
        player.figures(figurenum) = currentfigure.updatePos(goUp(currentCoord)._1,goUp(currentCoord)._2) // Update the internal coordinates of the game figure.
        (true,spielbrett4) // Return true (It worked), and the new gameboard
      }
      case _ => (false,spielbrett) // return false, (walking NOT possible), and the old gameboard
    }
  }

  def walkDown(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int, walksLeft:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goDown(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goDown(currentCoord),PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goDown(currentCoord), walksLeft, player.Playerid)
        player.figures(figurenum) = currentfigure.updatePos(goDown(currentCoord)._1,goDown(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }

  def walkLeft(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int, walksLeft:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goLeft(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goLeft(currentCoord),PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goLeft(currentCoord), walksLeft, player.Playerid)
        player.figures(figurenum) = currentfigure.updatePos(goLeft(currentCoord)._1,goLeft(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }
  def walkRight(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int, walksLeft:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goRight(currentCoord), walksLeft, player.Playerid) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goRight(currentCoord),PlayerCell(player.Playerid))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = getNextCell(spielbrett, spielbrett3, goRight(currentCoord), walksLeft, player.Playerid)
        player.figures(figurenum) = currentfigure.updatePos(goRight(currentCoord)._1,goRight(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }

  def isWalkable(x:Gameboard, currentCoord:(Int,Int), walksleft:Int, Playerid:Int):Boolean = {
    getCell(x, currentCoord) match {
      case FreeCell => true
      case SecureCell => true
      case PlayerCell(Playerid) => if (walksleft == 1 ) {false} else {true}
      case PlayerCell(1) => true
      case PlayerCell(2) => true
      case PlayerCell(3) => true
      case PlayerCell(4) => true
      case BlockedCell => walksleft == 1
      case GoalCell => walksleft == 1
      case _ => false
    }
  }

  def getCell(x:Gameboard, currentCoord:(Int,Int)):Cell = {
    x.cell(currentCoord._1, currentCoord._2)
  }

  def getNextCell(old:Gameboard, next:Gameboard, currentCoord:(Int,Int), walksleft:Int, PlayeriD:Int): Gameboard = {
    getCell(old, currentCoord) match {
      case BlockedCell => {if(walksleft == 1 ) {replaceBlock(next)} else {next}}
      case _ => next
    }
  }

  def wasStartBlock(x:Gameboard, currentCoord:(Int,Int)):Cell = {
    getCell(x, currentCoord) match {
      case StartCell => StartCell
      case _ => secureORfreeCell(currentCoord)
    }
  }

  def secureORfreeCell(currentCoord:(Int,Int)):Cell = {
    Settings().secureCells.contains(currentCoord) match {
      case true => SecureCell
      case false => FreeCell
    }
  }

  def replaceBlock(x:Gameboard) : Gameboard = {
    x.blockStrategy.replaceBlock(x)
  }
}
