package de.htwg.se.malefiz.controller.controllerComponent

import de.htwg.se.malefiz.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.malefiz.model.cellComponent.Cell
import de.htwg.se.malefiz.model.gameComponent.Game
import de.htwg.se.malefiz.model.gameboardComponent.{GameboardInterface, lastSaveInterface}
import de.htwg.se.malefiz.model.playerComponent._

import scala.swing.Publisher

/** Eine Stub Implementation unseres Controllers.
 *  Beschreibungen der Funktionen in der Base-Implementierung.
 *
 *  @author sehirsig & franzgajewski
 */
trait ControllerInterface extends Publisher {

  /** Das Spielbrett */
  var gameboard: GameboardInterface

  /** Der Spielstatus für die Ausgabe */
  var gameStatus: GameStatus

  /** Das Spieler "State Pattern" um den nächsten Spieler auszuwählen */
  var playerStatus: PlayerState

  /** Der Zugzähler, um die verbleibenden Züge zu zählen. */
  var moveCounter: Int

  /** Der Spieler-Bauer mit dem "Builder-Pattern" */
  val builder: PlayerBuilder

  /** Speicherung der Spieler, sowie Spielfiguren */
  var game: Game

  /** Speicherung ob es einen Gewinner gibt und wen. */
  var gameWon: (Boolean, String)

  /** Speicherung des letzten Spielzuges*/
  var savedGame: lastSaveInterface

  /** Nummer der ausgewählten Figur des momentanen Zuges */
  var selectedFigNum: Int

  /** Gibt die Zelle aus einem spezifischem String wieder
   *
   *  @param name Echter Name der Zelle
   *  @return die echte Zelle
   */
  def getpureCell(name: String): Cell

  /** Stellt das spiel von neu wieder her */
  def resetGame(): Unit

  /** Wählt eine der 5 Spielfiguren aus
   *
   *  @param x Zahl von [1-5]
   */
  def selectFigure(x: Int): Unit

  /** Fügt einen Spieler hinzu */
  def addPlayer(): Unit

  /** Fügt einen Spieler mit Name hinzu */
  def addPlayerName(name: String): Unit

  /** Startet das Spiel */
  def startGame(): Unit

  /** Initialisiert das Spiel */
  def setupGame(): Unit

  /** String Darstellung des Spielbrettes
   *
   * @return Spielbrett-Stringdarstellung
   */
  def boardToString(): String

  /** Den Würfel werfen.
   *
   * @return gewürfelte Zahl
   */
  def rollDice(): Int

  /** Überprüfen ob es einen Gewinner gibt */
  def checkWin(): Unit

  /** Wählt die Strategie der Blockaden aus (Strategy-Pattern)
   *
   *  @param blockStrategy "remove" oder "replace"
   */
  def setBlockStrategy(blockStrategy: String): Unit

  /** Bewegt die Spielfigur auf dem Spielbrett
   *
   *  @param input Bewegungsrichtung
   *  @param figurenum Nummer der Spielfigur
   */
  def move(input: String, figurenum: Int): Unit

  /** Löscht den kompletten Undo-Manager */
  def emptyMan: Unit

  /** Undo'd den kompletten Undo-Manager */
  def undoAll: Unit

  /** Ein undo im Undo-Manager */
  def undo: Unit

  /** Ein redo im Undo-Manager */
  def redo: Unit

  /** Speichere den momentanen Spielstand */
  def save: Unit

  /** Lade den momentanen Spielstand */
  def load: Unit

  /** Bewegt die Spielfigur auf dem Spielbrett
   *
   *  @param name Name des Debug Spielers
   */
  def addPlayerDEBUGWINTEST(name: String): Unit

  /** Würfel, der nur 1 würfelt */
  def debugDice(): Unit
}


/** Unsere Events, die beim Controller aufgeruft werden, um den Listenern bescheid zu geben. */
import scala.swing.event.Event

class RollDice extends Event
class ChooseFig extends Event
class Moving extends Event
class SettingUp extends Event
class StartUp extends Event
class StartGame extends Event
class WonGame extends Event
class GameReset extends Event
class GameSaved extends Event
class GameLoaded extends Event