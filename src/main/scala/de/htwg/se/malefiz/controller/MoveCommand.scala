package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Malefiz.controller
import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.util.Command

class MoveCommand(direction:String, controllerH: Controller) extends Command {
  var saved = controllerH.gameboard


  override def doStep: Unit = {
    saved = controllerH.gameboard
    var sucInp:Boolean = false

    direction match {
      case "w" => controllerH.gameboard = controllerH.gameboard.replaceCell(1,1,controllerH.replaceCell);sucInp = true
      case "a" => controllerH.gameboard = controllerH.gameboard.replaceCell(2,2,controllerH.replaceCell);sucInp = true
      case "s" => controllerH.gameboard = controllerH.gameboard.replaceCell(3,3,controllerH.replaceCell);sucInp = true
      case "d" => controllerH.gameboard = controllerH.gameboard.replaceCell(4,4,controllerH.replaceCell);sucInp = true
      case _ =>
    }
    if(controllerH.moveCounter == 1) {
      controllerH.gameStatus = PLAYING
      controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayers())
    }
    println(controllerH.moveCounter)
    if(sucInp) {
      println(controllerH.moveCounter)
      controllerH.moveCounter -= 1
    } else {
      println("Wrong Input")
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
