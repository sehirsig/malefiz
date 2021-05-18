package de.htwg.se.malefiz.model

case class Gamefigure(number: Int, coordx:Int, coordy:Int) {
  def setCoordinates(newx:Int, newy:Int):Gamefigure = copy(number,newx,newy)
  def getCoordinates:(Int,Int) = (coordx,coordy)
  def getNumber:Int = number
  override def toString:String = number.toString
}
