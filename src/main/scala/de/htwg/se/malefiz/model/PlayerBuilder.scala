package de.htwg.se.malefiz.model

trait PlayerBuilder {
  def setName(name: String): PlayerBuilder
  def setID(id: Int): PlayerBuilder
  def build(): Player
}

case class PlayerBuilderImp(name: String = "", id: Int = 0) extends PlayerBuilder {
  override def setName(name: String): PlayerBuilder = copy(name = name)
  override def setID(id: Int): PlayerBuilder = copy(id = id)

  override def build(): Player = Player(name, id)
}