name := """BruteHack"""

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  "postgresql" % "postgresql" % "9.3-1101-jdbc41",
  cache,
  ws
)
