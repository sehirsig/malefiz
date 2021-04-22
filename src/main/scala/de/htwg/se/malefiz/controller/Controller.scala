package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._

case class Controller() {
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