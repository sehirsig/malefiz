package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model.{Cell, Gameboard, checkCell}
import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.moveTypes._

class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {
  var savedG = controllerH.gameboard
  var savedMC = controllerH.moveCounter
  var savedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
  var savedSt = controllerH.gameStatus

  override def doStep: Unit = {
    savedG = controllerH.gameboard

    var sucInp:Boolean = false
    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1)
    currentplayer.addFigure()

    val currentfigure = currentplayer.figures(figurenum)
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2)

    var cmd = "g"
    var savetuple = (sucInp, controllerH.gameboard)
    direction match {
      case "w" =>  {
        savetuple = checkCell.walkUp(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)
        sucInp = savetuple._1
        controllerH.gameboard = savetuple._2}
      case "a" => {
        savetuple = checkCell.walkLeft(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)
        sucInp = savetuple._1
        controllerH.gameboard = savetuple._2}
      case "s" => {
        savetuple = checkCell.walkDown(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)
        sucInp = savetuple._1
        controllerH.gameboard = savetuple._2}
      case "d" => {
        savetuple = checkCell.walkRight(controllerH.gameboard, currentplayer, fig_coord, figurenum, controllerH.moveCounter)
        sucInp = savetuple._1
        controllerH.gameboard = savetuple._2}
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
    val newsavedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
    val newsavedSt = controllerH.gameStatus
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures = savedF
    controllerH.gameStatus = savedSt
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
  }

  override def redoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedF = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures
    val newsavedSt = controllerH.gameStatus
    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1).figures = savedF
    controllerH.gameStatus = savedSt
    savedF = newsavedF
    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
  }
}
