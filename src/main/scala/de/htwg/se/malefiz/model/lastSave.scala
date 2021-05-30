package de.htwg.se.malefiz.model

case class lastSave(lastFullDice:Int, lastDirectionOpposite:String, lastCell:Cell) {
  def updateLastFullDice(newNum:Int):lastSave = copy(newNum, lastDirectionOpposite, lastCell)
  def updateLastDirection(newDic:String):lastSave = copy(lastFullDice,newDic, lastCell)
  def updatelastCell(newCel:Cell):lastSave = copy(lastFullDice, lastDirectionOpposite, newCel)
}
