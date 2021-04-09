package de.htwg.se.malefiz.model

case class Cell() {
  private var cellStatus = "  "

  def setFree(): Unit = this.cellStatus = "O "
  def setBlocked(): Unit = this.cellStatus = "X "
  def setSecure(): Unit = this.cellStatus = "O "
  def setGoal(): Unit = this.cellStatus = "G "

  override def toString(): String = cellStatus

}
