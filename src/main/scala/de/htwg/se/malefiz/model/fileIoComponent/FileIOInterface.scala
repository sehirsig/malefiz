package de.htwg.se.malefiz.model.fileIoComponent

import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

/** Das Interface für unsere FileIO Implementierungen.
 *
 *  @author sehirsig & franzgajewski
 */
trait FileIOInterface {

  /** Ladet das Spielbrett
   *
   *  @param game Informationen und Positionen der Spieler/Spielfiguren
   *  @return ein Tupel aus dem neuen Spielbrett und den Spieldaten (Figur-Positionen)
   */
  def load(game:Game): (GameboardInterface,Game)

  /** Speichert das Spielbrett
   *
   *  @param gameboard Das Spielbrett zum speichern.
   */
  def save(gameboard: GameboardInterface): Unit
}
