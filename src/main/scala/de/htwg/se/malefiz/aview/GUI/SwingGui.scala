package de.htwg.se.malefiz.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.malefiz.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.malefiz.controller.controllerComponent._

import java.awt.Color
import javax.swing.ImageIcon

class SwingGui(controller: ControllerInterface) extends Frame {
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

  val BC = controller.getpureCell("BlockedCell")
  val BCImage = new ImageIcon(this.getClass().getResource("/blocked.png"))
  val FC = controller.getpureCell("FreeCell")
  val FCImage = new ImageIcon(this.getClass().getResource("/free.png"))
  val SC = controller.getpureCell("SecureCell")
  val SCImage = new ImageIcon(this.getClass().getResource("/free.png"))
  val SC1 = controller.getpureCell("Start1Cell")
  val SC1Image = new ImageIcon(this.getClass().getResource("/start1.png"))
  val SC2 = controller.getpureCell("Start2Cell")
  val SC2Image = new ImageIcon(this.getClass().getResource("/start2.png"))
  val SC3 = controller.getpureCell("Start3Cell")
  val SC3Image = new ImageIcon(this.getClass().getResource("/start3.png"))
  val SC4 = controller.getpureCell("Start4Cell")
  val SC4Image = new ImageIcon(this.getClass().getResource("/start4.png"))
  val GC = controller.getpureCell("GoalCell")
  val GCImage = new ImageIcon(this.getClass().getResource("/goal.png"))
  val PC1 = controller.getpureCell("PlayerCell1")
  val PC1Image = new ImageIcon(this.getClass().getResource("/player1.png"))
  val PC2 = controller.getpureCell("PlayerCell2")
  val PC2Image = new ImageIcon(this.getClass().getResource("/player2.png"))
  val PC3 = controller.getpureCell("PlayerCell3")
  val PC3Image = new ImageIcon(this.getClass().getResource("/player3.png"))
  val PC4 = controller.getpureCell("PlayerCell4")
  val PC4Image = new ImageIcon(this.getClass().getResource("/player4.png"))
  val IC = controller.getpureCell("InvalidCell")
  val ICImage = new ImageIcon()


  def gridPanel = new GridPanel(controller.gameboard.getStandardXYsize._1,controller.gameboard.getStandardXYsize._2) {
    border = LineBorder(Color.BLACK, 2)
    background = new Color(204,144,5)
    for {
      outerRow <- 0 until controller.gameboard.getStandardXYsize._1
      outerColumn <- 0 until controller.gameboard.getStandardXYsize._2
    } {
      count = count + 1
      contents += new Label {
        icon = controller.gameboard.rows.flatMap(_.toList)(count - 1) match {
          case BC => BCImage
          case FC => FCImage
          case SC => SCImage
          case SC1 => SC1Image
          case SC2 => SC2Image
          case SC3 => SC3Image
          case SC4 => SC4Image
          case GC => GCImage
          case PC1 => PC1Image
          case PC2 => PC2Image
          case PC3 => PC3Image
          case PC4 => PC4Image
          case IC => ICImage
          case _ => ICImage
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



