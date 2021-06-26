package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test-Klasse für unsere moveTypes Klasse der Base-Implementierung.
 *
 *  @author sehirsig & franzgajewski
 */
class moveTypesSpec extends AnyWordSpec with Matchers {
  "moveTypes Functions" when {
    "operate" should {
      "change up" in {
        moveTypes.goUp(1,1) should be (0,1)
      }
      "change down" in {
        moveTypes.goDown(1,1) should be (2,1)
      }
      "change left" in {
        moveTypes.goLeft(1,1) should be (1,0)
      }
      "change right" in {
        moveTypes.goRight(1,1) should be (1,2)
      }
    }
  }
}
