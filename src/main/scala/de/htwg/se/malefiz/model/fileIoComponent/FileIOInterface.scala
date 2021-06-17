package de.htwg.se.malefiz.model.fileIoComponent

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameComponent.Game

trait FileIOInterface {
  def load: GameboardInterface
  def save(grid: GameboardInterface, game:Game): Unit
}
