package de.htwg.se.malefiz.model

trait Cell {
  val cellStatus:String
  def isWalkable: Boolean = cellStatus != "  " && cellStatus != "T "
  override def toString(): String = { cellStatus }
}

object FreeCell extends Cell {
  override val cellStatus = "O "
}

object BlockedCell extends Cell {
  override val cellStatus = "X "
}

object StartCell extends Cell {
  override val cellStatus = "T "
}

object SecureCell extends Cell {
  override val cellStatus = "O "
}

object GoalCell extends Cell {
  override val cellStatus = "G "
}

object InvalidCell extends Cell {
  override val cellStatus = "  "
}

case class PlayerCell(num:Int) extends Cell {
  def getPlayer:Int = num
  override val cellStatus = num.toString + " "
}