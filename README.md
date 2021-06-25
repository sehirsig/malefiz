# HTWG Constance - AIN 3 - Software Engineering
# Scala Project - Malefiz Game
## Game Project for the lecture SE

![gamepicture](https://user-images.githubusercontent.com/81407658/114448533-f96ce480-9bd3-11eb-93a7-74dc0941f6c1.jpg)


# Game Rules
Malefiz is a board game for 2 to 4 players. Each player has 5 figures to play with. Every figure starts in their base, at the bottom of the board. The beginning player rolls the dice. The thrown number has to be pulled completly. While walking with the figure, change of direction is not allowed. If a player lands on an enemys figure, the enemy has to put his figure back to his base. Figures are allowed to jump over other figures, but not over barricades. If a player lands on the barricade (with the exact number), he has to set the barricade anywhere on the game board, all black fields are allowed, except the lowest row. The player reaching the top of the gameboard first (with the exact number rolled with the dice) with one figure wins the game.

# How This Game Works

 1. Start the game!
 2. Add some Players (2-4)!
 3. Roll the dice!
 4. Choose the gamefigure!
 5. Move the gamefigure!
 6. Next players turn!

![enter image description here](https://i.imgur.com/Qu2U0oy.png)![enter image description here](https://i.imgur.com/wHv20JW.png)![enter image description here](https://i.imgur.com/tocdyU4.png)![enter image description here](https://i.imgur.com/cAJQrEF.png)![enter image description here](https://i.imgur.com/6HQrexU.png)![enter image description here](https://i.imgur.com/nHwTkKM.png)

# Procedure Of Making This Game

 1. IntelliJ & Scala
 2. Version Control Systems - Git
 3. ScalaTest
 4. Text-User-Interface (TUI)
 5. MVC Architecture
 6. Travis & Coveralls
 7. Design Patterns
 8. Scala Swing (GUI)
 9. Interfaces & Components
 10. Dependency Injection
 11. File IO (XML & JSON)
 12. Docker 
 13. Documentation


# Used Design Patterns
* Builder-Pattern *[PlayerBuilder](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/playerComponent/PlayerBuilder.scala)*
* State-Pattern [*TUI State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/aview/TUIState.scala)  [*Player State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/controller/controllerComponent/PlayerState.scala)
* Try-Monade [*replaceCell()*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/gameboardComponent/gameboardBaseImpl/Gameboard.scala#L124)
* Option-Monade [*Dice*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/gameboardComponent/gameboardBaseImpl/Dice.scala)
* Strategy-Pattern [*Block-Strategy*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/util/BlockStrategy.scala)
# Coverage [Master]
[![Build Status](https://travis-ci.org/franzgajewski/malefiz.svg?branch=master&kill_cache=1)](https://travis-ci.org/franzgajewski/malefiz) [![Coverage Status](https://coveralls.io/repos/github/franzgajewski/malefiz/badge.svg?branch=master&kill_cache=1)](https://coveralls.io/github/franzgajewski/malefiz?branch=master&kill_cache=1)

Written by [@sehirsig](https://github.com/sehirsig/) & [@franzgajewski](https://github.com/franzgajewski/)
