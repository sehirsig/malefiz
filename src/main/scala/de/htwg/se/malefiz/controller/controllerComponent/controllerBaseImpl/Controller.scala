/*
Class: controllerBaseImpl/Controller.scala

Beschreibung:
Der Controller unseres Malefiz Spiel in der BaseImplementation.

 */

package de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._
import de.htwg.se.malefiz.util.UndoManager
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

import scala.swing.Publisher

case class Controller @Inject() (var gameboard: GameboardInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new MalefizModule) //Injector für Dependency Injection
  val fileIo = injector.instance[FileIOInterface] //Dependency Injection für FileIO

  override var gameStatus: GameStatus = WELCOME //Initialiersung des Spiel Status
  override var playerStatus: PlayerState = PlayerState1 //Initialiersung des Spieler-Status (Nächste-Spieler bestimmung)
  override var moveCounter: Int = 0 // Gibt die verbleibenden Spielfigur-Züge an
  override val builder: PlayerBuilder = new PlayerBuilderImp() //Builder-Pattern für unsere Spieler
  override var game: Game = Game(Vector[Player]()) //Speicherung unserer Spieler und Spielfiguren
  private val undoManager = new UndoManager //Initialiersung unseres UndoManagers
  override var gameWon: (Boolean, String) = (false, "") //Boolean, ob es einen Gewinner gibt, und wenn, wer.
  override var savedGame = injector.instance[lastSaveInterface] //Dependency Injection der Speicherung des letzten Zuges.
  override var selectedFigNum: Int = 0;

  publish(new StartUp)

  override def resetGame(): Unit = { //Komplett Reset des Spiels, für nach dem Spiel-Gewinn.
    gameStatus = WELCOME
    game = new Game(Vector[Player]())
    emptyMan
    savedGame = injector.instance[lastSaveInterface]
    playerStatus = PlayerState1
    moveCounter = 0
    gameWon = (false, "")
    selectedFigNum = 0;
    this.gameboard = gameboard.newGBStandardSize
    publish(new GameReset)
  }

  override def selectFigure(x: Int): Unit = { //Auswahl der Spielfigur.
    selectedFigNum = x
    gameStatus = MOVING
    publish(new Moving)
  }

  override def getpureCell(name: String): Cell = { //Bekomme die Cell aus dem String-Namen.
    gameboard.getCell(name)
  }

  override def addPlayer(): Unit = { //Füge einen Spieler hinzu.
    gameWon = (false, "")
    gameStatus = ENTERNAME
    publish(new SettingUp)
  }

  override def addPlayerName(name: String): Unit = { //Füge den Namen dem Spieler hinzu und die 5 Spielfiguren.
    builder.setName(name)
    val newplayernum = game.players.length + 1
    builder.setID(newplayernum)
    newplayernum match {
      case 1 => builder.setStartingPos(gameboard.getP1Base._1, gameboard.getP1Base._2)
      case 2 => builder.setStartingPos(gameboard.getP2Base._1, gameboard.getP2Base._2)
      case 3 => builder.setStartingPos(gameboard.getP3Base._1, gameboard.getP3Base._2)
      case 4 => builder.setStartingPos(gameboard.getP4Base._1, gameboard.getP4Base._2)
    }
    val player = builder.build()
    (1 to 5).map(x => player.addFigure(x))
    game = game.addPlayer(player)
    if (game.getPlayerNumber() > 3) {
      gameStatus = READY2
    } else if (game.getPlayerNumber() < 2) {
      gameStatus = IDLE
    } else {
      gameStatus = READY1
    }
    publish(new StartUp)
  }

  override def startGame(): Unit = { //Spiel starten.
    gameStatus = PLAYING
    publish(new StartGame)
  }

  override def setupGame(): Unit = { //Das Spiel einrichten, bei start der Applikation.
    publish(new SettingUp)
  }

  override def boardToString(): String = gameboard.toString() //Stringdarstellung des Spielbrettes.

  override def rollDice(): Int = { //Würfeln.
    moveCounter = gameboard.diceRoll
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    moveCounter
  }

  override def checkWin(): Unit = { //Überprüfen, ob es einen Gewinner gibt.
    if (gameboard.checkPlayerOnGoal) {
      gameStatus = GAMEWINNER
      val winner = gameboard.cell(gameboard.getGoalBase._1, gameboard.getGoalBase._2).toString().replace(" ", "").toInt - 1
      gameWon = (true, game.players(winner).name.replace("Some", ""))
      publish(new WonGame)
    }
  }

  override def setBlockStrategy(blockStrategy: String): Unit = { //Die BlockStrategy auswählen.
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(blockStrategy)
      case "remove" => gameboard.setBlockStrategy(blockStrategy)
    }
  }

  override def move(input: String, figurenum: Int): Unit = { //Spielzug.
    input match {
      case "skip" => moveCounter = 1; undoManager.doStep(new MoveCommand(input, figurenum, this))
      case "undo" => undo
      case "redo" => redo
      case _ => if (input != savedGame.lastDirectionOpposite) undoManager.doStep(new MoveCommand(input, figurenum, this));
    }
    if (moveCounter == 0) {
      checkWin() // Überprüfen, ob jemand auf der GoalCell steht, wenn Ja, geht es in CheckWin() weiter.
      if (!gameWon._1) { // Wenn NEIN, dann normal weiter.
        gameStatus = PLAYING
        publish(new RollDice)
      }
    } else {
      publish(new Moving)
    }
  }

  override def emptyMan: Unit = { //Leerung des Undomanagers, um einen Leeren Stack für den nächsten Spieler zu haben.
    undoManager.emptyStacks
  }

  override def undoAll: Unit = { //Alle Undos auf dem Stack auf einmal ausführen.
    undoManager.undoAll
  }

  override def undo: Unit = { //Undo.
    undoManager.undoStep
  }

  override def redo: Unit = { //Redo.
    undoManager.redoStep
  }

  override def save: Unit = { //Spielstand in Datei speichern.
    fileIo.save(gameboard)
    gameStatus = SAVED
    publish(new RollDice)
  }

  override def load: Unit = { //Spielstand aus Datei laden.
    val temp = fileIo.load(game) //"Game" Übergabe, damit die Spielfiguren richtig verwaltet werden können.
    gameboard = temp._1
    game = temp._2
    gameStatus = LOADED
    publish(new RollDice)
  }


  // Debug Für Tests. Hier werden die Startposition, eins vor dem Ziel platziert.
  // Mit MoveCounter auf 1 kann somit sofort gewonnen werden.
  override def addPlayerDEBUGWINTEST(name: String): Unit = {//Füge den Namen dem Spieler hinzu und die 5 Spielfiguren.
    builder.setName(name)
    val newplayernum = game.players.length + 1
    builder.setID(newplayernum)
    newplayernum match {
      case 1 => builder.setStartingPos(gameboard.getGoalBase._1 + 1, gameboard.getGoalBase._2)
      case 2 => builder.setStartingPos(gameboard.getGoalBase._1 + 1, gameboard.getGoalBase._2)
      case 3 => builder.setStartingPos(gameboard.getGoalBase._1 + 1, gameboard.getGoalBase._2)
      case 4 => builder.setStartingPos(gameboard.getGoalBase._1 + 1, gameboard.getGoalBase._2)
    }
    val player = builder.build()
    (1 to 5).map(x => player.addFigure(x))
    game = game.addPlayer(player)
    if (game.getPlayerNumber() > 3) {
      gameStatus = READY2
    } else if (game.getPlayerNumber() < 2) {
      gameStatus = IDLE
    } else {
      gameStatus = READY1
    }
    publish(new StartUp)
  }

  //Debug für Tests, hiermit wird immer eine 1 gewürfelt, somit kann man direkt ins Ziel.
  override def debugDice(): Unit = {
    moveCounter = 1
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    1
  }
}
