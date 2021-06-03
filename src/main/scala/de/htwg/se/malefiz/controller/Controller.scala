package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.util.{Observable, UndoManager}

import scala.swing.Publisher

case class Controller(var gameboard: Gameboard) extends Publisher{
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerState = PlayerState1
  var moveCounter: Int = 0
  val builder: PlayerBuilder = new PlayerBuilderImp()

  private val undoManager = new UndoManager

  var gameWon:Boolean = false

  var savedGame:lastSave = lastSave(0, "", InvalidCell)

  var selectedFigNum:Int = 0;

  def selectFigure(x:Int):Unit = {
    selectedFigNum = x
    gameStatus = MOVING
    publish(new SettingUp)
    //notifyObservers
  }

  var game: Game = Game(Vector[Player]())

  def addPlayer(): Unit = {
    gameWon = false
    gameStatus = ENTERNAME
    publish(new SettingUp)
    //notifyObservers
  }

  def addPlayerName(name: String): Unit = {
    builder.setName(name)
    val newplayernum = game.players.length + 1
    builder.setID(newplayernum)
    newplayernum match {
      case 1 => builder.setStartingPos(15,3)
      case 2 => builder.setStartingPos(15,7)
      case 3 => builder.setStartingPos(15,11)
      case 4 => builder.setStartingPos(15,15)
    }
    val player = builder.build()
    (1 to 5).map(x => player.addFigure(x))
    game = game.addPlayer(player)
    if(game.getPlayerNumber() > 3) {
      gameStatus = READY2
    }
    else if (game.getPlayerNumber() < 2) {
      gameStatus = IDLE
    }
    else {
      gameStatus = READY1
    }
    publish(new SettingUp)
    //notifyObservers
  }

  def startGame(): Unit = {
    gameStatus = PLAYING
    publish(new CellChanged)
    //notifyObservers
  }

  def boardToString(): String = gameboard.toString()

  def rollDice(): Int = {
    moveCounter = Dice.diceRoll
    println("You have rolled a: " + moveCounter)
    gameStatus = CHOOSEFIG
    publish(new SettingUp)
    //notifyObservers
    savedGame = savedGame.updateLastFullDice(moveCounter)
    moveCounter
  }

  def checkWin():Boolean = {
    if (gameboard.cell(1,9).isInstanceOf[PlayerCell]) {
      gameWon = true
      gameStatus = GAMEWINNER
      val winner = {
        if ((playerStatus.getCurrentPlayer - 1) == 0){
          game.players.length - 1
        } else {
          playerStatus.getCurrentPlayer - 2
        }
      }
      println("We Have a Winner: " + game.players(winner).name + "\n")
      println("Press p to add new Players for a new game!")
      game = Game(Vector[Player]())
      true
    } else {
      false
    }
  }

  def setBlockStrategy(blockStrategy: String): Unit = {
    blockStrategy match {
      case "replace" => gameboard.setBlockStrategy(BlockReplaceStrategy())
      case "remove" => gameboard.setBlockStrategy(BlockRemoveStrategy())
    }
  }

  def move(input: String, figurenum: Int): Unit = {
    input match {
      case "skip" => moveCounter = 1; undoManager.doStep(new MoveCommand(input, figurenum, this))
      case "undo" => undoManager.undoStep
      case "redo" => undoManager.redoStep
      case _ => if(input != savedGame.lastDirectionOpposite) undoManager.doStep(new MoveCommand(input, figurenum, this));
    }
    publish(new CellChanged)
    //notifyObservers
  }

  def emptyMan: Unit = {
    undoManager.emptyStacks
  }

  def undoAll: Unit = {
    undoManager.undoAll
  }

}