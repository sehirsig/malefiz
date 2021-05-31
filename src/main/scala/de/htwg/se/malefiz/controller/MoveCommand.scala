package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.{Cell, Gameboard, InvalidCell, PlayerCell, checkCell}
import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.moveTypes._

class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {
  var savedG = controllerH.gameboard
  var savedMC = controllerH.moveCounter
  var savedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
  var savedSt = controllerH.gameStatus
  var savedSG = controllerH.savedGame

  override def doStep: Unit = {
    savedG = controllerH.gameboard

    var sucInp:Boolean = false

    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1)

    (1 to 5).map(x => currentplayer.addFigure(x))

    val currentfigure = currentplayer.figures(figurenum)
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2)
    var lastCell:Cell = InvalidCell
    var savetuple = (sucInp, controllerH.gameboard)
    direction match {
      case "w" =>  {
        savetuple = checkCell.walkUp(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)}
        lastCell = savedG.cell(goUp(fig_coord)._1, goUp(fig_coord)._2)
      case "a" => {
        savetuple = checkCell.walkLeft(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)}
        lastCell = savedG.cell(goLeft(fig_coord)._1, goLeft(fig_coord)._2)
      case "s" => {
        savetuple = checkCell.walkDown(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)}
        lastCell = savedG.cell(goDown(fig_coord)._1, goDown(fig_coord)._2)
      case "d" => {
        savetuple = checkCell.walkRight(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)}
        lastCell = savedG.cell(goRight(fig_coord)._1, goRight(fig_coord)._2)
      case _ =>
    }

    sucInp = savetuple._1
    controllerH.gameboard = savetuple._2

    println(controllerH.moveCounter - 1) // TODO Raus damit
    if(sucInp) {
      controllerH.moveCounter -= 1
      direction match {
        case "w" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("s")
        case "s" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("w")
        case "a" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("d")
        case "d" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("a")
      }
      if (controllerH.savedGame.lastCell.isInstanceOf[PlayerCell]) {
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord, controllerH.savedGame.lastCell)
      }
      controllerH.savedGame = controllerH.savedGame.updatelastCell(lastCell)
    } else {
      controllerH.undoAll
      println("Wrong Input") // TODO raus damit
      direction match {
        case "skip" => controllerH.moveCounter = 0
        case _ =>
      }
    }
    if(controllerH.moveCounter < 1) {
      controllerH.gameStatus = PLAYING
      controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayerNumber())
      controllerH.emptyMan
      controllerH.savedGame = controllerH.savedGame.updateLastDirection("")
      controllerH.savedGame = controllerH.savedGame.updatelastCell(InvalidCell)
    }
  }

  override def undoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
    val newsavedSt = controllerH.gameStatus
    val newsavedSG = controllerH.savedGame
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures = savedF
    controllerH.gameStatus = savedSt
    controllerH.savedGame = savedSG
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
    savedSG = newsavedSG
  }

  override def redoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
    val newsavedSt = controllerH.gameStatus
    val newsavedSG = controllerH.savedGame
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures = savedF
    controllerH.gameStatus = savedSt
    controllerH.savedGame = savedSG
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
    savedSG = newsavedSG
  }
}
