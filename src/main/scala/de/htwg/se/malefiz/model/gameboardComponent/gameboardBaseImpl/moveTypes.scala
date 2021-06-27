package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

/** Class to overwrite coordinates of a cell quickly to those of the next cell.
 *
 *  @author sehirsig & franzgajewski
 */
object moveTypes {
  def goDown(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1 + 1, oldcord._2)
  }

  def goUp(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1 - 1, oldcord._2)
  }

  def goRight(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1, oldcord._2 + 1)
  }

  def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    (oldcord._1, oldcord._2 - 1)
  }
}
