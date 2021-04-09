package de.htwg.se.malefiz.model

case class Cell(cellStatus: String = "  ", secureStatus: Boolean = false) {

  val cellStatusValidation = cellStatus match {
      case "  " => 1
      case "O " => 2
      case "X " => 3
      case "G " => 4
      case _ => 0
  }

  override def toString(): String = {
    if (cellStatusValidation == 0) {
      "not a valid cell"
    }
    else {
      cellStatus
    }
  }

}
