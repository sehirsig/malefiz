package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.model.properties.Settings
import org.scalatest.{Matchers, WordSpec}

class SettingsSpec extends WordSpec with Matchers {
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


