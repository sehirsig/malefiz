package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.Observable

case class Controller(var gb: Gameboard) extends Observable{
  val set = Settings()
  val gameboard = new Gameboard(set.xDim, set.yDim)

  val players = Vector[Player]()// TODO vector mit spieler, potentielle heimat für player, addPlayer fügt neuen spieler ein, checkt auf duplikate, ID ist obsulet
  def addPlayer(input: String): Unit = {
    val player = Player(input,1)
    println(player)
  }

  var player1 = Player("",1) //TODO Vars wegbekommen, Heimat für Player suchen
  var player2 = Player("",2)
  var player3 = Player("",3)
  var player4 = Player("",4)
  var playerNumber = 2;
  var currentPlayer = 1;
  // GameFigures for each player ? Gamefigure Array / -> 4 new vars, oder Game Figures im Spieler

  def setPlayerNumber(number:Int): Unit = {
      playerNumber = number
  }

  def createPlayer(name:String, nID:Int): Unit = {
      nID match {
        case 1 => player1 = Player(name, nID)
        case 2 => player2 = Player(name, nID)
        case 3 => player3 = Player(name, nID)
        case 4 => player4 = Player(name, nID)
      }
  }

  def boardToString(): String = gameboard.toString()

  def nextPlayer(): Unit = {
    if (currentPlayer < playerNumber) {
      currentPlayer = currentPlayer + 1
    } else {
      currentPlayer = 1
    }
  }

  def rollDice(): Int = {
    notifyObservers
    Dice().roll
  }

  def moveUp(): Unit = {
  println("moves up")
  notifyObservers
  }
  def moveDown(): Unit = {
    println("moves down")
    notifyObservers
  }
  def moveLeft(): Unit = {
    println("moves left")
    notifyObservers
  }
  def moveRight(): Unit = {
    println("moves right")
    notifyObservers
  }


}