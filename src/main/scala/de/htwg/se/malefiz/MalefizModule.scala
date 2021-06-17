package de.htwg.se.malefiz

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.gameboardComponent.gameboardAdvancedImpl.Gameboard
import de.htwg.se.malefiz.model.fileIoComponent._

class MalefizModule extends AbstractModule with ScalaModule {

  val defaultSizeX:Int = 18
  val defaultSizeY:Int = 19

  override def configure():Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSizeX")).to(defaultSizeX)
    bindConstant().annotatedWith(Names.named("DefaultSizeY")).to(defaultSizeY)
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameboardInterface].toInstance(new Gameboard(defaultSizeX,defaultSizeY))

    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }
}
