package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere Settings Klasse der Base-Implementierung.
 *
 *  @author sehirsig & franzgajewski
 */
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


