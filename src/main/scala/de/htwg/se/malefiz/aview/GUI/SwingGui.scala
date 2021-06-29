package de.htwg.se.malefiz.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.malefiz.controller.controllerComponent._

import java.awt.{Color, Font}
import javax.swing.{ImageIcon, JOptionPane}

/** Malefiz game with graphical user interface.
 *
 *  @author sehirsig & franzgajewski
 */
class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)

  /** Set main frame to visible. */
  this.visible = true

  /** End game, when main frame gets closed. */
  peer.setDefaultCloseOperation(3)

  title = "HTWG Malefiz"

  /** Set size of main frame. */
  preferredSize = new Dimension(600, 800)

  /** Text field for game state. */
  val statusline = new TextField {
    text = GameStatus.gameMessage(controller.gameStatus)
    columns = 20
    editable = false
  }

  /** Panel for adding players. */
  val nameinputPanel = new FlowPanel {
    contents += new Button {
      text = "Start the Game!"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked => if (controller.game.players.length > 0) controller.startGame()
      }
      contents += new Button {
        text = "Add a Player!"
        visible = true
        listenTo(mouse.clicks)
        reactions += {
          case _: MouseClicked => playChoose
        }
      }
    }
  }

  val welcomeMessage = new Label {
    text = "Welcome to Malefiz the Game!"
  }


  /** Welcome message with continue-button. */
  val welcomePanel = new BoxPanel(Orientation.Vertical) {
    contents += welcomeMessage
    contents += nameinputPanel
    contents += statusline
    border = Swing.EmptyBorder(20,20,20,20)
    contents(0).preferredSize = new Dimension(600,400)
    contents(0).xLayoutAlignment = 1
    contents(0).font = new Font("Bookman Old Style", Font.ITALIC, 24)
    contents(1).preferredSize = new Dimension(600,50)
    contents(2).preferredSize = new Dimension(600,30)
  }

  /** Textfield for the current player. */
  val currentplayer = new TextField{
    text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    columns = 10
    editable = false
  }

  /** Textfield for the rolled number. */
  val diceRolled = new TextField{
    text = ""
    columns = 10
    editable = false
  }

  val helpString = "For more informations visit our ReadMe.md on our Github @ github.com/franzgajewski/malefiz !"

  /** Dialog box to warn for too few player at load. */
  def warnLoad:Unit ={
    Dialog.showMessage(contents.head, "This game has " + controller.game.getPlayerNumber().toString + " Player/s. You need to add atleast 2 players to load a game!", "Malefiz - Load save game", Dialog.Message.Warning, Swing.EmptyIcon)
  }


  /** Dialog box to show the help. */
  def helpBox:Unit ={
    Dialog.showMessage(contents.head, helpString, "Malefiz - Load Savegame", Dialog.Message.Info, Swing.EmptyIcon)
  }

  /** MenuBar with the load game option. */
  val loadmenuBar = new MenuBar{
    contents += new Menu("Help") {
      contents += new MenuItem(Action("ReadMe") { helpBox })
    }
    contents += new Menu("Settings") {
      contents += new MenuItem(Action("Load Game") { if (controller.game.getPlayerNumber() > 1) {controller.load } else { warnLoad }})
      contents += new MenuItem(Action("Reset Game") { controller.resetGame() })
    }
  }

  /** MenuBar with the save game option. */
  val closedmenuBar = new MenuBar{
    contents += new Menu("Help") {
      contents += new MenuItem(Action("ReadMe") { helpBox })
    }
    contents += new Menu("Settings") {
      contents += new MenuItem(Action("Save Game") { controller.save })
      contents += new MenuItem(Action("Reset Game") { controller.resetGame() })
    }
  }

  /** MenuBar with save/load disabled for while not moving. */
  val choosemenuBar = new MenuBar{
    contents += new Menu("Help") {
      contents += new MenuItem(Action("ReadMe") { helpBox })
    }
    contents += new Menu("Settings") {
      contents += new MenuItem(Action("Reset Game") { controller.resetGame() })
    }
  }

  /** MenuBar with save/load disabled but Undo / Redo for while moving.*/
  val helpmovemenuBar = new MenuBar{
    contents += new Menu("Help") {
      contents += new MenuItem(Action("ReadMe") { helpBox })
    }
    contents += new Menu("Settings") {
      contents += new MenuItem(Action("Reset Game") { controller.resetGame() })
      contents += new MenuItem(Action("Undo Move") { controller.move("undo", controller.selectedFigNum) })
      contents += new MenuItem(Action("Redo Move") { controller.move("redo", controller.selectedFigNum) })
    }
  }

  /** Initialize menu bar. */
  menuBar = loadmenuBar

  /** Main contents when initialising the game. */
  contents = {
    welcomePanel
  }

  /** Panel for moving game figures. */
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

  /** Panel for saving and loading the game, as well as dice rolling. */
  val diceRollPanel = new FlowPanel() {
    contents += new Button{
      text = "Roll the Dice!"
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked => controller.rollDice()
      }
    }
  }

  /** Panel for selecting a game figure. */
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

  /** At game start PNGs from the resource folder are loaded into variables. */
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

  /** Counter to get to 342 (total number of cells). */
  var count = 0

  /** Graphical creation of the game board. */
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

  /** Events the GUI listens and reacts to. */
  reactions += {
    case event: Moving => redrawPlay
    case event: RollDice => redrawRoll
    case event: SettingUp => reinfo
    case event: StartUp => rePlayers
    case event: StartGame => redrawRoll
    case event: ChooseFig => redrawChooseFig
    case event: WonGame => reGameWon
    case event: GameReset => reGameNew
    case event: GameSaved => redrawRoll
    case event: GameLoaded => redrawRoll
  }

  /** Dialog box to add player name. */
  def choosePlayer:Option[String] ={
    Dialog.showInput(contents.head, "Enter Player " + (controller.game.players.length + 1).toString + " Name!", "Malefiz - Player Configurator", Dialog.Message.Question, Swing.EmptyIcon, Nil, "Player " + (controller.game.players.length+1))
  }

  /** Boolean to prevent more than 4 players joining. */
  var enough = false

  /** Add new players. Prevents new players, when game is full. */
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

  /** Repaint players. */
  def rePlayers:Unit = {
    repaint
  }

  /** Repaint game state. */
  def reinfo:Unit = {
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    repaint
  }

  /** Repaint entire frame, when in play mode. */
  def redrawPlay:Unit = {
    menuBar = helpmovemenuBar
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += movePanel
        contents += currentplayer
        contents += diceRolled
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,580)
        contents(2).preferredSize = new Dimension(400,50)
        contents(2).preferredSize = new Dimension(400,5)
        contents(3).preferredSize = new Dimension(400,5)
        contents(4).preferredSize = new Dimension(400,5)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.savedGame.lastFullDice + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  /** Repaint entire frame, when in die-roll mode. */
  def redrawRoll:Unit = {
    menuBar = closedmenuBar
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += diceRollPanel
        contents += currentplayer
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,580)
        contents(1).preferredSize = new Dimension(600,50)
        contents(2).preferredSize = new Dimension(600,5)
        contents(3).preferredSize = new Dimension(600,5)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.savedGame.lastFullDice + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  /** Repaint entire frame, when in game figure select. */
  def redrawChooseFig:Unit = {
    menuBar = choosemenuBar
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += choosePanel
        contents += diceRolled
        contents += currentplayer
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,580)
        contents(1).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,10)
        contents(3).preferredSize = new Dimension(780,10)
        contents(4).preferredSize = new Dimension(780,10)
      }
    }
    statusline.text = GameStatus.gameMessage(controller.gameStatus)
    currentplayer.text = "Turn of Player: " + controller.playerStatus.getCurrentPlayer.toString
    diceRolled.text = "You rolled a " + controller.moveCounter + "." + " Moves left: " + controller.moveCounter
    repaint
  }

  /** Frame for when the game is won. */
  def reGameWon:Unit = {
    menuBar = choosemenuBar
    contents = {
      new BoxPanel(Orientation.Vertical) {
        contents += gridPanel
        contents += gameWonPanel
        contents += statusline
        border = Swing.EmptyBorder(20,20,20,20)
        contents(0).preferredSize = new Dimension(400,580)
        contents(1).preferredSize = new Dimension(780,50)
        contents(2).preferredSize = new Dimension(780,5)
      }
    }
    statusline.text = {
       GameStatus.gameMessage(controller.gameStatus)+ "\n" + "Winner: " + controller.gameWon._2
    }
      currentplayer.text = ""
      diceRolled.text = ""
      enough = false
      repaint
  }

  /** Frame for restarting after the game was won. */
  def reGameNew:Unit = {
    menuBar = loadmenuBar
    contents = welcomePanel
    rePlayers
    repaint
  }

  val gameWonButton = new Button{
    text = "Press to reset and play again!"
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>  controller.resetGame()
    }
  }

  val gameWonPanel = new FlowPanel() {
    contents += gameWonButton
  }
}