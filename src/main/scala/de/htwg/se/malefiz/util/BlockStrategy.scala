package de.htwg.se.malefiz.util

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

/** Interface unseres BlockStrategy-Patterns.
 *
 *  @author sehirsig & franzgajewski
 */
trait BlockStrategy {

  /** Ersetzen der Blockade
   *
   *  @param spielbrett das alte Spielbrett
   *  @return das neue Spielbrett
   */
  def replaceBlock(spielbrett:GameboardInterface): GameboardInterface
}
