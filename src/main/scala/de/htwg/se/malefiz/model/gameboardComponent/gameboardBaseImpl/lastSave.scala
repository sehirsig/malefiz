package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.gameboardComponent.lastSaveInterface

case class lastSave(lastFullDice: Int, lastDirectionOpposite: String, lastCell: String) extends lastSaveInterface{
  def updateLastFullDice(newNum: Int): lastSave = copy(newNum, lastDirectionOpposite, lastCell)
  def updateLastDirection(newDic: String): lastSave = copy(lastFullDice, newDic, lastCell)
  def updatelastCell(newCel: String): lastSave = copy(lastFullDice, lastDirectionOpposite, newCel)
}
