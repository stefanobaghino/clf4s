name := """clf4s"""

version := "0.0.1"

organization := "me.baghino"

scalaVersion := "2.11.6"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "com.storm-enroute" %% "scalameter" % "0.6" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test")

val scalaMeterFramework = new TestFramework("org.scalameter.ScalaMeterFramework")

testFrameworks in ThisBuild += scalaMeterFramework

testOptions in ThisBuild += Tests.Argument(scalaMeterFramework, "-silent")

logBuffered := false

parallelExecution in Test := false
