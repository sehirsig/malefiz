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

  val injector = Guice.createInjector(new MalefizModule)
  val fileIo = injector.instance[FileIOInterface]

  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerState = PlayerState1
  var moveCounter: Int = 0
  val builder: PlayerBuilder = new PlayerBuilderImp()
  var game: Game = Game(Vector[Player]())
  private val undoManager = new UndoManager
  var gameWon: (Boolean, String) = (false, "")
  var savedGame = injector.instance[lastSaveInterface]
  var selectedFigNum: Int = 0;

  publish(new StartUp)
//docker test
  def resetGame(): Unit = {
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

  def selectFigure(x: Int): Unit = {
    selectedFigNum = x
    gameStatus = MOVING
    publish(new Moving)
    //notifyObservers
  }

  def getpureCell(name: String): Cell = {
    gameboard.getCell(name)
  }

  def addPlayer(): Unit = {
    gameWon = (false, "")
    gameStatus = ENTERNAME
    publish(new SettingUp)
    //notifyObservers
  }

  def addPlayerName(name: String): Unit = {
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
    //notifyObservers
  }

  def startGame(): Unit = {
    gameStatus = PLAYING
    publish(new StartGame)
    //notifyObservers
  }

  def setupGame(): Unit = {
    publish(new SettingUp)
  }

  def boardToString(): String = gameboard.toString()

  def rollDice(): Int = {
    moveCounter = gameboard.diceRoll
    gameStatus = CHOOSEFIG
    //notifyObservers
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    moveCounter
  }

  def checkWin(): Unit = {
    if (gameboard.checkPlayerOnGoal) {
      gameStatus = GAMEWINNER
     // val winne3r = gameboard.cell(1,9).toString().toInt - 1
      val winner = gameboard.cell(1,9).toString().replace(" ", "").toInt - 1
      gameWon = (true, game.players(winner).name.replace("Some", ""))
      publish(new WonGame)
    }
  }

  def setBlockStrategy(blockStrategy: String): Unit = {
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(blockStrategy)
      case "remove" => gameboard.setBlockStrategy(blockStrategy)
    }
  }

  def move(input: String, figurenum: Int): Unit = {
    input match {
      case "skip" => moveCounter = 1; undoManager.doStep(new MoveCommand(input, figurenum, this))
      case "undo" => undo
      case "redo" => redo
      case _ => if (input != savedGame.lastDirectionOpposite) undoManager.doStep(new MoveCommand(input, figurenum, this));
    }
    if (moveCounter == 0) {
      checkWin()
      if (!gameWon._1) {
        gameStatus = PLAYING
        publish(new RollDice)
      }
    } else {
      publish(new Moving)
    }
    //notifyObservers
  }

  def emptyMan: Unit = {
    undoManager.emptyStacks
  }

  def undoAll: Unit = {
    undoManager.undoAll
  }

  def undo: Unit = {
    undoManager.undoStep
  }

  def redo: Unit = {
    undoManager.redoStep
  }

  def save: Unit = {
    fileIo.save(gameboard)
    gameStatus = SAVED
    publish(new RollDice)
  }

  def load: Unit = {
    val temp = fileIo.load(game)
    gameboard = temp._1
    game = temp._2
    gameStatus = LOADED
    publish(new RollDice)
  }

}
