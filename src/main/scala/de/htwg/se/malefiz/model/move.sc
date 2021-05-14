import de.htwg.se.malefiz.Malefiz.tui
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.{Cell, Gameboard, Gamefigure, Player}
import de.htwg.se.malefiz.model.properties.Settings

import scala.io.StdIn.readLine

val controller = new Controller(new Gameboard(Settings().xDim, Settings().yDim))
val tui = new TUI(controller)
controller.notifyObservers

tui.processing("p")
tui.processing("p")
tui.processing("s")

val p = Player.apply("Hi", 1)
val g = Gamefigure.apply(1, p)

def movePlayer(input:String): Unit = {
  moveHandler(input, g)
}

def moveHandler(input:String, figure:Gamefigure): Unit = {
  input match {
    case "w" => MoveUp
    case "a" => MoveLeft
    case "s" => MoveDown
    case "d" => MoveRight
  }
}

object changePos {
  def change(oldx:Int,oldy:Int,newx:Int,newy:Int, gb: Gameboard): Gameboard = {
    gb.replaceCell(oldx, oldy, Cell(" "))
    gb.replaceCell(newx, newy, Cell("HE"))
  }
}

trait Moving {
  def move(gf:Gamefigure)
}

object MoveUp extends Moving {
  override def move(gf: Gamefigure): Unit = {

  }
}

object MoveDown extends Moving {
  override def move(gf: Gamefigure): Unit = {
  }
}

object MoveLeft extends Moving {
  override def move(gf: Gamefigure): Unit = {
  }
}

object MoveRight extends Moving {
  override def move(gf: Gamefigure): Unit = {
  }
}