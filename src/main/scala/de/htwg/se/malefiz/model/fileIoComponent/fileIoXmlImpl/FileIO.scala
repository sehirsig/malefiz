package de.htwg.se.malefiz.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.model.cellComponent.PlayerCell
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface

import scala.xml.{Elem, PrettyPrinter}

/** XML implementation of the file IO. Saves current game state for later use.
 *  Saves x and y dimension and all cells as well as their content.
 *  The content is stored as a string and later recovered by Gameboard functions.
 *
 *  @author sehirsig & franzgajewski
 */
class FileIO extends FileIOInterface{
  override def load(game:Game): (GameboardInterface,Game) = {
    var gameboard: GameboardInterface = null
    val file = scala.xml.XML.loadFile("gameboard.xml")
    val injector = Guice.createInjector(new MalefizModule)
    gameboard = injector.instance[GameboardInterface]

    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: String = cell.text.trim
      val endzelle = gameboard.getCell(value)
      gameboard = gameboard.movePlayer((row, col), endzelle)
      if (endzelle.isInstanceOf[PlayerCell]) {
        val fignum = endzelle.getFigureNum
        endzelle.cellStatus match {
          case "1 " => game.players(0).figures(fignum - 1) = game.players(0).figures(fignum - 1).updatePos(row, col);
          case "2 " => game.players(1).figures(fignum - 1) = game.players(1).figures(fignum - 1).updatePos(row, col);
          case "3 " => if (game.players.length > 2) { game.players(2).figures(fignum - 1) = game.players(2).figures(fignum - 1).updatePos(row, col); }
          case "4 " => if (game.players.length > 3) { game.players(3).figures(fignum - 1) = game.players(3).figures(fignum - 1).updatePos(row, col); }
        }
      }
    }
    (gameboard,game)
  }

  override def save(gameboard: GameboardInterface): Unit = saveString(gameboard)

  def saveXML(gameboard: GameboardInterface): Unit = {
    scala.xml.XML.save("gameboard.xml", gameboardToXml(gameboard))
  }

  def saveString(gameboard: GameboardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameboard.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameboardToXml(gameboard))
    pw.write(xml)
    pw.close
  }
  def gameboardToXml(gameboard: GameboardInterface): Elem = {
    <gameboard sizex={ gameboard.getStandardXYsize._1.toString }
               sizey={ gameboard.getStandardXYsize._2.toString }>
      {
      for {
        row <- 0 until gameboard.getStandardXYsize._1
        col <- 0 until gameboard.getStandardXYsize._2
      } yield cellToXml(gameboard, row, col)
      }
    </gameboard>
  }

  def cellToXml(gameboard: GameboardInterface, row: Int, col: Int): Elem = {
    <cell row={ row.toString } col={ col.toString }>
      { gameboard.cellString(row, col) }
    </cell>
  }
}
