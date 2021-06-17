package de.htwg.se.malefiz.model.gameboardComponent.gameboardStubImpl

import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.gameboardComponent.lastSaveInterface

case class lastSave(lastFullDice: Int, lastDirectionOpposite: String, lastCell: Cell) extends lastSaveInterface{
  def updateLastFullDice(newNum: Int): lastSave = this
  def updateLastDirection(newDic: String): lastSave = this
  def updatelastCell(newCel: Cell): lastSave = this
}
