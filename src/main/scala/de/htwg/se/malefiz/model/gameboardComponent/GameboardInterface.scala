package de.htwg.se.malefiz.model.gameboardComponent

import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.playerComponent.Player
import scala.util.Try

/** Interface für unsere Spielbrett Implementierungen.
 *
 *  @author sehirsig & franzgajewski
 */
trait GameboardInterface {

  /** Matrix unseres Spielbrettes */
  val rows:Vector[Vector[Cell]]

  /** Ändert die Block-Strategy
   *
   *  @param blockstrategy "remove" oder "replace"
   */
  def setBlockStrategy(blockstrategy: String): Unit

  /** Bewegt einen Spieler
   *
   *  @param coord Koordinate als Int-Tupel von der Matrix
   *  @param cell Die zu ersetzende Zelle
   *  @return das neue Spielbrett
   */
  def movePlayer(coord: (Int, Int), cell: Cell): GameboardInterface

  /** Bewegt eine Zelle
   *
   *  @param row X-Koordinate von der Matrix
   *  @param col Y-Koordinate von der Matrix
   *  @param cell Die zu ersetzende Zelle
   *  @return das neue Spielbrett
   */
  def replaceCell(row: Int, col: Int, cell: Cell): Try[GameboardInterface]

  /** Gibt eine Zelle aus Koordinaten vom Spielbrett wieder.
   *
   *  @param row X-Koordinate von der Matrix
   *  @param col Y-Koordinate von der Matrix
   *  @return Die Zelle von der Koordinate
   */
  def cell(row: Int, col: Int): Cell

  /** Neues Spielbrett in der Größe die von Settings bereitgestellt wird.
   *
   *  @return das neue Spielbrett
   */
  def newGBStandardSize: GameboardInterface

  /** Bekomme die Standardgröße aus der Settings-File
   *
   *  @return Tupel der Standard X,Y Größe des Spielbrettes
   */
  def getStandardXYsize: (Int,Int)


  /** Funktion für die Funktion der Block-Strategy
   *
   *  @param spielbrett das alte Spielbrett
   *  @return das neue Spielbrett mit geänderter Blockade
   */
  def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface

  /** Lässt den Spieler Hoch hochlaufen und überprüft diesen Zug.
   *
   *  @param spielbrett das Alte Spielbrett
   *  @param player der momentane Spieler des Zuges
   *  @param currentCoord die momentanen Koordinaten der Spielfigur
   *  @param figurenum die nummer der Spielfigur
   *  @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *  @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  /** Lässt den Spieler Runter hochlaufen und überprüft diesen Zug.
   *
   *  @param spielbrett das Alte Spielbrett
   *  @param player der momentane Spieler des Zuges
   *  @param currentCoord die momentanen Koordinaten der Spielfigur
   *  @param figurenum die nummer der Spielfigur
   *  @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *  @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  /** Lässt den Spieler Links hochlaufen und überprüft diesen Zug.
   *
   *  @param spielbrett das Alte Spielbrett
   *  @param player der momentane Spieler des Zuges
   *  @param currentCoord die momentanen Koordinaten der Spielfigur
   *  @param figurenum die nummer der Spielfigur
   *  @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *  @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  /** Lässt den Spieler Rechts hochlaufen und überprüft diesen Zug.
   *
   *  @param spielbrett das Alte Spielbrett
   *  @param player der momentane Spieler des Zuges
   *  @param currentCoord die momentanen Koordinaten der Spielfigur
   *  @param figurenum die nummer der Spielfigur
   *  @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *  @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface)

  /** Wirft den Würfel.
   *
   *  @return Zahl die gewürfelt wurde
   */
  def diceRoll: Int


  /** Ändert die Koordinaten eines Tupels um einen Wert nach unten
   *
   *  @param oldcord Tupel der X/Y Koordinaten
   *
   *  @return neues Tupel der X/Y Koordinaten
   */
  def goDown(oldcord: (Int, Int)): (Int, Int)

  /** Ändert die Koordinaten eines Tupels um einen Wert nach oben
   *
   *  @param oldcord Tupel der X/Y Koordinaten
   *
   *  @return neues Tupel der X/Y Koordinaten
   */
  def goUp(oldcord: (Int, Int)): (Int, Int)

  /** Ändert die Koordinaten eines Tupels um einen Wert nach rechts
   *
   *  @param oldcord Tupel der X/Y Koordinaten
   *
   *  @return neues Tupel der X/Y Koordinaten
   */
  def goRight(oldcord: (Int, Int)): (Int, Int)

  /** Ändert die Koordinaten eines Tupels um einen Wert nach links
   *
   *  @param oldcord Tupel der X/Y Koordinaten
   *
   *  @return neues Tupel der X/Y Koordinaten
   */
  def goLeft(oldcord: (Int, Int)): (Int, Int)

  /** Nimmt einen String Wert und gibt die Zelle zurück.
   *
   *  @param name Name der Zelle
   *
   *  @return ausgewählte Zelle
   */
  def getCell(name:String): Cell

  /** Überprüft ob ein Spieler im Ziel steht
   *
   *  @return boolean, ob Spieler im Ziel ist oder nicht
   */
  def checkPlayerOnGoal: Boolean

  /** Gibt den String-Namen der Zelle von den Koordinaten des Spielbrettes wieder
   *
   *  @param row X Koordinate der Zelle
   *  @param col Y Koordinate Zelle
   *
   *  @return String Namen der Zelle
   */
  def cellString(row: Int, col: Int): String

  /** Bekomme den String namen direkt von der Zelle

   *  @param cell eine Zelle
   *
   *  @return String Namen der Zelle
   */
  def getStringOfCell(cell:Cell): String

  /** Bekomme die Koordinaten der Base des 1. Spielers
   *
   *  @return Int Tupel X/Y aus den Koordinaten der Base des 1. Spielers
   */
  def getP1Base: (Int,Int)

  /** Bekomme die Koordinaten der Base des 2. Spielers
   *
   *  @return Int Tupel X/Y aus den Koordinaten der Base des 2. Spielers
   */
  def getP2Base: (Int,Int)

  /** Bekomme die Koordinaten der Base des 3. Spielers
   *
   *  @return Int Tupel X/Y aus den Koordinaten der Base des 3. Spielers
   */
  def getP3Base: (Int,Int)

  /** Bekomme die Koordinaten der Base des 4. Spielers
   *
   *  @return Int Tupel X/Y aus den Koordinaten der Base des 4. Spielers
   */
  def getP4Base: (Int,Int)

  /** Bekomme die Koordinaten des Ziels
   *
   *  @return Int Tupel X/Y aus den Koordinaten des Zieles
   */
  def getGoalBase: (Int,Int)
}

/** Interface für den zuletzt gezogenen Zug des Spielers.
 *
 *  @author sehirsig & franzgajewski
 */
trait lastSaveInterface {

  /** Zuletzt gewürfelte Augenzahl
   *
   *  @return uletzt gewürfelte Augenzahl in Int
   */
  val lastFullDice: Int

  /** Zuletzt gelaufene Richtung
   *
   *  @return in "w", "s", "a" oder "d"
   */
  val lastDirectionOpposite: String

  /** Stelle, auf die die Figur zuletzt stand
   *
   *  @return Zelle
   */
  val lastCell: Cell

  /** Zuletzt gewürfelte Augenzahl aktualisieren
   *
   *  @param newNum neue Augenzahl
   *  @return das neue Interface
   */
  def updateLastFullDice(newNum: Int): lastSaveInterface

  /** Zuletzt gelaufene Richtung aktualisieren
   *
   *  @param newDic neue Richtung
   *  @return das neue Interface
   */
  def updateLastDirection(newDic: String): lastSaveInterface

  /** Stelle, auf die die Figur zuletzt stand aktualisieren
   *
   *  @param newCel neue Zelle
   *  @return das neue Interface
   */
  def updatelastCell(newCel: Cell): lastSaveInterface
}
