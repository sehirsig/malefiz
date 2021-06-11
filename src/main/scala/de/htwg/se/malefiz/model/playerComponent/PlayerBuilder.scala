package de.htwg.se.malefiz.model.playerComponent

trait PlayerBuilder {
  def setName(name: String): PlayerBuilder
  def setID(id: Int): PlayerBuilder
  def setStartingPos(pos: (Int, Int)): PlayerBuilder
  def build(): Player
}

case class PlayerBuilderImp() extends PlayerBuilder {
  var name: String = ""
  var id: Int = 0
  var pos:(Int, Int) = (0,0)
  override def setName(nameNew: String): PlayerBuilder = {
    this.name = nameNew
    this
  }
  override def setID(idNew: Int): PlayerBuilder = {
    this.id = idNew
    this
  }
  override def setStartingPos(posNew: (Int, Int)): PlayerBuilder = {
    this.pos = posNew
    this
  }
  override def build(): Player = Player(name, id, pos)
}