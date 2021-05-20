package de.htwg.se.malefiz.model

trait PlayerBuilder {
  def setName(name: String): PlayerBuilder
  def setID(id: Int): PlayerBuilder
  def setStartingPos(pos: (Int, Int)): PlayerBuilder
  def build(): Player
}

case class PlayerBuilderImp(name: String = "", id: Int = 0, pos:(Int, Int) = (0,0)) extends PlayerBuilder {
  override def setName(name: String): PlayerBuilder = copy(name = name)
  override def setID(id: Int): PlayerBuilder = copy(id = id)
  override def setStartingPos(pos: (Int, Int)): PlayerBuilder = copy(pos = pos)
  override def build(): Player = Player(name, id, pos)
}