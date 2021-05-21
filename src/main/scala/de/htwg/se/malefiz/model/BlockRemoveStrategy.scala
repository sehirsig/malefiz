package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.util.BlockStrategy

case class BlockRemoveStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: Gameboard): Gameboard = spielbrett
}
