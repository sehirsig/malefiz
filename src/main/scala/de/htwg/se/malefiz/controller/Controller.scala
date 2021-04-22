package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings
import de.htwg.se.malefiz.util.Observable

case class Controller() extends Observable{
  val set = Settings()
  val gameboard = new Gameboard(set.xDim, set.yDim)

  def boardToString(): String = gameboard.toString()
  def moveUp(): Unit = {
    println("moves up")
  }
  def moveDown(): Unit = {
    println("moves down")
  }
  def moveLeft(): Unit = {
    println("moves left")
  }
  def moveRight(): Unit = {
    println("moves right")
  }
}