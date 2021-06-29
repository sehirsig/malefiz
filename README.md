# HTWG Constance - AIN 3 - Software Engineering
# Scala Project - Malefiz Game
## Game Project For The Lecture SE

![gamepicture](https://user-images.githubusercontent.com/81407658/114448533-f96ce480-9bd3-11eb-93a7-74dc0941f6c1.jpg)


# Game Rules
Malefiz is a board game for 2 to 4 players. Each player has 5 figures to play with. Every figure starts in their base, at the bottom of the board. The beginning player rolls the dice. The thrown number has to be pulled completly. While walking with the figure, change of direction is not allowed. If a player lands on an enemys figure, the enemy has to put his figure back to his base. Figures are allowed to jump over other figures, but not over barricades. If a player lands on the barricade (with the exact number), he has to set the barricade anywhere on the game board, all black fields are allowed, except the lowest row. The player reaching the top of the gameboard first (with the exact number rolled with the dice) with one figure wins the game.

# How This Game Works
## Open The Game And Add Players (2-4)!
![AddPlayers](https://media1.giphy.com/media/08wXVjeDiLUJRTpbmT/giphy.gif)
## Start The Game!

![StartGame](https://media0.giphy.com/media/TtQEBzPCN0VPwfuYsy/giphy.gif)
## Roll The Dice!
![RollDice](https://media1.giphy.com/media/sOmkUSZknIfG7oPllP/giphy.gif)
## Choose The Gamefigure!
![ChooseGameFig](https://media4.giphy.com/media/jwDEbzvgWz9KhIOI2U/giphy.gif)
## Move The Gamefigure!
![MoveFig](https://media1.giphy.com/media/OIl7bsl0qQZ3ohONdW/giphy.gif)
## Kick A Gamefigure!
![KickFig](https://media0.giphy.com/media/EtHxH94U4jjW3dsG9D/giphy.gif)
## Move A Blockade!
![BlockMove](https://media2.giphy.com/media/PBaipOQTlGIAIwcd6U/giphy.gif)

## Informations
> When the player does a faulty move, the gamefigure gets reset to the starting point of its move (ex. walk into a barrier with one or more moves left.).

> If a player can't do a regular move, the player can press the 'skip' button to skip his move.

# Extra Features
## Save The Game Progress
![SaveGame](https://media3.giphy.com/media/idnf5TMhaVC5So1MSj/giphy.gif)
## Load The Game Progress
![LoadGame](https://media2.giphy.com/media/J7sybAKJ3JTQit5aQS/giphy.gif)

# Procedure Of Making This Game

 1. IntelliJ & Scala
 2. Version Control Systems - Git
 3. Agile Development (ScalaTest)
 4. Continuous Deployment (TravisCI & Coveralls)
 5. MVC Architecture (Model-View-Controller)
 6. TUI (Text-based User Interface)
 7. Design Patterns
 8. GUI (Graphical User Interface)
 9. Components
 10. Dependency Injection
 11. File IO (XML & JSON)
 12. Docker 
 13. Documentation


# Used Design Patterns
* Builder-Pattern *[PlayerBuilder](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/playerComponent/PlayerBuilder.scala)*
* State-Pattern [*TUI State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/aview/TUIState.scala)  [*Player State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/controller/controllerComponent/PlayerState.scala)
* Try-Monade [*replaceCell()*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/gameboardComponent/gameboardBaseImpl/Gameboard.scala#L123)
* Option-Monade [*Dice*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/gameboardComponent/gameboardBaseImpl/Dice.scala)
* Strategy-Pattern [*Block-Strategy*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/util/BlockStrategy.scala)
* Reactor-Events [*Event/Listener/Reactor*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/controller/controllerComponent/ControllerInterface.scala#L131)
* Command-Pattern [*Command*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/util/Command.scala)
* Undo-Manager [*UndoManager*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/util/UndoManager.scala)
# Coverage [Master]
[![Build Status](https://travis-ci.org/franzgajewski/malefiz.svg?branch=master&kill_cache=1)](https://travis-ci.org/franzgajewski/malefiz) [![Coverage Status](https://coveralls.io/repos/github/franzgajewski/malefiz/badge.svg?branch=master&kill_cache=1)](https://coveralls.io/github/franzgajewski/malefiz?branch=master&kill_cache=1)

# Dockerhub
[Docker Hub Link (sehirsig/malefiz)](https://hub.docker.com/r/sehirsig/malefiz)
## GUI Version
> `docker pull sehirsig/malefiz:v1`

## TUI Version
> `docker pull sehirsig/malefiz:tui`


*Written by [@sehirsig](https://github.com/sehirsig/) & [@franzgajewski](https://github.com/franzgajewski/)*
