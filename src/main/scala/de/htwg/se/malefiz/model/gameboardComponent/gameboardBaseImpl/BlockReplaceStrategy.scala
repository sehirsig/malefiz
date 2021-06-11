package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy

import scala.util.Random

case class BlockReplaceStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: GameboardInterface): GameboardInterface = {
    val b = searchVectors(FreeCell, spielbrett.rows)
    val rnd = new Random()
    val randomNumber = rnd.nextInt(b.size - 1)
    val replace = b(randomNumber)
    spielbrett.movePlayer(replace, "BlockedCell")
  }

  private def searchVectors(x: Cell, vec: Vector[Vector[Cell]]) =
    for {
      i <- 0 until vec.size
      j <- 0 until vec(i).size
      if vec(i)(j) == x
    } yield (i, j)
}
