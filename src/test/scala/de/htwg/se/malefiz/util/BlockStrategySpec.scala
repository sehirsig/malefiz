package de.htwg.se.malefiz.util

import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.checkCell._
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard, Settings}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/** Test class for BlockStrategy class.
 *
 *  @author sehirsig & franzgajewski
 */
class BlockStrategySpec  extends AnyWordSpec with Matchers {
  "A BlockStrategy " should {
    val settings = new Settings
    val spielbrett = new Gameboard(settings.yDim, settings.xDim)
    "replace a Block with Replace Strategy" in {
      spielbrett.setBlockStrategy("replace")
      val newspielbrett = replaceBlock(spielbrett)
      newspielbrett should not be (spielbrett)
    }
    "remove a Block with Remove Strategy" in {
      spielbrett.setBlockStrategy("remove")
      val newspielbrett = replaceBlock(spielbrett)
      newspielbrett should be (spielbrett)
    }
  }
}