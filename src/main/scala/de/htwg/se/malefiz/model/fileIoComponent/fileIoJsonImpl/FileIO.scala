package de.htwg.se.malefiz.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.model.cellComponent.PlayerCell
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import play.api.libs.json._
import play.api.libs.json.Reads._
import scala.io.Source

/** Alle Zellen unseres Malefiz-Spiels, die es gibt.
 *  JSON Implementierung unserer FileIO, um den momentanen Spielstand zu speichern und später wieder aufzurufen.
 *  Gespeichert wird die Dimension X und Y, sowie alle Positionen der Zellen und dessen Inhalt.
 *  Der Inhalt wird als String gespeichert und später wieder durch Gameboard Funktionen wiederhergestellt.
 *
 *  @author sehirsig & franzgajewski
 */
class FileIO extends FileIOInterface {

  override def load(game:Game): (GameboardInterface,Game) = {

    val source: String = Source.fromFile("gameboard.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new MalefizModule)
    var gameboard = injector.instance[GameboardInterface]
    val sizex = (json \ "gameboard" \ "sizeX").get.toString.toInt
    val sizey = (json \ "gameboard" \ "sizeY").get.toString.toInt

    /** Spielfiguren Counter */
    var player1Fig = 0
    var player2Fig = 0
    var player3Fig = 0
    var player4Fig = 0

    for (index <- 0 until sizex * sizey) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "Type").as[String]
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

  override def save(gameboard: GameboardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameboard.json"))
    pw.write(Json.prettyPrint(gameboardToJson(gameboard)))
    pw.close
  }



  implicit val cellWrites = new Writes[String] {
    def writes(cell: String) = Json.obj(
      "Type" -> cell
    )
  }

  def gameboardToJson(gameboard: GameboardInterface): JsObject = {
    Json.obj(
      "gameboard" -> Json.obj(
        "sizeX" -> JsNumber(gameboard.getStandardXYsize._1),
        "sizeY" -> JsNumber(gameboard.getStandardXYsize._2),
        "cells" -> Json.toJson(
          for {
            row <- 0 until gameboard.getStandardXYsize._1
            col <- 0 until gameboard.getStandardXYsize._2
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(gameboard.cellString(row, col))
            )
          }
        )
      )
    )
  }
}
