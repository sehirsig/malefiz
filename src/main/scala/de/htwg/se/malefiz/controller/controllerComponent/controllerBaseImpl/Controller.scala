package de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.controller.controllerComponent.GameStatus._
import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._
import de.htwg.se.malefiz.util.UndoManager
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector

import scala.swing.Publisher

/** Base implementation of the controller of the Malefiz game.
 *
 *  @author sehirsig & franzgajewski
 */
case class Controller @Inject() (var gameboard: GameboardInterface) extends ControllerInterface with Publisher {

  /** Injector for the dependency injection. */
  val injector = Guice.createInjector(new MalefizModule)

  /** Dependency injection for fileIO. */
  val fileIo = injector.instance[FileIOInterface]

  /** Initialization of the game state. */
  override var gameStatus: GameStatus = WELCOME

  /** Initialization of the player state (to determine the next player). */
  override var playerStatus: PlayerState = PlayerState1

  /** Indicates remaining moves. */
  override var moveCounter: Int = 0

  /** Builder pattern for players. */
  override val builder: PlayerBuilder = new PlayerBuilderImp()

  /** Saving of players and figures. */
  override var game: Game = Game(Vector[Player]())

  /** Initialization of the UndoManager */
  private val undoManager = new UndoManager

  /** Boolean indicates, whether there is a winner and who. */
  override var gameWon: (Boolean, String) = (false, "")

  /** Dependency injection of the state of the last move. */
  override var savedGame = injector.instance[lastSaveInterface]

  override var selectedFigNum: Int = 0;

  publish(new StartUp)

  /** Game reset for restart after a game was won. */
  override def resetGame(): Unit = {
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

  /** Selection of game figure. */
  override def selectFigure(x: Int): Unit = {
    selectedFigNum = x
    gameStatus = MOVING
    publish(new Moving)
  }

  /** Retrieve cell from string descriptor. */
  override def getpureCell(name: String): Cell = {
    gameboard.getCell(name)
  }

  /** Add a player. */
  override def addPlayer(): Unit = {
    gameWon = (false, "")
    gameStatus = ENTERNAME
    publish(new SettingUp)
  }

  /** Add player name and initialize 5 game figures. */
  override def addPlayerName(name: String): Unit = {
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

  /** Start game. */
  override def startGame(): Unit = {
    gameStatus = PLAYING
    publish(new StartGame)
  }

  /** Setup game at start of the application. */
  override def setupGame(): Unit = {
    publish(new SettingUp)
  }

  /** String representation of the game board. */
  override def boardToString(): String = gameboard.toString()

  /** Die roll. */
  override def rollDice(): Int = {
    moveCounter = gameboard.diceRoll
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    moveCounter
  }

  /** Check for a winner. */
  override def checkWin(): Unit = {
    if (gameboard.checkPlayerOnGoal) {
      gameStatus = GAMEWINNER
      val winner = gameboard.cell(gameboard.getGoalBase._1, gameboard.getGoalBase._2).toString().replace(" ", "").toInt - 1
      gameWon = (true, game.players(winner).name.replace("Some", ""))
      publish(new WonGame)
    }
  }

  /** Choose block strategy. This determines what happens to blocks, after they are captured. */
  override def setBlockStrategy(blockStrategy: String): Unit = {
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(blockStrategy)
      case "remove" => gameboard.setBlockStrategy(blockStrategy)
    }
  }

  /** Move. */
  override def move(input: String, figurenum: Int): Unit = {
    input match {
      case "skip" => moveCounter = 1; undoManager.doStep(new MoveCommand(input, figurenum, this))
      case "undo" => undo
      case "redo" => redo
      case _ => if (input != savedGame.lastDirectionOpposite) undoManager.doStep(new MoveCommand(input, figurenum, this));
    }
    if (moveCounter == 0) {
      /** Checks if the goal cell is occupied. */
      checkWin()
      /** Continues if game has not been won. */
      if (!gameWon._1) {
        gameStatus = PLAYING
        publish(new RollDice)
      }
    } else {
      publish(new Moving)
    }
  }

  /** Empties undo stack to reset the undo manager for the next player. */
  override def emptyMan: Unit = {
    undoManager.emptyStacks
  }

  /** Executes all undo steps on the undo stack. */
  override def undoAll: Unit = {
    undoManager.undoAll
  }

  /** Undo. */
  override def undo: Unit = {
    undoManager.undoStep
  }

  /** Redo. */
  override def redo: Unit = {
    undoManager.redoStep
  }

  /** Save game state to file. */
  override def save: Unit = {
    fileIo.save(gameboard)
    gameStatus = SAVED
    publish(new RollDice)
  }

  /** Load game state from file. */
  override def load: Unit = {
    /** Loaded file gets split to relevant variables, so that game figures can be handled correctly. */
    val temp = fileIo.load(game)
    gameboard = temp._1
    game = temp._2
    gameStatus = LOADED
    publish(new RollDice)
  }




  /** Debug for testing purposes. Here the starting position is set directly in front of the goal.
   * With a move counter of 1 the game can be won immediately.
   *
   * @param name name of the debug player
   */
  override def addPlayerDEBUGWINTEST(name: String): Unit = {
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

  /** Debug for testing purposes. This will always roll a 1 so that the game can be won immediately. */
  override def debugDice(): Unit = {
    moveCounter = 1
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
  }
}