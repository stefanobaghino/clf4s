name := """clf4s"""

version := "0.0.2"

organization := "me.baghino"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test")

initialCommands in console := """
  |import clf4s._
  |""".stripMargin
