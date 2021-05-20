package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.Cell
import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.moveTypes._

class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {
  var saved = controllerH.gameboard


  override def doStep: Unit = {
    saved = controllerH.gameboard
    var sucInp:Boolean = false
    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1)
    currentplayer.addFigure()

    val currentfigure = currentplayer.figures(figurenum)
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2)
    val currentCell = Cell("XX")
    direction match {
      case "w" =>  {
        controllerH.gameboard = controllerH.gameboard.movePlayer(goUp(fig_coord),Cell(currentplayer.Playerid.toString+ " "))
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord,currentCell) //TODO Duplicate entfernen
        currentplayer.figures(figurenum) = currentfigure.updatePos(goUp(fig_coord)._1,goUp(fig_coord)._2)
        sucInp = true }
      case "a" => {
        controllerH.gameboard = controllerH.gameboard.movePlayer(goLeft(fig_coord),Cell(currentplayer.Playerid.toString+ " "))
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord,currentCell)
        currentplayer.figures(figurenum) = currentfigure.updatePos(goLeft(fig_coord)._1,goLeft(fig_coord)._2)
        sucInp = true }
      case "s" => {
        controllerH.gameboard = controllerH.gameboard.movePlayer(goDown(fig_coord),Cell(currentplayer.Playerid.toString+ " "))
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord,currentCell)
        currentplayer.figures(figurenum) = currentfigure.updatePos(goDown(fig_coord)._1,goDown(fig_coord)._2)
        sucInp = true }
      case "d" => {
        controllerH.gameboard = controllerH.gameboard.movePlayer(goRight(fig_coord),Cell(currentplayer.Playerid.toString+ " "))
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord,currentCell)
        currentplayer.figures(figurenum) = currentfigure.updatePos(goRight(fig_coord)._1,goRight(fig_coord)._2)
        sucInp = true }
      case _ =>
    }
    println(controllerH.moveCounter - 1)
    if(sucInp) {
      controllerH.moveCounter -= 1
    } else {
      println("Wrong Input")
    }
    if(controllerH.moveCounter < 1) {
      controllerH.gameStatus = PLAYING
      controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayerNumber())
    }
  }

  override def undoStep: Unit = {
    val newsaved = controllerH.gameboard
    controllerH.gameboard = saved
    saved = newsaved
  }

  override def redoStep: Unit = {
    val newsaved = controllerH.gameboard
    controllerH.gameboard = saved
    saved = newsaved
  }
}
