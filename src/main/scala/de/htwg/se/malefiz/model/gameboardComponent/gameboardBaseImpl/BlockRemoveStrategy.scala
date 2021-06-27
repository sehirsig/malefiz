package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy

/** Blocked cell remove strategy of the strategy pattern.
 *  Permanently removes barricades from the game, when captured.
 *
 *  @author sehirsig & franzgajewski
 */
case class BlockRemoveStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: GameboardInterface): GameboardInterface = spielbrett
}