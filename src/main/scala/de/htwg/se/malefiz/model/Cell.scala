package de.htwg.se.malefiz.model

case class Cell(cellStatus: String = "  ", secureStatus: Boolean = false) {
  def isWalkable: Boolean = cellStatus != "  "
  override def toString(): String = { cellStatus }
}
