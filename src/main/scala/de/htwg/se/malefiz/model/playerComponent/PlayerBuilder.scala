package de.htwg.se.malefiz.model.playerComponent

/** Builder pattern for player class.
 *
 *  @author sehirsig & franzgajewski
 */
trait PlayerBuilder {
  def setName(name: String): PlayerBuilder
  def setID(id: Int): PlayerBuilder
  def setStartingPos(pos: (Int, Int)): PlayerBuilder
  def build(): Player
}

/** Implement of the interface. */
case class PlayerBuilderImp() extends PlayerBuilder {
  var name: String = ""
  var id: Int = 0
  var pos:(Int, Int) = (0,0)

  /** Set player name. */
  override def setName(nameNew: String): PlayerBuilder = {
    this.name = nameNew
    this
  }

  /** Set player ID. */
  override def setID(idNew: Int): PlayerBuilder = {
    this.id = idNew
    this
  }

  /** Set starting position for game figures of the player. */
  override def setStartingPos(posNew: (Int, Int)): PlayerBuilder = {
    this.pos = posNew
    this
  }

  /** Build player. */
  override def build(): Player = Player(name, id, pos)
}