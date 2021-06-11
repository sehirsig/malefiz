package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.Settings
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class SettingsSpec extends AnyWordSpec with Matchers {
  "The Settings should" when {
    "have the right dimension" should {
      val set = Settings()
      "be displayed as \"  \"" in {
        set.yDim should be(Settings().yDim)
        set.xDim should be(Settings().xDim)
      }
    }
    "be initialized right" should {
      val set = Settings()
      "with the right path" in {
        set.walkableCells.contains(Settings().freeCells)
      }
    }
  }
}


