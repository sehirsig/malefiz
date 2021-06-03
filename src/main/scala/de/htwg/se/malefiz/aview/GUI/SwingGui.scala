package de.htwg.se.malefiz.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.util.Observer

import scala.io.Source._
import de.htwg.se.malefiz.aview.GUI.GUIState
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import java.awt.Color
import javax.swing.ImageIcon

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  var currentState:GUIState = IdleGUIState

  this.visible = true
  title = "HTWG Malefiz"
  preferredSize = new Dimension(1000, 800)

  val statusline = new TextField(GameStatus.gameMessage(controller.gameStatus), 20)

//IdleGUIState, PlayerNameState, PlayingGUIState, ChooseGameFigGUIState, WinnerGUIState, MovingGUIState



  val nameinput = new FlowPanel {
    contents += new TextField("Person", 10)
    contents += new Button("Enter Name")
    contents += new TextField(GameStatus.gameMessage(controller.gameStatus), 20)
    border = Swing.EmptyBorder(15)
  }

  contents = {
    new FlowPanel {
      contents += nameinput
      contents += statusline
    }
    new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
    }
  }

  var count = 0
  controller.gameboard.rows.flatten

  val BlockIcon = new Label {
    new ImageIcon("path to the image file")
  }
  val FreeIcon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val SecureIcon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val StartIcon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val GoalIcon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val Player1Icon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val Player2Icon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val Player3Icon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val Player4Icon = new Label {
    icon = new ImageIcon("path to the image file")
  }
  val InvalidIcon = new Label {
    icon = new ImageIcon("path to the image file")
  }

  val meinbild = new Label {
    icon = new ImageIcon("/resources/blockade/cool.png")
  }



  def gridPanel = new GridPanel(Settings().xDim,Settings().yDim) {
    border = LineBorder(Color.BLACK, 2)
    background = Color.ORANGE
    for {
      outerRow <- 0 until Settings().xDim
      outerColumn <- 0 until Settings().yDim
    } {
      //contents += new Button(count.toString())
      count = count + 1
      contents += new Label(controller.gameboard.rows.flatMap(_.toList)(count - 1) match {
        case BlockedCell => "X"
        case FreeCell => "d"
        case SecureCell => "d"
        case StartCell => "d"
        case GoalCell => "d"
        case PlayerCell(num) => num match {
          case 1 => "P1"
          case 2 => "P2"
          case 3 => "P3"
          case 4 => "P4"
          case _ => "P0"
        }
        case InvalidCell => ""
        case _ => ""
      })//controller.gameboard.rows.flatMap(_.toList)(count).toString()
    }
  }

  reactions += {
    case event: CellChanged => redraw
    case event: SettingUp => reinfo
  }

  def reinfo:Unit = {
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    repaint
  }

  def redraw:Unit = {
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    repaint
  }
}
