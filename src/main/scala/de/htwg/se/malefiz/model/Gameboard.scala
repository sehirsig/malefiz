package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings


case class Gameboard() {
  private val settings = new Settings

  val gameboard = Array.ofDim[Cell](settings.yDim,settings.xDim)

  val yDim = settings.yDim

  val xDim = settings.xDim

  def cellToString(x:Int, y:Int): String = gameboard(x)(y).toString()

  def initiate():Unit = {

    for(i<-0 to (yDim - 1); j<- 0 to (xDim - 1)) {
      val cell = new Cell(" ")
      this.gameboard(i)(j) = cell
    }


    settings.freeCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2).setFree()}
    settings.blockedCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2).setBlocked()}
    settings.secureCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2).setSecure()}
    this.gameboard(settings.goalCell._1)(settings.goalCell._2).setGoal()

    println(gameboard(1)(1))
    println(gameboard(2)(2))
    println(gameboard(2)(9))
    println(gameboard(13)(1))
    println(gameboard(1)(9))
    //update()
  }

  def update():Unit = {
    this.gameboard.foreach { row => row.foreach(print) }
  }


}
