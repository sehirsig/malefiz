package de.htwg.se.malefiz.util

import de.htwg.se.malefiz.model.checkCell._
import de.htwg.se.malefiz.model.{BlockRemoveStrategy, BlockReplaceStrategy, Gameboard, properties}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BlockStrategySpec  extends AnyWordSpec with Matchers {
  "A BlockStrategy " should {
    val settings = new properties.Settings
    val spielbrett = new Gameboard(settings.yDim, settings.xDim)
    "replace a Block with Replace Strategy" in {
      spielbrett.setBlockStrategy(BlockReplaceStrategy())
      val newspielbrett = replaceBlock(spielbrett)
      newspielbrett should not be (spielbrett)
    }
    "remove a Block with Remove Strategy" in {
      spielbrett.setBlockStrategy(BlockRemoveStrategy())
      val newspielbrett = replaceBlock(spielbrett)
      newspielbrett should be (spielbrett)
    }
  }
}
