/*
Class: controllerBaseImpl/MoveCommand.scala

Beschreibung:
Hier wird jeder einzelne Zug verwaltet, mit dem Command-Pattern können Züge zurückgenommen oder mit "redo" wiedergespielt werden.

 */

package de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.util.Command

class MoveCommand(direction:String, figurenum:Int, controllerH: Controller) extends Command {
  //Speicherung unserer Variablen für diesen Step.
  var savedG = controllerH.gameboard //Spielbrett wird gespeichert.
  var savedMC = controllerH.moveCounter //Zähler der verbleibende Züge.
  var savedSt = controllerH.gameStatus //Spielstatus.
  var savedSG = controllerH.savedGame //Informationen des letzten Zuges.
  var savedGM = controllerH.game //"Game" speichert die Spieler.

  //Hier schliessbar, Speicherung der Spielfiguren.
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
    var sucInp:Boolean = false //Boolean, ob der Move geklappt hat und rechtens war.

    val currentplayer = controllerH.game.players(controllerH.playerStatus.getCurrentPlayer - 1) //Bekomme jetzigen Spieler.

    val currentfigure = currentplayer.figures(figurenum-1) //Wähle momentane Spielfigur aus.
    val fig_coord = (currentfigure.pos._1, currentfigure.pos._2) //Koordinaten der momentanen Figur.
    var lastCell:Cell = InvalidCell //Initialisierung der letzten Zelle (Wird im nächsten Schritt ersetzt)
    var savetuple = (sucInp, controllerH.gameboard) //Tupel für den Boolean und das neue Spielbrett
    var newpos = fig_coord //Neue Position der Spielfigur( Wird ersetzt)
    direction match {
      case "w" =>  {
        savetuple = savedG.walkUp(controllerH.gameboard, currentplayer, fig_coord, figurenum-1, controllerH.moveCounter)} // returns TRUE, if walking worked and Saves Gameboard
        lastCell = savedG.cell(savedG.goUp(fig_coord)._1, savedG.goUp(fig_coord)._2) // Saves LastCell to Replace it afterwards
        newpos = savedG.goUp(fig_coord) // Saves newposition to look for Player Kick
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

    if ((controllerH.moveCounter - 1) == 0) { // If 0, Kick Spieler
      controllerH.game.players.map(b => b.figures.map(k => {
        if (((k.pos._1, k.pos._2) == newpos) && (k.player != currentplayer)) { //Position der Spielfigur des Gekickten auf seine Basis zurücksetzen.
          k.player.figures(k.getNumber) = k.player.figures(k.getNumber).updatePos(k.player.startingPos)
          controllerH.gameboard = controllerH.gameboard.movePlayer(k.player.startingPos, k.player.cell)
        }
      }))
    }
    sucInp = savetuple._1 //Boolean wird hier gesetzt.
    controllerH.gameboard = savetuple._2 //Neues Spielbrett wird hier gesetzt.

    if(sucInp) {
      controllerH.moveCounter -= 1
      direction match { // Sperre die andere Richtung, damit man nicht einfach links - rechts / oben - unten laufen kann
        case "w" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("s")
        case "s" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("w")
        case "a" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("d")
        case "d" => controllerH.savedGame = controllerH.savedGame.updateLastDirection("a")
      }
      if (controllerH.savedGame.lastCell.isInstanceOf[PlayerCell]) { //Wenn man über eine Person drüber läuft, , diese wieder hinschreiben.
        controllerH.gameboard = controllerH.gameboard.movePlayer(fig_coord, controllerH.savedGame.lastCell)
      }
      controllerH.savedGame = controllerH.savedGame.updatelastCell(lastCell)
    } else {
      controllerH.undoAll //Wenn Laufen nicht geklappt, hat (in Illeagle Richtung) Kompletter zug zurücksetzen.
      direction match {
        case "skip" => controllerH.moveCounter = 0
        case _ =>
      }
    }
    if(controllerH.moveCounter < 1) { // Wenn dies der letzte Zug war, Zug des nächsten Spielers einleiten.
        controllerH.playerStatus = controllerH.playerStatus.nextPlayer(controllerH.game.getPlayerNumber())
        controllerH.emptyMan //Empty the Undomanager to be able to completetly reset it when in falsche richtung geloffen
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

    //Hier schliessbar, Speicherung der Spielfiguren.
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

    //Hier schliessbar, Ladungen der Spielfiguren.
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

    //Hier schliessbar, Speicherung der Spielfiguren.
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

    //Hier schliessbar, Speicherung der Spielfiguren.
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

    //Hier schliessbar, Ladungen der Spielfiguren.
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

    //Hier schliessbar, Speicherung der Spielfiguren.
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
