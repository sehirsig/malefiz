package de.htwg.se.malefiz.model.gameboardComponent.gameboardAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard => BaseGameboard}

class Gameboard @Inject() (@Named("DefaultSizeX") sizex:Int, @Named("DefaultSizeY") sizey:Int) extends BaseGameboard(sizex,sizey){
}
