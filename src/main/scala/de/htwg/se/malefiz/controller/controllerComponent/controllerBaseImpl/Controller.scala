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

/** Der Controller unseres Malefiz Spiel in der BaseImplementation.
 *
 *  @author sehirsig & franzgajewski
 */
case class Controller @Inject() (var gameboard: GameboardInterface) extends ControllerInterface with Publisher {

  /** Injector für Dependency Injection */
  val injector = Guice.createInjector(new MalefizModule)

  /** Dependency Injection für FileIO */
  val fileIo = injector.instance[FileIOInterface]

  /** Initialiersung des Spiel Status */
  override var gameStatus: GameStatus = WELCOME

  /** Initialiersung des Spieler-Status (Nächste-Spieler bestimmung) */
  override var playerStatus: PlayerState = PlayerState1

  /** Gibt die verbleibenden Spielfigur-Züge an */
  override var moveCounter: Int = 0

  /** Builder-Pattern für unsere Spieler */
  override val builder: PlayerBuilder = new PlayerBuilderImp()

  /** Speicherung unserer Spieler und Spielfiguren */
  override var game: Game = Game(Vector[Player]())

  /** Initialiersung unseres UndoManagers */
  private val undoManager = new UndoManager

  /** Boolean, ob es einen Gewinner gibt, und wenn, wer. */
  override var gameWon: (Boolean, String) = (false, "")

  /** Dependency Injection der Speicherung des letzten Zuges. */
  override var savedGame = injector.instance[lastSaveInterface]

  override var selectedFigNum: Int = 0;

  publish(new StartUp)

  /** Komplett Reset des Spiels, für nach dem Spiel-Gewinn. */
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

  /** Auswahl der Spielfigur. */
  override def selectFigure(x: Int): Unit = {
    selectedFigNum = x
    gameStatus = MOVING
    publish(new Moving)
  }

  /** Bekomme die Cell aus dem String-Namen. */
  override def getpureCell(name: String): Cell = {
    gameboard.getCell(name)
  }

  /** Füge einen Spieler hinzu. */
  override def addPlayer(): Unit = {
    gameWon = (false, "")
    gameStatus = ENTERNAME
    publish(new SettingUp)
  }

  /** Füge den Namen dem Spieler hinzu und die 5 Spielfiguren. */
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

  /** Spiel starten. */
  override def startGame(): Unit = {
    gameStatus = PLAYING
    publish(new StartGame)
  }

  /** Das Spiel einrichten, bei start der Applikation. */
  override def setupGame(): Unit = {
    publish(new SettingUp)
  }

  /** Stringdarstellung des Spielbrettes. */
  override def boardToString(): String = gameboard.toString()

  /** Würfeln. */
  override def rollDice(): Int = {
    moveCounter = gameboard.diceRoll
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
    moveCounter
  }

  /** Überprüfen, ob es einen Gewinner gibt. */
  override def checkWin(): Unit = {
    if (gameboard.checkPlayerOnGoal) {
      gameStatus = GAMEWINNER
      val winner = gameboard.cell(gameboard.getGoalBase._1, gameboard.getGoalBase._2).toString().replace(" ", "").toInt - 1
      gameWon = (true, game.players(winner).name.replace("Some", ""))
      publish(new WonGame)
    }
  }

  /** Die BlockStrategy auswählen. */
  override def setBlockStrategy(blockStrategy: String): Unit = {
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(blockStrategy)
      case "remove" => gameboard.setBlockStrategy(blockStrategy)
    }
  }

  /** Spielzug. */
  override def move(input: String, figurenum: Int): Unit = {
    input match {
      case "skip" => moveCounter = 1; undoManager.doStep(new MoveCommand(input, figurenum, this))
      case "undo" => undo
      case "redo" => redo
      case _ => if (input != savedGame.lastDirectionOpposite) undoManager.doStep(new MoveCommand(input, figurenum, this));
    }
    if (moveCounter == 0) {
      /** Überprüfen, ob jemand auf der GoalCell steht, wenn Ja, geht es in CheckWin() weiter. */
      checkWin()
      /** Wenn NEIN, dann normal weiter */
      if (!gameWon._1) {
        gameStatus = PLAYING
        publish(new RollDice)
      }
    } else {
      publish(new Moving)
    }
  }

  /** Leerung des Undomanagers, um einen Leeren Stack für den nächsten Spieler zu haben. */
  override def emptyMan: Unit = {
    undoManager.emptyStacks
  }

  /** Alle Undos auf dem Stack auf einmal ausführen. */
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

  /** Spielstand in Datei speichern. */
  override def save: Unit = {
    fileIo.save(gameboard)
    gameStatus = SAVED
    publish(new RollDice)
  }

  /** Spielstand aus Datei laden. */
  override def load: Unit = {
    /** "Game" Übergabe, damit die Spielfiguren richtig verwaltet werden können. */
    val temp = fileIo.load(game)
    gameboard = temp._1
    game = temp._2
    gameStatus = LOADED
    publish(new RollDice)
  }




  /**Debug Für Tests. Hier werden die Startposition, eins vor dem Ziel platziert.
   * Mit MoveCounter auf 1 kann somit sofort gewonnen werden.
   *
   * @param name Name des Debug Spielers
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

  /** Debug für Tests, hiermit wird immer eine 1 gewürfelt, somit kann man direkt ins Ziel. */
  override def debugDice(): Unit = {
    moveCounter = 1
    gameStatus = CHOOSEFIG
    savedGame = savedGame.updateLastFullDice(moveCounter)
    publish(new ChooseFig)
  }
}
