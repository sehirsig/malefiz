package de.htwg.se.malefiz.util

import de.htwg.se.malefiz.model.Gameboard

trait BlockStrategy {
  def replaceBlock(spielbrett:Gameboard): Gameboard
}
