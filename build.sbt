name          := "malefiz"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.13.10"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "com.google.inject" % "guice" % "5.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "5.0.1"
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.3"

coverageExcludedPackages := "de.htwg.se.malefiz.aview.GUI;" +
  "de.htwg.se.malefiz.model.gameboardComponent.gameboardBaseImpl.Dice;" +
  "de.htwg.se.malefiz.model.fileIoComponent.*;"



//*******************************************************************************//
//Libraries that we will use in later lectures compatible with this scala version
// uncomment to use!!