package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere Dice Klasse der Base-Implementierung.
 *
 *  @author sehirsig & franzgajewski
 */
case class DiceSpec() extends AnyWordSpec with Matchers {
  "A Dice" when {
    "used" should {
      "should have value between 1 - 6" in {
        Dice.diceRoll.isInstanceOf[Double]
        Dice.diceRoll should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
    }
  }
}
