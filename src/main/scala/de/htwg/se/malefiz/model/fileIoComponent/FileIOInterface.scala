/*
Class: FileIOInterface.scala

Beschreibung:
Das Interface für unsere FileIO Implementierungen.

 */

package de.htwg.se.malefiz.model.fileIoComponent

import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

trait FileIOInterface {
  def load(game:Game): (GameboardInterface,Game) //Extra-Parameter, um die Spielfigurpositionen zu aktualisieren.
  def save(gameboard: GameboardInterface): Unit
}
