package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.util.BlockStrategy
import scala.util.Random

/** Blocked Cell Replace Strategy unseres Strategy-Patterns.
 *  Verschiebe die Blocked Zellen in auf eine andere Position. (Nicht auf Secure-Cells, nur Free-Cells!)
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


  /** Sucht alle freien Plätze, wo die Blockade hingesetzt werden kann
   *
   * @param x zu Suchende Zelle (FreeCell)
   * @param vec unsere Matrix
   * @return
   */
  private def searchVectors(x: Cell, vec: Vector[Vector[Cell]]): IndexedSeq[(Int,Int)] =
    for {
      i <- 0 until vec.size
      j <- 0 until vec(i).size
      if vec(i)(j) == x
    } yield (i, j)
}
