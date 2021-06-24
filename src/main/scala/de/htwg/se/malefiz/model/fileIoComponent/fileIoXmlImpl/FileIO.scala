package de.htwg.se.malefiz.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.model.cellComponent.{Cell, PlayerCell}
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.playerComponent.Player

import scala.io.Source
import scala.xml.PrettyPrinter

/** Alle Zellen unseres Malefiz-Spiels, die es gibt.
 *  XML Implementierung unserer FileIO, um den momentanen Spielstand zu speichern und später wieder aufzurufen.
 *  Gespeichert wird die Dimension X und Y, sowie alle Positionen der Zellen und dessen Inhalt.
 *  Der Inhalt wird als String gespeichert und später wieder durch Gameboard Funktionen wiederhergestellt.
 *
 *  @author sehirsig & franzgajewski
 */
class FileIO extends FileIOInterface{
  override def load(game:Game): (GameboardInterface,Game) = {
    var gameboard: GameboardInterface = null
    val file = scala.xml.XML.loadFile("gameboard.xml")
    val injector = Guice.createInjector(new MalefizModule)
    gameboard = injector.instance[GameboardInterface]

    /** Spielfiguren Counter */
    var player1Fig = 0
    var player2Fig = 0
    var player3Fig = 0
    var player4Fig = 0

    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: String = cell.text.trim
      val endzelle = gameboard.getCell(value)
      gameboard = gameboard.movePlayer((row, col), endzelle)
      if (endzelle.isInstanceOf[PlayerCell]) {
        endzelle.cellStatus match {
          case "1 " => game.players(0).figures(player1Fig) = game.players(0).figures(player1Fig).updatePos(row,col); player1Fig += 1;
          case "2 " => game.players(1).figures(player2Fig) = game.players(1).figures(player2Fig).updatePos(row,col); player2Fig += 1;
          case "3 " => game.players(2).figures(player3Fig) = game.players(2).figures(player3Fig).updatePos(row,col); player3Fig += 1;
          case "4 " => game.players(3).figures(player4Fig) = game.players(3).figures(player4Fig).updatePos(row,col); player4Fig += 1;
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
  def gameboardToXml(gameboard: GameboardInterface) = {
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

  def cellToXml(gameboard: GameboardInterface, row: Int, col: Int) = {
    <cell row={ row.toString } col={ col.toString }>
      { gameboard.cellString(row, col) }
    </cell>
  }
}
