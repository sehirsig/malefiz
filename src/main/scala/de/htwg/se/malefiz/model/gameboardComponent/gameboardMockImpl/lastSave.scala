package de.htwg.se.malefiz.model.gameboardComponent.gameboardMockImpl

import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.gameboardComponent.lastSaveInterface

case class lastSave(lastFullDice: Int, lastDirectionOpposite: String, lastCell: Cell) extends lastSaveInterface{
  def updateLastFullDice(newNum: Int): lastSave = copy(newNum, lastDirectionOpposite, lastCell)
  def updateLastDirection(newDic: String): lastSave = copy(lastFullDice, newDic, lastCell)
  def updatelastCell(newCel: Cell): lastSave = copy(lastFullDice, lastDirectionOpposite, newCel)
}
