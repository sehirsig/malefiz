package de.htwg.se.malefiz.model.gameboardComponent.gameboardAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.{Gameboard => BaseGameboard}

/** Extension of the base implementation of the game board.
 *  Here the dimensions are written in the constructor.
 *
 *  @author sehirsig & franzgajewski
 */
class Gameboard @Inject() (@Named("DefaultSizeX") sizex:Int, @Named("DefaultSizeY") sizey:Int) extends BaseGameboard(sizex,sizey){
}
