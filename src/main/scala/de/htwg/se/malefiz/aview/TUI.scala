package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.model.{Cell, Gameboard}

class TUI
{
  def command(input: String, brett:Gameboard[Cell]):Gameboard[Cell] = {
    input match {
      case "h" => {
        println("Test")
        brett
      }
    }
  }
}
