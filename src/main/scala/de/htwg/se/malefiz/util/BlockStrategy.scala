package de.htwg.se.malefiz.util

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

/** BlockStrategy pattern interface.
 *
 *  @author sehirsig & franzgajewski
 */
trait BlockStrategy {

  /** Replace block
   *
   *  @param spielbrett old game board
   *  @return new game board
   */
  def replaceBlock(spielbrett:GameboardInterface): GameboardInterface
}