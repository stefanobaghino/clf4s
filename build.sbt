name := """clf4s"""

version := "0.0.2"

organization := "me.baghino"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("joda-time" % "joda-time" % "2.7",
                            "org.scalatest" %% "scalatest" % "2.2.4" % "test")

compileInputs in (Test, compile) <<=
  (compileInputs in (Test, compile)) dependsOn (scalafmtTest in Test)

initialCommands in console := """
  |import clf4s._
  |""".stripMargin
