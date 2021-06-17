package de.htwg.se.malefiz.model.gameboardComponent.gameboardStubImpl

import de.htwg.se.malefiz.model.cellComponent.InvalidCell
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class lastSaveSpec extends AnyWordSpec with Matchers {
  "A Mock lastSave is the last move save from this Game" when { //
    val lasv = new lastSave(0,"", InvalidCell)
    "all functions used properly" should {
      "do nothing" in {
        lasv.updatelastCell(InvalidCell)
        lasv.updateLastDirection("")
        lasv.updateLastFullDice(0)
      }
    }
  }
}
