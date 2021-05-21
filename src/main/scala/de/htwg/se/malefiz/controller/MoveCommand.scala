package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.Cell
import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.moveTypes._
import de.htwg.se.malefiz.model.checkCell

class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {
  var savedG = controllerH.gameboard
  var savedMC = controllerH.moveCounter
  var savedF = controllerH.game
  var savedSt = controllerH.gameStatus

  override def doStep: Unit = {
    savedG = controllerH.gameboard

    var sucInp:Boolean = false
    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1)
    currentplayer.addFigure()

    val currentfigure = currentplayer.figures(figurenum)
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2)
    val currentCell = Cell("XX")
    direction match {
      case "w" =>  {
        //checkCell.walkUp(controllerH.gameboard, currentplayer, fig_coord, figurenum)
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
    println(controllerH.moveCounter - 1) // TODO Raus damit
    if(sucInp) {
      controllerH.moveCounter -= 1
    } else {
      println("Wrong Input") // TODO raus damit
    }
    if(controllerH.moveCounter < 1) {
      controllerH.gameStatus = PLAYING
      controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayerNumber())
    }
  }

  override def undoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedF = controllerH.game
    val newsavedSt = controllerH.gameStatus
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game = savedF
    controllerH.gameStatus = savedSt
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
  }

  override def redoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedF = controllerH.game
    val newsavedSt = controllerH.gameStatus
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game = savedF
    controllerH.gameStatus = savedSt
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
  }
}
