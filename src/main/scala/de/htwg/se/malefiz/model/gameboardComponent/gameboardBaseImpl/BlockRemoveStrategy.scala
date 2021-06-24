package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy

/** Blocked Cell Remove Strategy unseres Strategy-Patterns.
 *  Lösche die Blocked Zellen, wenn man drauf landet.
 *
 *  @author sehirsig & franzgajewski
 */
case class BlockRemoveStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: GameboardInterface): GameboardInterface = spielbrett
}
