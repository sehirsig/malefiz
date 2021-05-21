package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.moveTypes._
import de.htwg.se.malefiz.util.BlockStrategy

object checkCell {
  def walkUp(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goUp(currentCoord)) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goUp(currentCoord),Cell(player.Playerid.toString + " "))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = replaceBlock(spielbrett3)
        player.figures(figurenum) = currentfigure.updatePos(goUp(currentCoord)._1,goUp(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }
  def walkDown(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goDown(currentCoord)) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goDown(currentCoord),Cell(player.Playerid.toString + " "))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = replaceBlock(spielbrett3)
        player.figures(figurenum) = currentfigure.updatePos(goDown(currentCoord)._1,goDown(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }
  def walkLeft(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goLeft(currentCoord)) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goLeft(currentCoord),Cell(player.Playerid.toString + " "))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = replaceBlock(spielbrett3)
        player.figures(figurenum) = currentfigure.updatePos(goLeft(currentCoord)._1,goLeft(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }
  def walkRight(spielbrett:Gameboard, player:Player, currentCoord:(Int,Int), figurenum:Int):(Boolean,Gameboard) = {
    isWalkable(spielbrett, goRight(currentCoord)) match {
      case true => {
        val currentfigure = player.figures(figurenum)
        val spielbrett2 = spielbrett.movePlayer(goRight(currentCoord),Cell(player.Playerid.toString + " "))
        val spielbrett3 = spielbrett2.movePlayer(currentCoord, wasStartBlock(spielbrett, currentCoord))
        val spielbrett4 = replaceBlock(spielbrett3)
        player.figures(figurenum) = currentfigure.updatePos(goRight(currentCoord)._1,goRight(currentCoord)._2)
        (true,spielbrett4)
      }
      case _ => (false,spielbrett)
    }
  }
  def isWalkable(x:Gameboard, currentCoord:(Int,Int)):Boolean = {
    getCell(x, currentCoord) match {
      case Cell("O ") => true
      case Cell("P ") => true
      case Cell("X ") => true
      case _ => false
    }
  }
  def getCell(x:Gameboard, currentCoord:(Int,Int)):Cell = {
    x.cell(currentCoord._1, currentCoord._2)
  }
  def getNextCell(x:Gameboard, currentCoord:(Int,Int)): Unit = {
    x.cell(currentCoord._1, currentCoord._2) match {
      case Cell("  ") => None
      case Cell("P ") => kickFigure(x)
      case Cell("X ") => replaceBlock(x)
    }
  }


  def wasStartBlock(x:Gameboard, currentCoord:(Int,Int)):Cell = {
    getCell(x, currentCoord) match {
      case Cell("T ") => Cell("T ")
      case _ => Cell("O ")
    }
  }

  def kickFigure(x:Gameboard): Gameboard = {
    x
  }

  def replaceBlock(x:Gameboard) : Gameboard = {
    x.blockStrategy.replaceBlock(x)
  }
}
