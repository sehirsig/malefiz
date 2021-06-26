package de.htwg.se.malefiz.model.playerComponent

/** Builder-Pattern für unsere Spieler-Klasse.
 *
 *  @author sehirsig & franzgajewski
 */
trait PlayerBuilder {
  def setName(name: String): PlayerBuilder
  def setID(id: Int): PlayerBuilder
  def setStartingPos(pos: (Int, Int)): PlayerBuilder
  def build(): Player
}

/** Implementierung des Interfaces */
case class PlayerBuilderImp() extends PlayerBuilder {
  var name: String = ""
  var id: Int = 0
  var pos:(Int, Int) = (0,0)

  /** Name des Spielers setzen */
  override def setName(nameNew: String): PlayerBuilder = {
    this.name = nameNew
    this
  }

  /** ID des Spielers setzen */
  override def setID(idNew: Int): PlayerBuilder = {
    this.id = idNew
    this
  }

  /** Startposition für die Spielfiguren des Spielers setzen */
  override def setStartingPos(posNew: (Int, Int)): PlayerBuilder = {
    this.pos = posNew
    this
  }

  /** Den Spieler zusammen bauen */
  override def build(): Player = Player(name, id, pos)
}