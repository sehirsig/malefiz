package de.htwg.se.malefiz.model.fileIoComponent

import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

trait FileIOInterface {
  def load(game:Game): (GameboardInterface,Game)
  def save(gameboard: GameboardInterface): Unit
}
