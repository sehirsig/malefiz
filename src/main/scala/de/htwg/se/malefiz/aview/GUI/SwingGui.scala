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


  val namebox = new TextField("Name", 10)
  val namebutton = new Button {
    text = "Test"
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked => controller.startGame()
    }
  }

  val nameinputPanel = new FlowPanel {
    contents += namebox
    contents += namebutton
    border = Swing.EmptyBorder(105)
  }

  val welcomeMessage = new Label {
    text = "Welcome to Malefiz the Game!"
  }

  val welcomePanel = new BoxPanel(Orientation.Vertical) {
    //contents += gridPanel
    contents += welcomeMessage
    contents += nameinputPanel
    contents += statusline
    //contents += BlockIcon
    border = Swing.EmptyBorder(20,20,20,20)
    contents(0).preferredSize = new Dimension(780,1500)
    contents(1).preferredSize = new Dimension(780,50)
    contents(2).preferredSize = new Dimension(780,30)
  }

  contents = {
    new BoxPanel(Orientation.Vertical) {
      //contents += gridPanel
      contents += welcomeMessage
      contents += nameinputPanel
      contents += statusline
      border = Swing.EmptyBorder(20,20,20,20)
      contents(0).preferredSize = new Dimension(780,1500)
      contents(1).preferredSize = new Dimension(780,50)
      contents(2).preferredSize = new Dimension(780,30)
    }
  }

  var count = 0

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
        case BlockedCell => "⌧"
        case FreeCell => "▓"
        case SecureCell => "▓"
        case StartCell => "█"
        case GoalCell => "©"
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
      if (count == 342) count = 0
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
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        //contents += welcomeMessage
        contents += nameinputPanel
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(780,1500)
        contents(1).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,30)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    repaint
  }
}
