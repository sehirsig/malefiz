package de.htwg.se.malefiz.model

import org.scalatest.{Matchers, WordSpec}

case class DiceSpec() extends WordSpec with Matchers {
  "A Dice" when {
    "used" should {
      val d = Dice()
      "should have value between 1 - 6" in {
        d.roll.isInstanceOf[Double]
        d.roll should (be(1) or be(2) or be(3) or be(4) or be(5) or be(6))
      }
    }
  }
}
