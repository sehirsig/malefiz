package de.htwg.se.malefiz.model.cellComponent

/** Alle Zellen unseres Malefiz-Spiels, die es gibt.
 *
 *  @author sehirsig & franzgajewski
 */
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

object Start1Cell extends Cell {
  override val cellStatus = "T "
}

object Start2Cell extends Cell {
  override val cellStatus = "T "
}

object Start3Cell extends Cell {
  override val cellStatus = "T "
}

object Start4Cell extends Cell {
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

case class PlayerCell(num:Int) extends Cell { //Zelle für jeden einzelnen Spieler, indem der Parameter gesetzt wird.
  def getPlayer:Int = num
  override val cellStatus = num.toString + " "
}