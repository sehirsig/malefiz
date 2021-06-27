package de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.util.Command

/** Manages each individual movement.
 * Via the command pattern moves can be undone and redone.
 *
 *  @author sehirsig & franzgajewski
 */
class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {

  /** Saves game board. */
  var savedG = controllerH.gameboard

  /** Counts remaining turns. */
  var savedMC = controllerH.moveCounter

  /** Game state. */
  var savedSt = controllerH.gameStatus

  /** Information on the last move. */
  var savedSG = controllerH.savedGame

  /** "Game" saves the players. */
  var savedGM = controllerH.game

  //Saving game figures
  //<editor-fold desc="Gamefigure Saves">

  var savedF1 = controllerH.game.players(0).figures(0)
  var savedF2 = controllerH.game.players(0).figures(1)
  var savedF3 = controllerH.game.players(0).figures(2)
  var savedF4 = controllerH.game.players(0).figures(3)
  var savedF5 = controllerH.game.players(0).figures(4)
  var savedF6 = controllerH.game.players(1).figures(0)
  var savedF7 = controllerH.game.players(1).figures(1)
  var savedF8 = controllerH.game.players(1).figures(2)
  var savedF9 = controllerH.game.players(1).figures(3)
  var savedF10 = controllerH.game.players(1).figures(4)
  var savedF11 = controllerH.game.players(0).figures(0)
  var savedF12 = controllerH.game.players(0).figures(1)
  var savedF13 = controllerH.game.players(0).figures(2)
  var savedF14 = controllerH.game.players(0).figures(3)
  var savedF15 = controllerH.game.players(0).figures(4)
  var savedF16 = controllerH.game.players(1).figures(0)
  var savedF17 = controllerH.game.players(1).figures(1)
  var savedF18 = controllerH.game.players(1).figures(2)
  var savedF19 = controllerH.game.players(1).figures(3)
  var savedF20 = controllerH.game.players(1).figures(4)
  controllerH.game.players.length match {
    case 2 => {}
    case 3 => {
      savedF11 = controllerH.game.players(2).figures(0)
      savedF12 = controllerH.game.players(2).figures(1)
      savedF13 = controllerH.game.players(2).figures(2)
      savedF14 = controllerH.game.players(2).figures(3)
      savedF15 = controllerH.game.players(2).figures(4)
    }
    case 4 => {
      savedF11 = controllerH.game.players(2).figures(0)
      savedF12 = controllerH.game.players(2).figures(1)
      savedF13 = controllerH.game.players(2).figures(2)
      savedF14 = controllerH.game.players(2).figures(3)
      savedF15 = controllerH.game.players(2).figures(4)
      savedF16 = controllerH.game.players(3).figures(0)
      savedF18 = controllerH.game.players(3).figures(1)
      savedF17 = controllerH.game.players(3).figures(2)
      savedF19 = controllerH.game.players(3).figures(3)
      savedF20 = controllerH.game.players(3).figures(4)
    }
  }
  //</editor-fold>

  override def doStep: Unit = {
    /** Boolean indicates whether move was legal and successful. */
    var sucInp:Boolean = false

    /** Select current player. */
    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1)

    /** Select current game figure. */
    val currentfigure = currentplayer.figures(figurenum-1)

    /** Coordinates of the current game figure. */
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2)

    /** Initialization of the last cell (which will be replaced in the next step). */
    var lastCell:Cell = InvalidCell

    /** Tupel the Boolean and the new game board. */
    var savetuple = (sucInp, controllerH.gameboard)

    /** New position of the game figure. */
    var newpos = fig_coord
    direction match {
      case "w" =>  {
        /** Returns TRUE, if walking worked and saves game board. */
        savetuple = savedG.walkUp(controllerH.gameboard, currentplayer, fig_coord, figurenum-1, controllerH.moveCounter)}
        /** Saves last cell to replace it afterwards. */
        lastCell = savedG.cell(savedG.goUp(fig_coord)._1, savedG.goUp(fig_coord)._2)
        /** Saves newposition to look for player kick. */
        newpos = savedG.goUp(fig_coord)
      case "a" => {
        savetuple = savedG.walkLeft(controllerH.gameboard, currentplayer, fig_coord, figurenum-1, controllerH.moveCounter)}
        lastCell = savedG.cell(savedG.goLeft(fig_coord)._1, savedG.goLeft(fig_coord)._2)
        newpos = savedG.goLeft(fig_coord)
      case "s" => {
        savetuple = savedG.walkDown(controllerH.gameboard, currentplayer, fig_coord, figurenum-1, controllerH.moveCounter)}
        lastCell = savedG.cell(savedG.goDown(fig_coord)._1, savedG.goDown(fig_coord)._2)
        newpos = savedG.goDown(fig_coord)
      case "d" => {
        savetuple = savedG.walkRight(controllerH.gameboard, currentplayer, fig_coord, figurenum-1, controllerH.moveCounter)}
        lastCell = savedG.cell(savedG.goRight(fig_coord)._1, savedG.goRight(fig_coord)._2)
        newpos = savedG.goRight(fig_coord)
      case _ =>
    }

    /** If 0, kick player. */
    if ((controllerH.moveCounter - 1) == 0) {
      controllerH.game.players.map(b => b.figures.map(k => {
        /** Reset position of the figure of the kicked player to starting position. */
        if (((k.pos._1, k.pos._2) == newpos) && (k.player != currentplayer)) {
          k.player.figures(k.getNumber) = k.player.figures(k.getNumber).updatePos(k.player.startingPos)
          controllerH.gameboard = controllerH.gameboard.movePlayer(k.player.startingPos, k.player.cell)
        }
      }))
    }

    /** Boolean indicates if move was successful. */
    sucInp = savetuple._1

    /** Newgame board. */
    controllerH.gameboard = savetuple._2

    if(sucInp) {
      controllerH.moveCounter -= 1
      /** Lock the reverse direction to avoid players shuffling between two cells. */
      direction match {
        case "w" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("s")
        case "s" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("w")
        case "a" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("d")
        case "d" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("a")
      }
      /** If traversing over another game figure, repaint that one. */
      if (controllerH.savedGame.lastCell.isInstanceOf[PlayerCell]) {
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord, controllerH.savedGame.lastCell)
      }
      controllerH.savedGame = controllerH.savedGame.updatelastCell(lastCell)
    } else {
      /** When illegal move, reset turn and start over. */
      controllerH.undoAll
      direction match {
        case "skip" => controllerH.moveCounter = 0
        case _ =>
      }
    }
    /** When last move, initiate next player's turn. */
    if(controllerH.moveCounter < 1) {
        controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayerNumber())
      /** Empty the undo manager to be able to completetly reset it when moved into wrong direction. */
        controllerH.emptyMan
        controllerH.savedGame = controllerH.savedGame.updateLastDirection("")
        controllerH.savedGame = controllerH.savedGame.updatelastCell(InvalidCell)
    }
  }

  override def undoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedSt = controllerH.gameStatus
    val newsavedSG = controllerH.savedGame
    val newsavedGM = controllerH.game

    //Saving game figures
    //<editor-fold desc="Gamefigure Saves">

    var newsavedF1 = controllerH.game.players(0).figures(0)
    var newsavedF2 = controllerH.game.players(0).figures(1)
    var newsavedF3 = controllerH.game.players(0).figures(2)
    var newsavedF4 = controllerH.game.players(0).figures(3)
    var newsavedF5 = controllerH.game.players(0).figures(4)
    var newsavedF6 = controllerH.game.players(1).figures(0)
    var newsavedF7 = controllerH.game.players(1).figures(1)
    var newsavedF8 = controllerH.game.players(1).figures(2)
    var newsavedF9 = controllerH.game.players(1).figures(3)
    var newsavedF10 = controllerH.game.players(1).figures(4)
    var newsavedF11 = controllerH.game.players(0).figures(0)
    var newsavedF12 = controllerH.game.players(0).figures(1)
    var newsavedF13 = controllerH.game.players(0).figures(2)
    var newsavedF14 = controllerH.game.players(0).figures(3)
    var newsavedF15 = controllerH.game.players(0).figures(4)
    var newsavedF16 = controllerH.game.players(1).figures(0)
    var newsavedF17 = controllerH.game.players(1).figures(1)
    var newsavedF18 = controllerH.game.players(1).figures(2)
    var newsavedF19 = controllerH.game.players(1).figures(3)
    var newsavedF20 = controllerH.game.players(1).figures(4)
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        newsavedF11 = controllerH.game.players(2).figures(0)
        newsavedF12 = controllerH.game.players(2).figures(1)
        newsavedF13 = controllerH.game.players(2).figures(2)
        newsavedF14 = controllerH.game.players(2).figures(3)
        newsavedF15 = controllerH.game.players(2).figures(4)
      }
      case 4 => {
        newsavedF11 = controllerH.game.players(2).figures(0)
        newsavedF12 = controllerH.game.players(2).figures(1)
        newsavedF13 = controllerH.game.players(2).figures(2)
        newsavedF14 = controllerH.game.players(2).figures(3)
        newsavedF15 = controllerH.game.players(2).figures(4)
        newsavedF16 = controllerH.game.players(3).figures(0)
        newsavedF18 = controllerH.game.players(3).figures(1)
        newsavedF17 = controllerH.game.players(3).figures(2)
        newsavedF19 = controllerH.game.players(3).figures(3)
        newsavedF20 = controllerH.game.players(3).figures(4)
      }
    }
    //</editor-fold>


    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.gameStatus = savedSt
    controllerH.savedGame = savedSG
    controllerH.game = savedGM

    //Loading game figures
    //<editor-fold desc="Gamefigure Loads">

    controllerH.game.players(0).figures(0) = savedF1
    controllerH.game.players(0).figures(1) = savedF2
    controllerH.game.players(0).figures(2) = savedF3
    controllerH.game.players(0).figures(3) = savedF4
    controllerH.game.players(0).figures(4) = savedF5
    controllerH.game.players(1).figures(0) = savedF6
    controllerH.game.players(1).figures(1) = savedF7
    controllerH.game.players(1).figures(2) = savedF8
    controllerH.game.players(1).figures(3) = savedF9
    controllerH.game.players(1).figures(4) = savedF10
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        controllerH.game.players(2).figures(0) = savedF11
        controllerH.game.players(2).figures(1) = savedF12
        controllerH.game.players(2).figures(2) = savedF13
        controllerH.game.players(2).figures(3) = savedF14
        controllerH.game.players(2).figures(4) = savedF15
      }
      case 4 => {
        controllerH.game.players(2).figures(0) = savedF11
        controllerH.game.players(2).figures(1) = savedF12
        controllerH.game.players(2).figures(2) = savedF13
        controllerH.game.players(2).figures(3) = savedF14
        controllerH.game.players(2).figures(4) = savedF15
        controllerH.game.players(3).figures(0) = savedF16
        controllerH.game.players(3).figures(1) = savedF17
        controllerH.game.players(3).figures(2) = savedF18
        controllerH.game.players(3).figures(3) = savedF19
        controllerH.game.players(3).figures(4) = savedF20
      }
    }
    //</editor-fold>

    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
    savedSG = newsavedSG
    savedGM = newsavedGM

    //Saving game figures
    //<editor-fold desc="Gamefigure Saves">

    savedF1 = newsavedF1
    savedF2 = newsavedF2
    savedF3 = newsavedF3
    savedF4 = newsavedF4
    savedF5 = newsavedF5
    savedF6 = newsavedF6
    savedF7 = newsavedF7
    savedF8 = newsavedF8
    savedF9 = newsavedF9
    savedF10 = newsavedF10
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        savedF11 = newsavedF11
        savedF12 = newsavedF12
        savedF13 = newsavedF13
        savedF14 = newsavedF14
        savedF15 = newsavedF15
      }
      case 4 => {
        savedF11 = newsavedF11
        savedF12 = newsavedF12
        savedF13 = newsavedF13
        savedF14 = newsavedF14
        savedF15 = newsavedF15
        savedF16 = newsavedF16
        savedF18 = newsavedF17
        savedF17 = newsavedF18
        savedF19 = newsavedF19
        savedF20 = newsavedF20
      }
    }
    //</editor-fold>

  }

  override def redoStep: Unit = {
    val newsavedG = controllerH.gameboard
    val newsavedMC = controllerH.moveCounter
    val newsavedSt = controllerH.gameStatus
    val newsavedSG = controllerH.savedGame
    val newsavedGM = controllerH.game

    //Saving game figures
    //<editor-fold desc="Gamefigure Saves">

    var newsavedF1 = controllerH.game.players(0).figures(0)
    var newsavedF2 = controllerH.game.players(0).figures(1)
    var newsavedF3 = controllerH.game.players(0).figures(2)
    var newsavedF4 = controllerH.game.players(0).figures(3)
    var newsavedF5 = controllerH.game.players(0).figures(4)
    var newsavedF6 = controllerH.game.players(1).figures(0)
    var newsavedF7 = controllerH.game.players(1).figures(1)
    var newsavedF8 = controllerH.game.players(1).figures(2)
    var newsavedF9 = controllerH.game.players(1).figures(3)
    var newsavedF10 = controllerH.game.players(1).figures(4)
    var newsavedF11 = controllerH.game.players(0).figures(0)
    var newsavedF12 = controllerH.game.players(0).figures(1)
    var newsavedF13 = controllerH.game.players(0).figures(2)
    var newsavedF14 = controllerH.game.players(0).figures(3)
    var newsavedF15 = controllerH.game.players(0).figures(4)
    var newsavedF16 = controllerH.game.players(1).figures(0)
    var newsavedF17 = controllerH.game.players(1).figures(1)
    var newsavedF18 = controllerH.game.players(1).figures(2)
    var newsavedF19 = controllerH.game.players(1).figures(3)
    var newsavedF20 = controllerH.game.players(1).figures(4)
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        newsavedF11 = controllerH.game.players(2).figures(0)
        newsavedF12 = controllerH.game.players(2).figures(1)
        newsavedF13 = controllerH.game.players(2).figures(2)
        newsavedF14 = controllerH.game.players(2).figures(3)
        newsavedF15 = controllerH.game.players(2).figures(4)
      }
      case 4 => {
        newsavedF11 = controllerH.game.players(2).figures(0)
        newsavedF12 = controllerH.game.players(2).figures(1)
        newsavedF13 = controllerH.game.players(2).figures(2)
        newsavedF14 = controllerH.game.players(2).figures(3)
        newsavedF15 = controllerH.game.players(2).figures(4)
        newsavedF16 = controllerH.game.players(3).figures(0)
        newsavedF18 = controllerH.game.players(3).figures(1)
        newsavedF17 = controllerH.game.players(3).figures(2)
        newsavedF19 = controllerH.game.players(3).figures(3)
        newsavedF20 = controllerH.game.players(3).figures(4)
      }
    }
    //</editor-fold>


    controllerH.gameboard = savedG
    controllerH.moveCounter = savedMC
    controllerH.gameStatus = savedSt
    controllerH.savedGame = savedSG
    controllerH.game = savedGM

    //Loading game figures
    //<editor-fold desc="Gamefigure Loads">

    controllerH.game.players(0).figures(0) = savedF1
    controllerH.game.players(0).figures(1) = savedF2
    controllerH.game.players(0).figures(2) = savedF3
    controllerH.game.players(0).figures(3) = savedF4
    controllerH.game.players(0).figures(4) = savedF5
    controllerH.game.players(1).figures(0) = savedF6
    controllerH.game.players(1).figures(1) = savedF7
    controllerH.game.players(1).figures(2) = savedF8
    controllerH.game.players(1).figures(3) = savedF9
    controllerH.game.players(1).figures(4) = savedF10
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        controllerH.game.players(2).figures(0) = savedF11
        controllerH.game.players(2).figures(1) = savedF12
        controllerH.game.players(2).figures(2) = savedF13
        controllerH.game.players(2).figures(3) = savedF14
        controllerH.game.players(2).figures(4) = savedF15
      }
      case 4 => {
        controllerH.game.players(2).figures(0) = savedF11
        controllerH.game.players(2).figures(1) = savedF12
        controllerH.game.players(2).figures(2) = savedF13
        controllerH.game.players(2).figures(3) = savedF14
        controllerH.game.players(2).figures(4) = savedF15
        controllerH.game.players(3).figures(0) = savedF16
        controllerH.game.players(3).figures(1) = savedF17
        controllerH.game.players(3).figures(2) = savedF18
        controllerH.game.players(3).figures(3) = savedF19
        controllerH.game.players(3).figures(4) = savedF20
      }
    }
    //</editor-fold>

    savedG = newsavedG
    savedMC = newsavedMC
    savedSt = newsavedSt
    savedSG = newsavedSG
    savedGM = newsavedGM

    //Saving game figures
    //<editor-fold desc="Gamefigure Saves">

    savedF1 = newsavedF1
    savedF2 = newsavedF2
    savedF3 = newsavedF3
    savedF4 = newsavedF4
    savedF5 = newsavedF5
    savedF6 = newsavedF6
    savedF7 = newsavedF7
    savedF8 = newsavedF8
    savedF9 = newsavedF9
    savedF10 = newsavedF10
    controllerH.game.players.length match {
      case 2 => {}
      case 3 => {
        savedF11 = newsavedF11
        savedF12 = newsavedF12
        savedF13 = newsavedF13
        savedF14 = newsavedF14
        savedF15 = newsavedF15
      }
      case 4 => {
        savedF11 = newsavedF11
        savedF12 = newsavedF12
        savedF13 = newsavedF13
        savedF14 = newsavedF14
        savedF15 = newsavedF15
        savedF16 = newsavedF16
        savedF18 = newsavedF17
        savedF17 = newsavedF18
        savedF19 = newsavedF19
        savedF20 = newsavedF20
      }
    }
    //</editor-fold>
  }
}