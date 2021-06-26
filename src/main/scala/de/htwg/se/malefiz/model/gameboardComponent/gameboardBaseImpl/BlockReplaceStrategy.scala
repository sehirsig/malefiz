package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy
import scala.util.Random

/** Blocked cell replace strategy of the strategy pattern.
 *  Move barricade to a random legal location, when captured.
 *
 *  @author sehirsig & franzgajewski
 */
case class BlockReplaceStrategy() extends BlockStrategy {
  override def replaceBlock(spielbrett: GameboardInterface): GameboardInterface = {
    val b = searchVectors(FreeCell, spielbrett.rows)
    val rnd = new Random()
    val randomNumber = rnd.nextInt(b.size - 1)
    val replace = b(randomNumber)
    spielbrett.movePlayer(replace, BlockedCell)
  }


  /** Determines legal cells to place a barricade.
   *
   * @param x legal cell type (FreeCell)
   * @param vec game board matrix
   * @return coordinates of legal cells
   */
  private def searchVectors(x: Cell, vec: Vector[Vector[Cell]]): IndexedSeq[(Int,Int)] =
    for {
      i <- 0 until vec.size
      j <- 0 until vec(i).size
      if vec(i)(j) == x
    } yield (i, j)
}
