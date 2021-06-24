package de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl

import com.google.inject.Inject
import de.htwg.se.malefiz.model.cellComponent._
import de.htwg.se.malefiz.model.gameboardComponent.GameboardInterface
import de.htwg.se.malefiz.model.playerComponent.Player
import de.htwg.se.malefiz.util.BlockStrategy

import scala.util.{Failure, Random, Success, Try}

/** Base Implementierung unseres Spielbretts.
 *
 *  @author sehirsig & franzgajewski
 */
case class Gameboard(rows: Vector[Vector[Cell]]) extends GameboardInterface {

  /** Ersetzen aller Zellen, je nachdem was in Settings angegeben wurde. */
  @Inject
  def this(sizex: Int, sizey: Int) = this(Vector.tabulate(sizex, sizey) {
    (row, col) => {
      if (Settings().blockedCells.contains(row, col)) {
        BlockedCell
      } else if (Settings().freeCells.contains(row, col)) {
        FreeCell
      } else if (Settings().secureCells.contains(row, col)) {
        SecureCell
      } else if (Settings().goalCell == (row, col)) {
        GoalCell
      } else if (Settings().start1.contains(row, col)) {
        Start1Cell
      } else if (Settings().start2.contains(row, col)) {
        Start2Cell
      } else if (Settings().start3.contains(row, col)) {
        Start3Cell
      } else if (Settings().start4.contains(row, col)) {
        Start4Cell
      } else {
        InvalidCell
      }
    }
  })

  /** Initialisierung der Block-Strategy. */
  var blockStrategy: BlockStrategy = BlockReplaceStrategy()

  /** Block-Strategy ändern. */
  override def setBlockStrategy(blockstrategy: String): Unit = {
    blockstrategy match {
      case "remove" => this.blockStrategy = BlockRemoveStrategy()
      case "replace" => this.blockStrategy = BlockReplaceStrategy()
    }
  }

  /** Block-Strategy Funktion. */
  override def replaceBlocks(spielbrett: GameboardInterface): GameboardInterface = {
    blockStrategy.replaceBlock(spielbrett)
  }

  /** Neues Spielbrett ausgeben, mit der Settings-Standard Größe */
  override def newGBStandardSize: Gameboard = {
    new Gameboard(Settings().xDim, Settings().yDim)
  }

  /** Standardgröße aus den Settings.
   *
   *   @return Tupel der Standard X,Y Größe des Spielbrettes
   */
  override def getStandardXYsize: (Int,Int) = {
    (Settings().xDim, Settings().yDim)
  }

  /** Bekomme den String-Namen der Zelle (z.B. für XML / JSON)
   *
   * @param cell eine Zelle
   *
   *   @return String Namen der Zelle
   */
  override def getStringOfCell(cell:Cell): String = {
    cell match {
      case FreeCell => "FreeCell"
      case BlockedCell => "BlockedCell"
      case Start1Cell => "Start1Cell"
      case Start2Cell =>"Start2Cell"
      case Start3Cell =>"Start3Cell"
      case Start4Cell =>"Start4Cell"
      case SecureCell =>"SecureCell"
      case GoalCell =>"GoalCell"
      case InvalidCell =>"InvalidCell"
      case PlayerCell(1) =>"PlayerCell1"
      case PlayerCell(2) =>"PlayerCell2"
      case PlayerCell(3) =>"PlayerCell3"
      case PlayerCell(4) =>"PlayerCell4"
      case _ => "InvalidCell"
    }
  }

  /** Bekomme eine Zelle aus X,Y Koordinaten von dem Spielbrett
   *
   * @param row X-Koordinate von der Matrix
   * @param col Y-Koordinate von der Matrix
   *   @return Die Zelle von der Koordinate
   */
  override def cell(row: Int, col: Int): Cell = rows(row)(col)


  /** Bekomme eine Zelle in der String-Variante aus X,Y Koordinaten von dem Spielbrett
   *
   * @param row X Koordinate der Zelle
   * @param col Y Koordinate Zelle
   *
   *   @return String Namen der Zelle
   */
  override def cellString(row: Int, col: Int): String = getStringOfCell(rows(row)(col)) //Bekomme die Stringdarstellung aus den Koordinaten.


  /** Ändere eine Zelle im Spielbrett. Gibt einen Fehler, wenn es ausserhalb des Indexes ist.
   * "Try-Monade"
   *
   * @param row X-Koordinate von der Matrix
   * @param col Y-Koordinate von der Matrix
   * @param cell Die zu ersetzende Zelle
   *   @return das neue Spielbrett
   */
  override def replaceCell(row: Int, col: Int, cell: Cell): Try[Gameboard] = { //Eine Zelle ersetzen mit Fehlerbehandlung. Try-Monade. Für Falsche Indexierung.
    val tmp = Try(copy(rows.updated(row, rows(row).updated(col, cell))))
    tmp match {
      case Success(v) => Success(v)
      case Failure(e) => Failure(e)
    }
  }

  /** Bewege einen Spieler.
   *
   * @param coord Koordinate als Int-Tupel von der Matrix
   * @param cell Die zu ersetzende Zelle
   *   @return das neue Spielbrett
   */
  override def movePlayer(coord: (Int, Int), cell: Cell): Gameboard = { //Einen Spieler auf dem Spielbrett bewegen.
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  /** Bewege eine Zelle
   *
   * @param coord
   * @param cell
   * @return das neue Spielbrett
   */
  def moveCell(coord: (Int, Int), cell: Cell): Gameboard = { //Eine Zelle auf dem Spielbrett bewegen.
    copy(rows.updated(coord._1, rows(coord._1).updated(coord._2, cell)))
  }

  /** Überprüfen und laufen, falls man in diese Richtung laufen kann.
   *
   * @param spielbrett das Alte Spielbrett
   * @param player der momentane Spieler des Zuges
   * @param currentCoord die momentanen Koordinaten der Spielfigur
   * @param figurenum die nummer der Spielfigur
   * @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *   @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  override def walkUp(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkUp(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Überprüfen und laufen, falls man in diese Richtung laufen kann.
   *
   * @param spielbrett das Alte Spielbrett
   * @param player der momentane Spieler des Zuges
   * @param currentCoord die momentanen Koordinaten der Spielfigur
   * @param figurenum die nummer der Spielfigur
   * @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *   @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  override def walkDown(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkDown(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Überprüfen und laufen, falls man in diese Richtung laufen kann.
   *
   * @param spielbrett das Alte Spielbrett
   * @param player der momentane Spieler des Zuges
   * @param currentCoord die momentanen Koordinaten der Spielfigur
   * @param figurenum die nummer der Spielfigur
   * @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *   @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  override def walkLeft(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkLeft(spielbrett, player, currentCoord, figurenum, walksLeft)
  }

  /** Überprüfen und laufen, falls man in diese Richtung laufen kann.
   *
   * @param spielbrett das Alte Spielbrett
   * @param player der momentane Spieler des Zuges
   * @param currentCoord die momentanen Koordinaten der Spielfigur
   * @param figurenum die nummer der Spielfigur
   * @param walksLeft Anzahl Züge, die die Spielfigur noch nehmen darf
   *
   *   @return boolean, ob der Zug geklappt hat und das neue Spielbrett
   */
  override def walkRight(spielbrett: GameboardInterface, player: Player, currentCoord: (Int, Int), figurenum: Int, walksLeft: Int): (Boolean, GameboardInterface) = {
    checkCell.walkRight(spielbrett, player, currentCoord, figurenum, walksLeft)
  }


  /** Den Würfel werfen
   *
   *   @return Zahl die gewürfelt wurde
   */
  override def diceRoll: Int = {
    Dice.diceRoll
  }


  /** Koordinate Unten berechnen aus aktueller Position
   *
   * @param oldcord Tupel der X/Y Koordinaten
   *
   *   @return neues Tupel der X/Y Koordinaten
   */
  override def goDown(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goDown(oldcord)
  }

  /** Koordinate Oben berechnen aus aktueller Position
   *
   * @param oldcord Tupel der X/Y Koordinaten
   *
   *   @return neues Tupel der X/Y Koordinaten
   */
  override def goUp(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goUp(oldcord)
  }

  /** Koordinate Rechts berechnen aus aktueller Position
   *
   * @param oldcord Tupel der X/Y Koordinaten
   *
   *   @return neues Tupel der X/Y Koordinaten
   */
  override def goRight(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goRight(oldcord)
  }

  /** Koordinate Links berechnen aus aktueller Position
   *
   * @param oldcord Tupel der X/Y Koordinaten
   *
   *   @return neues Tupel der X/Y Koordinaten
   */
  override def goLeft(oldcord: (Int, Int)): (Int, Int) = {
    moveTypes.goLeft(oldcord)
  }

  /** Bekomme die Zelle aus deren String-Variante (z.B. für XML / JSON)
   *
   * @param name Name der Zelle
   *
   *   @return ausgewählte Zelle
   */
  override def getCell(name: String): Cell = {
    name match {
      case "FreeCell" => FreeCell
      case "BlockedCell" => BlockedCell
      case "Start1Cell" => Start1Cell
      case "Start2Cell" => Start2Cell
      case "Start3Cell" => Start3Cell
      case "Start4Cell" => Start4Cell
      case "SecureCell" => SecureCell
      case "GoalCell" => GoalCell
      case "InvalidCell" => InvalidCell
      case "PlayerCell1" => PlayerCell(1)
      case "PlayerCell2" => PlayerCell(2)
      case "PlayerCell3" => PlayerCell(3)
      case "PlayerCell4" => PlayerCell(4)
    }
  }

  /** Überprüfen, ob ein Spieler im Ziel ist.
   *
   *   @return boolean, ob Spieler im Ziel ist oder nicht
   */
  def checkPlayerOnGoal: Boolean = {
    cell(Settings().goalCell._1, Settings().goalCell._2).isInstanceOf[PlayerCell]
  }


  /** Koordinaten bekommen.
   *
   *   @return Int Tupel X/Y aus den Koordinaten der Base des 1. Spielers
   */
  override def getP1Base: (Int,Int) = {
    Settings().start1.head
  }

  /** Koordinaten bekommen.
   *
   *   @return Int Tupel X/Y aus den Koordinaten der Base des 2. Spielers
   */
  override def getP2Base: (Int,Int) = {
    Settings().start2.head
  }

  /** Koordinaten bekommen.
   *
   *   @return Int Tupel X/Y aus den Koordinaten der Base des 3. Spielers
   */
  override def getP3Base: (Int,Int) = {
    Settings().start3.head
  }

  /** Koordinaten bekommen.
   *
   *   @return Int Tupel X/Y aus den Koordinaten der Base des 4. Spielers
   */
  override def getP4Base: (Int,Int) = {
    Settings().start4.head
  }

  /** Koordinaten bekommen.
   *
   *   @return Int Tupel X/Y aus den Koordinaten des Zieles
   */
  override def getGoalBase: (Int,Int) = {
    Settings().goalCell
  }

  /** String-Variante des Spielbrettes
   *
   * @return Spielbrett als String
   */
  override def toString: String = {
    val buf = new StringBuilder
    rows.foreach(x => buf ++= x.mkString + "\n")
    buf.toString
  }
}
