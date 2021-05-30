package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.util.{Observable, UndoManager}

case class Controller(var gameboard: Gameboard) extends Observable{
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerState = PlayerState1
  var moveCounter: Int = 0
  val builder: PlayerBuilder = new PlayerBuilderImp()

  private val undoManager = new UndoManager

  var savedGame:lastSave = lastSave(0, "", InvalidCell)

  var game: Game = Game(Vector[Player]())

  def addPlayer(): Unit = {
    gameStatus = ENTERNAME
    notifyObservers
  }
  def addPlayerName(name: String): Unit = {
    builder.setName(name)
    gameStatus = ENTERCOLOR
    notifyObservers
  }
  def addPlayerColor(color: Int): Unit = {
    builder.setID(color)
    color match {
      case 1 => builder.setStartingPos(15,3)
      case 2 => builder.setStartingPos(15,7)
      case 3 => builder.setStartingPos(15,11)
      case 4 => builder.setStartingPos(15,15)
    }
    val player = builder.build()
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
    notifyObservers
  }

  def startGame(): Unit = {
    gameStatus = PLAYING
    notifyObservers
  }

  def boardToString(): String = gameboard.toString()

  def rollDice(): Int = {
    moveCounter = Dice.diceRoll
    gameStatus = MOVING
    notifyObservers
    println("You have rolled a: " + moveCounter)
    savedGame = savedGame.updateLastFullDice(moveCounter)
    moveCounter
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
    notifyObservers
  }

  def emptyMan: Unit = {
    undoManager.emptyStacks
    notifyObservers
  }

  def undoAll: Unit = {
    undoManager.undoAll
    notifyObservers
  }
/*
  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }*/

}