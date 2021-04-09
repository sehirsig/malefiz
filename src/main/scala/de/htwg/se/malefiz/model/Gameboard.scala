package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings


case class Gameboard() {
  private val settings = Settings()

  val gameboard = Array.ofDim[Cell](settings.yDim,settings.xDim)

  val yDim = settings.yDim

  val xDim = settings.xDim

  def cellToString(x:Int, y:Int): String = gameboard(x)(y).toString()

  //def initiating():Unit = { --> Auskommentiert -> Code Teil des Konstruktors (Wird einfach instanziiert

    for(i<-0 to (yDim - 1); j<- 0 to (xDim - 1)) {
      val cell = new Cell()
      this.gameboard(i)(j) = cell
    }


    settings.freeCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2) = new Cell("O ")}
    settings.blockedCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2) = new Cell("X ")}
    settings.secureCells.foreach { tuple => this.gameboard(tuple._1)(tuple._2) = new Cell("O ", true)}
    this.gameboard(settings.goalCell._1)(settings.goalCell._2) = new Cell("G ")
  //}

  def update():Unit = {
    this.gameboard.foreach { row => row.foreach(print); println() }
  }


}
