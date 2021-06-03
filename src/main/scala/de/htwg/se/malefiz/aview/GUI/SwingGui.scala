package de.htwg.se.malefiz.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.util.Observer

import scala.io.Source._

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  title = "HTWG Malefiz"
  preferredSize = new Dimension(320, 240)
  contents = new Label("Here is the contents!")

  reactions += {
    case event: CellChanged =>
    case event: SettingUp => this.visible = true
  }
}
