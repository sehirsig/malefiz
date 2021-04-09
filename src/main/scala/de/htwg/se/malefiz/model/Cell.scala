package de.htwg.se.malefiz.model

case class Cell(cellStatus: String = "  ", secureStatus: Boolean = false) {

  val cellStatusValidation = cellStatus match {
      case "  " => "valid"
      case "O " => "valid"
      case "X " => "valid"
      case "G " => "valid"
      case _ => "not valid"
  }

  override def toString(): String = cellStatus

}
