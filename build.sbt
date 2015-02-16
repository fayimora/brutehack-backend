name := """BruteHack"""

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  jdbc,
  "org.postgresql" % "postgresql" % "9.3-1101-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0-M2",
  "com.github.tminglei" %% "slick-pg" % "0.6.0-M2",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.6.0-M2",
  "com.github.tminglei" %% "slick-pg_jts" % "0.6.0-M2",
  "org.slf4j" % "slf4j-simple" % "1.7.7",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  cache,
  ws
)
