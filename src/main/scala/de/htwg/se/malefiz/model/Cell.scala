package de.htwg.se.malefiz.model

case class Cell(cellStatus: String = "  ", secureStatus: Boolean = false) {

  val cellStatusValidation = cellStatus match {
      case "  " => 0
      case "O " => 0
      case "X " => 0
      case "G " => 0
      case _ => 1
  }

  override def toString(): String = {
    if (cellStatusValidation != 0) {
      "not a valid cell"
    }
    else {
      cellStatus
    }
  }

}
