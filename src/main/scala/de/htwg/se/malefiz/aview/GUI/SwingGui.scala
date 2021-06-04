package de.htwg.se.malefiz.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.util.Observer

import scala.io.Source._
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.model.properties.Settings

import java.awt.Color
import javax.swing.ImageIcon

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)

  this.visible = true
  title = "HTWG Malefiz"
  preferredSize = new Dimension(600, 800)

  val statusline = new TextField {
    text = GameStatus.gameMessage(controller.gameStatus)
    columns = 20
    editable = false
  }

//IdleGUIState, PlayerNameState, PlayingGUIState, ChooseGameFigGUIState, WinnerGUIState, MovingGUIState



  //val namebox = new TextField("Name", 10)
  val namebutton = new Button {
    text = "Start the Game!"
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked => if (controller.game.players.length > 0) controller.startGame()
    }
  }
  val nameinputPanel = new FlowPanel {
    //contents += namebox
    contents += namebutton
    border = Swing.EmptyBorder(105)
  }
  val welcomeMessage = new Label {
    text = "Welcome to Malefiz the Game!"
  }

  val welcomeAddPlayerButton = new Button {
    text = "Add a Player!"
    visible = true
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked => playChoose
    }
  }


  val welcomePanel = new BoxPanel(Orientation.Vertical) {
    contents += welcomeMessage
    contents += nameinputPanel
    contents += welcomeAddPlayerButton
    contents += statusline
    border = Swing.EmptyBorder(20,20,20,20)
    contents(0).preferredSize = new Dimension(780,1500)
    contents(1).preferredSize = new Dimension(780,50)
    contents(2).preferredSize = new Dimension(780,50)
    contents(3).preferredSize = new Dimension(780,30)
  }

  val currentplayer = new TextField{
    text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    columns = 10
    editable = false
  }

  val diceRolled = new TextField{
    text = ""
    columns = 10
    editable = false
  }


  contents = {
    welcomePanel
  }

  var count = 0

  val movePanel = new FlowPanel() {
    contents += new Button{
      text = "Go Left"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked => controller.move("a", controller.selectedFigNum)
      }
    }
    contents += new Button{
      text = "Go Up"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.move("w", controller.selectedFigNum)
      }
    }
    contents += new Button{
      text = "Go Down"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.move("s", controller.selectedFigNum)
      }
    }
    contents += new Button{
      text = "Go Right"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.move("d", controller.selectedFigNum)
      }
    }
    contents += new Button{
      text = "Skip"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.move("skip", controller.selectedFigNum)
      }
    }
  }
/*
  val movePanel = new Button {
    text ="Move"
    listenTo(this.keys)
    reactions += {
      case KeyPressed(_, Key.W, _, _) => println("w")
      case KeyPressed(_, Key.A, _, _) => println("a")
      case KeyPressed(_, Key.S, _, _) => println("s")
      case KeyPressed(_, Key.D, _, _) => println("d")
    }
    focusable = true
    requestFocus
  }*/


  val diceButton = new Button{
    text = "Roll the Dice!"
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked => controller.rollDice()
    }
  }

  val choosePanel = new FlowPanel {
    contents += new Button{
      text = "Figure 1"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked => controller.selectFigure(1)
      }
    }
    contents += new Button{
      text = "Figure 2"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.selectFigure(2)
      }
    }
    contents += new Button{
      text = "Figure 3"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.selectFigure(3)
      }
    }
    contents += new Button{
      text = "Figure 4"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.selectFigure(4)
      }
    }
    contents += new Button{
      text = "Figure 5"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>  controller.selectFigure(5)
      }
    }
  }

  def gridPanel = new GridPanel(Settings().xDim,Settings().yDim) {
    border = LineBorder(Color.BLACK, 2)
    background = Color.ORANGE
    for {
      outerRow <- 0 until Settings().xDim
      outerColumn <- 0 until Settings().yDim
    } {
      count = count + 1
      contents += new Label {
        icon = controller.gameboard.rows.flatMap(_.toList)(count - 1) match {
          case BlockedCell => new ImageIcon("src\\main\\resources\\blocked.png")
          case FreeCell => new ImageIcon("src\\main\\resources\\free.png")
          case SecureCell => new ImageIcon("src\\main\\resources\\free.png")
          case Start1Cell => new ImageIcon("src\\main\\resources\\start1.png")
          case Start2Cell => new ImageIcon("src\\main\\resources\\start2.png")
          case Start3Cell => new ImageIcon("src\\main\\resources\\start3.png")
          case Start4Cell => new ImageIcon("src\\main\\resources\\start4.png")
          case GoalCell => new ImageIcon("src\\main\\resources\\goal.png")
          case PlayerCell(num) => num match {
            case 1 => new ImageIcon("src\\main\\resources\\player1.png")
            case 2 => new ImageIcon("src\\main\\resources\\player2.png")
            case 3 => new ImageIcon("src\\main\\resources\\player3.png")
            case 4 => new ImageIcon("src\\main\\resources\\player4.png")
            case _ => new ImageIcon("src\\main\\resources\\player0.png")
          }
          case InvalidCell => new ImageIcon()
          case _ => new ImageIcon()
        }}
      if (count == 342) count = 0
    }
  }

  reactions += {
    case event: Moving => redrawPlay
    case event: RollDice => redrawRoll
    case event: SettingUp => reinfo
    case event: StartUp => rePlayers
    case event: StartGame => redrawRoll
    case event: ChooseFig => redrawChooseFig
    case event: WonGame => reGameWon
    case event: GameReset => reGameNew
  }



  def choosePlayer:Option[String] ={
    Dialog.showInput(contents.head, "Enter Player " + (controller.game.players.length + 1).toString + " Name!", "Malefiz - Player Configurator", Dialog.Message.Question, Swing.EmptyIcon, Nil, "Player " + (controller.game.players.length+1))
  }


  var enough = false
  def playChoose: Unit = {
    if (controller.game.players.length < 4) {
      if (controller.game.players.length > 1) {
        if (!enough) {
          val s: Option[String] = choosePlayer
          if (s != None) {
            controller.addPlayer()
            controller.addPlayerName(s.toString)
          } else {
            enough = true
          }
          return
        }
      } else {
        val s: Option[String] = choosePlayer
        if (s != None) {
          controller.addPlayer()
          controller.addPlayerName(s.toString)
        }
      }
    }
  }

  def rePlayers:Unit = {
    repaint
  }

  def reinfo:Unit = {
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    repaint
  }

  def redrawPlay:Unit = {
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += movePanel
        contents += currentplayer
        contents += diceRolled
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,600)
        contents(2).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,5)
        contents(3).preferredSize = new Dimension(780,5)
        contents(4).preferredSize = new Dimension(780,5)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.savedGame.lastFullDice + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  def redrawRoll:Unit = {
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += diceButton
        contents += currentplayer
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,600)
        contents(1).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,5)
        contents(3).preferredSize = new Dimension(780,5)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.savedGame.lastFullDice + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  def redrawChooseFig:Unit = {
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += choosePanel
        contents += diceRolled
        contents += currentplayer
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,600)
        contents(1).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,5)
        contents(3).preferredSize = new Dimension(780,5)
        contents(4).preferredSize = new Dimension(780,5)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.moveCounter + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  def reGameWon:Unit = {
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += statusline
        contents += gameWonButton
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,400)
        contents(1).preferredSize = new Dimension(780,100)
        contents(2).preferredSize = new Dimension(780,200)
      }
    }
    statusline.text = {
      "We Have a Winner: " + controller.gameWon._2 + "\n" + statusline.text
    }
      currentplayer.text = ""
      diceRolled.text = ""
      enough = false
      repaint
  }

  def reGameNew:Unit = {
    contents = welcomePanel
    rePlayers
    repaint
  }


  val gameWonButton = new Button{
    text = "Press to play again!"
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>  controller.resetGame()
    }
  }
}



