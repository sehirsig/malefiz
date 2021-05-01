package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.GameStatus._
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.Observable

case class Controller(var gb: Gameboard) extends Observable{
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerStatus = PLAYER0
  var moveCounter: Int = 0

  val set = Settings()
  val gameboard = new Gameboard(set.xDim, set.yDim)
  val game = Game()

  def addPlayer(): Unit = {
    game.addPlayer()
    if(game.getPlayers() > 1) gameStatus = READY
    notifyObservers
  }
  def startGame(): Unit = {
    gameStatus = PLAYING
    playerStatus = PLAYER1
    notifyObservers
//    println(playerStatus)
//    println(playerMessage(playerStatus))
  }

  def boardToString(): String = gameboard.toString()

  def rollDice(): Int = {
//    moveCounter = Dice().roll
    moveCounter = 2
    gameStatus = MOVING
    notifyObservers
    println("You have rolled a: " + moveCounter)
    moveCounter
  }

  def move(input: String): Unit = {
    input match {
      case "w" => println("moves up")
      case "a" => println("moves down")
      case "s" => println("moves left")
      case "d" => println("moves right")
    }
    if(moveCounter == 1) {
      gameStatus = PLAYING
//      nextPlayer()
    }
    println(moveCounter)
    moveCounter -= 1
    notifyObservers
  }

  def nextPlayer(): Unit = {
    playerStatus match {
      case PLAYER1 => gameStatus = PLAYER2
      case PLAYER2 => gameStatus = PLAYER3
      case PLAYER3 => gameStatus = PLAYER4
      case PLAYER4 => gameStatus = PLAYER1
      case _ => gameStatus = PLAYER1
    }
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