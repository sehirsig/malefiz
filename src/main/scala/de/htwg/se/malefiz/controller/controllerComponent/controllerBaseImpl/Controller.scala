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

  var gameStatus: GameStatus = IDLE //Initialiersung des Spiel Status
  var playerStatus: PlayerState = PlayerState1 //Initialiersung des Spieler-Status (Nächste-Spieler bestimmung)
  var moveCounter: Int = 0 // Gibt die verbleibenden Spielfigur-Züge an
  val builder: PlayerBuilder = new PlayerBuilderImp() //Builder-Pattern für unsere Spieler
  var game: Game = Game(Vector[Player]()) //Speicherung unserer Spieler und Spielfiguren
  private val undoManager = new UndoManager //Initialiersung unseres UndoManagers
  var gameWon: (Boolean, String) = (false, "") //Boolean, ob es einen Gewinner gibt, und wenn, wer.
  var savedGame = injector.instance[lastSaveInterface] //Dependency Injection der Speicherung des letzten Zuges.
  var selectedFigNum: Int = 0;

  publish(new StartUp)

  def resetGame(): Unit = { //Komplett Reset des Spiels, für nach dem Spiel-Gewinn.
    gameStatus = IDLE
    game = Game(Vector[Player]())
    emptyMan
    savedGame = injector.instance[lastSaveInterface]
    playerStatus = PlayerState1
    moveCounter = 0
    gameWon = (false, "")
    selectedFigNum = 0;
    this.gameboard = gameboard.newGBStandardSize
    publish(new GameReset)
  }

  def selectFigure(x: Int): Unit = { //Auswahl der Spielfigur.
    selectedFigNum = x
    gameStatus = MOVING
    publish(new Moving)
  }

  def getpureCell(name: String): Cell = { //Bekomme die Cell aus dem String-Namen.
    gameboard.getCell(name)
  }

  def addPlayer(): Unit = { //Füge einen Spieler hinzu.
    gameWon = (false, "")
    gameStatus = ENTERNAME
    publish(new SettingUp)
  }

  def addPlayerName(name: String): Unit = { //Füge den Namen dem Spieler hinzu und die 5 Spielfiguren.
    builder.setName(name)
    val newplayernum = game.players.length + 1
    builder.setID(newplayernum)
    newplayernum match {
      case 1 => builder.setStartingPos(15, 3)
      case 2 => builder.setStartingPos(15, 7)
      case 3 => builder.setStartingPos(15, 11)
      case 4 => builder.setStartingPos(15, 15)
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

  def startGame(): Unit = { //Spiel starten.
    gameStatus = PLAYING
    publish(new StartGame)
  }

  def setupGame(): Unit = { //Das Spiel einrichten, bei start der Applikation.
    publish(new SettingUp)
  }

  def boardToString(): String = gameboard.toString() //Stringdarstellung des Spielbrettes.

  def rollDice(): Int = { //Würfeln.
    moveCounter = gameboard.diceRoll
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    moveCounter
  }

  def checkWin(): Unit = { //Überprüfen, ob es einen Gewinner gibt.
    if (gameboard.checkPlayerOnGoal) {
      gameStatus = GAMEWINNER
      val winner = gameboard.cell(1,9).toString().replace(" ", "").toInt - 1
      gameWon = (true, game.players(winner).name.replace("Some", ""))
      publish(new WonGame)
    }
  }

  def setBlockStrategy(blockStrategy: String): Unit = { //Die BlockStrategy auswählen.
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(blockStrategy)
      case "remove" => gameboard.setBlockStrategy(blockStrategy)
    }
  }

  def move(input: String, figurenum: Int): Unit = { //Spielzug.
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

  def emptyMan: Unit = { //Leerung des Undomanagers, um einen Leeren Stack für den nächsten Spieler zu haben.
    undoManager.emptyStacks
  }

  def undoAll: Unit = { //Alle Undos auf dem Stack auf einmal ausführen.
    undoManager.undoAll
  }

  def undo: Unit = { //Undo.
    undoManager.undoStep
  }

  def redo: Unit = { //Redo.
    undoManager.redoStep
  }

  def save: Unit = { //Spielstand in Datei speichern.
    fileIo.save(gameboard)
    gameStatus = SAVED
    publish(new RollDice)
  }

  def load: Unit = { //Spielstand aus Datei laden.
    val temp = fileIo.load(game) //"Game" Übergabe, damit die Spielfiguren richtig verwaltet werden können.
    gameboard = temp._1
    game = temp._2
    gameStatus = LOADED
    publish(new RollDice)
  }
}
