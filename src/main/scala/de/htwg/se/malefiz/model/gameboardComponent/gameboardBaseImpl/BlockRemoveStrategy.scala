/*
Class: gameboardBaseImpl/BlockRemoveStrategy.scala

Beschreibung:
Blocked Cell Remove Strategy unseres Strategy-Patterns.
Lösche die Blocked Zellen, wenn man drauf landet.
 */

package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy

case class BlockRemoveStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: GameboardInterface): GameboardInterface = spielbrett
}
