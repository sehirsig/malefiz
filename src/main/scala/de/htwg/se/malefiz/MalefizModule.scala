/*
Class:  MalefizModule.scala

Beschreibung:
Hier wird die Dependency Injection für unsere Components verwaltet.

 */

package de.htwg.se.malefiz

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.malefiz.controller.controllerComponent._
import de.htwg.se.malefiz.model.cellComponent.InvalidCell
import de.htwg.se.malefiz.model.fileIoComponent.FileIOInterface
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSave, lastSaveInterface}
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.Gameboard
import de.htwg.se.malefiz.model.fileIoComponent._

class MalefizModule extends AbstractModule with ScalaModule {

  val defaultSizeX:Int = 18
  val defaultSizeY:Int = 19

   //gameboardComponent.lastSave(0, "", InvalidCell)
  val defaultLastDice = 0
  val defaultLastWay = ""
  val defaultCell = InvalidCell


  override def configure():Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSizeX")).to(defaultSizeX)
    bindConstant().annotatedWith(Names.named("DefaultSizeY")).to(defaultSizeY)
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameboardInterface].toInstance(new Gameboard(defaultSizeX,defaultSizeY))
    bind[lastSaveInterface].toInstance(new lastSave(defaultLastDice, defaultLastWay, defaultCell))
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
  }
}
