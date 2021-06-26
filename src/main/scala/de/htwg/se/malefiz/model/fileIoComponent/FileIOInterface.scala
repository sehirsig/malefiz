package de.htwg.se.malefiz.model.fileIoComponent

import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

/** Interface for the file IO implementation.
 *
 *  @author sehirsig & franzgajewski
 */
trait FileIOInterface {

  /** Loads game board.
   *
   *  @param game information and positions of the players and figures
   *  @return a tupel with the new game board and figure positions
   */
  def load(game:Game): (GameboardInterface,Game)

  /** Saves game board.
   *
   *  @param gameboard the game board to be stored
   */
  def save(gameboard: GameboardInterface): Unit
}
