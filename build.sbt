name := """clf4s"""

version := "0.0.2"

organization := "me.baghino"

crossScalaVersions := Seq("2.10.5", "2.11.7")

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")

initialCommands in console := """
  |import clf4s._
  |""".stripMargin
