package de.htwg.se.malefiz.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: GameboardInterface = {
    var gameboard: GameboardInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new MalefizModule)
    gameboard = injector.instance[GameboardInterface]
    gameboard
  }

  override def save(grid: GameboardInterface, game:Game): Unit = {

  }
}
