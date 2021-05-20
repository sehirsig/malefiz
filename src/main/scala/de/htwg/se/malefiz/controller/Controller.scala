package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.Observable

case class Controller(var gameboard: Gameboard) extends Observable{
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerState = PlayerState1
  var moveCounter: Int = 0
  val builder: PlayerBuilder = new PlayerBuilderImp()

//  val set: Settings = Settings()
//  val gameboard = new Gameboard(set.xDim, set.yDim)
  val emptyV = Vector[Player]()
  var game: Game = Game(emptyV)

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
      case 1 => builder.setStartingPos(14,3)
      case 2 => builder.setStartingPos(14,7)
      case 3 => builder.setStartingPos(14,11)
      case 4 => builder.setStartingPos(14,15)
    }
    val player = builder.build()
    game = game.addPlayer(player)
    if(game.getPlayers() > 3) {
      gameStatus = READY2
    }
    else if (game.getPlayers() < 2) {
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
    moveCounter
  }

  val replaceCell = Cell("RR")
  def move(input: String): Unit = {
    input match {
      case "w" => gameboard = gameboard.replaceCell(1,1,replaceCell) // TODO find coordinates, replace cell with current player
      case "a" => gameboard = gameboard.replaceCell(2,2,replaceCell)
      case "s" => gameboard = gameboard.replaceCell(3,3,replaceCell)
      case "d" => gameboard = gameboard.replaceCell(4,4,replaceCell)
    }
    if(moveCounter == 1) {
      gameStatus = PLAYING
      playerStatus = playerStatus.nextPlayer(game.getPlayers())
    }
    println(moveCounter)
    moveCounter -= 1
    notifyObservers
  }

//  def moveUp(): Unit = {
//    println("moves up")
//    if(moveCounter == 1) gameStatus = PLAYING
//    moveCounter -= 1
//    notifyObservers
//    println(moveCounter)
//  }
//  def moveDown(): Unit = {
//    println("moves down")
//    if(moveCounter == 1) gameStatus = PLAYING
//    moveCounter -= 1
//    notifyObservers
//    println(moveCounter)
//  }
//  def moveLeft(): Unit = {
//    println("moves left")
//    if(moveCounter == 1) gameStatus = PLAYING
//    moveCounter -= 1
//    notifyObservers
//    println(moveCounter)
//  }
//  def moveRight(): Unit = {
//    println("moves right")
//    if(moveCounter == 1) gameStatus = PLAYING
//    moveCounter -= 1
//    notifyObservers
//    println(moveCounter)
//  }

  //  var player1 = Player("",1) //TODO Vars wegbekommen, Heimat für Player suchen
  //  var player2 = Player("",2)
  //  var player3 = Player("",3)
  //  var player4 = Player("",4)
  //  var playerNumber = 2;
  //  var currentPlayer = 1;
  // GameFigures for each player ? Gamefigure Array / -> 4 new vars, oder Game Figures im Spieler

  //  def setPlayerNumber(number:Int): Unit = {
  //      playerNumber = number
  //  }
  //
  //  def createPlayer(name:String, nID:Int): Unit = {
  //      nID match {
  //        case 1 => player1 = Player(name, nID)
  //        case 2 => player2 = Player(name, nID)
  //        case 3 => player3 = Player(name, nID)
  //        case 4 => player4 = Player(name, nID)
  //      }
  //  }

  //  def nextPlayer(): Unit = {
  //    if (currentPlayer < playerNumber) {
  //      currentPlayer = currentPlayer + 1
  //    } else {
  //      currentPlayer = 1
  //    }
  //  }
}