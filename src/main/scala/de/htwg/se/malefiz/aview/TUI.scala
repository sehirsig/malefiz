package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.{Controller, GameStatus}
import de.htwg.se.malefiz.util.Observer

import scala.io.StdIn.readLine

case class TUI(controller: Controller) extends Observer {
  controller.add(this)

//  def initiateGame(): Unit = { //State
//    println(
//      """Welcome to Malefiz!
//        |Please Enter the number of Players (2-4):
//        |""".stripMargin)
//    val pnumber = readLine()
//    controller.setPlayerNumber(pnumber.toInt)
//    if (pnumber == "2" || pnumber == "3" || pnumber == "4") {
//      for (i <- 1 to pnumber.toInt) {
//        println("Please Enter Name for Player " + i)
//        val playername = readLine() // TODO Readlines rauswerfen, alles in processing
//        controller.createPlayer(playername, i)
//      }
//    } else {
//      for (i <- 1 to 2) {
//        println("Please Enter Name for Player " + i)
//        val playername = readLine()
//        controller.createPlayer(playername, i)
//      }
//    }
//    println("Type >anything< to start the game!")
//  }

  def processing(input: String): Unit = { // Bei Player boolean, has Figur Draussen oder Int, alles über 0
    val figurDraussen = true

    input match {
      case "welcomeMessage" => println("Welcome to Malefiz!")
      case "p" => controller.addPlayer()
      case "start" => controller.startGame()
      case "r" => controller.rollDice()
      case "w" => controller.moveUp()
      case "a" => controller.moveLeft()
      case "s" => controller.moveDown()
      case "d" => controller.moveRight()
      case _ => println("invalid input")
    }
  }

  override def update: Unit =  {
    if (controller.gameStatus == GameStatus.PLAYING || controller.gameStatus == GameStatus.TURN) println(controller.boardToString)
    println(GameStatus.message(controller.gameStatus))
  }
}
