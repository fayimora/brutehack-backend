name := "BruteHack"
organization := "com.brutehack"
version := "0.0.1"
scalaVersion := "2.11.7"
parallelExecution in ThisBuild := false

maintainer := "Fayimora <fayi@fayimora.com>"
packageSummary := "BruteHack Backend Package"
packageDescription := "The backend for Brutehack"

fork in run := true
fork in Test := true

javaOptions in run ++= Seq("-Dconfig.file=src/main/resources/conf/development.conf")
javaOptions in (Test, test) ++= Seq("-Dconfig.file=src/main/resources/conf/development.conf")

scalacOptions ++= Seq("-deprecation")

val versions = new {
  val finatra = "2.1.1"
  val guice = "4.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Twitter Maven" at "https://maven.twttr.com/",
  DefaultMavenRepository
)

assemblyMergeStrategy in assembly := {
  case "BUILD" => MergeStrategy.discard
  case PathList("javax", "annotation", xs @ _*) => MergeStrategy.last
  case other => MergeStrategy.defaultMergeStrategy(other)
}

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % versions.finatra,// exclude("com.google.inject", "guice"),
  "com.twitter.finatra" %% "finatra-slf4j" % versions.finatra,
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.scalikejdbc" %% "scalikejdbc" % "2.3.3",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.twitter" %% "bijection-util" % "0.8.1",
  // "com.github.racc" % "typesafeconfig-guice" % "0.0.2" exclude("com.google.inject", "guice"),
  "org.reflections" % "reflections" % "0.9.10",
  "com.typesafe" % "config" % "1.3.0",
  "joda-time" % "joda-time" % "2.8.2",
  "com.github.finagle" %% "finagle-oauth2" % "0.1.5",

  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",
  "com.twitter.finatra" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter.finatra" %% "finatra-jackson" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter.inject" %% "inject-modules" % versions.finatra % "test" classifier "tests",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test" classifier "tests",

  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test",
  "com.lihaoyi" % "ammonite-repl" % "0.4.8" % "test" cross CrossVersion.full
)

initialCommands in (Test, console) := """ammonite.repl.Repl.run("")"""

test in assembly := {}

Seq(Revolver.settings: _*)

Seq(flywaySettings: _*)

flywayUrl := "jdbc:postgresql://localhost:5432/brutehack"

flywayUser := "bhdev"

mappings in Universal += {
  // we are using the development.conf as default application.conf
  val conf = (resourceDirectory in Compile).value / "conf/development.conf"
  conf -> "conf/application.conf"
}

// we specify the name for our fat jar
assemblyJarName in assembly := "brutehack.jar"

// removes all jar mappings in universal and appends the fat jar
mappings in Universal <<= (mappings in Universal, assembly in Compile) map { (mappings, fatJar) =>
  val filtered = mappings filter { case (file, name) =>  ! name.endsWith(".jar") }
  filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}

// the bash scripts classpath only needs the fat jar
scriptClasspath := Seq( (jarName in assembly).value )

bashScriptExtraDefines += """addJava "-Dconfig.file=${app_home}/../conf/application.conf""""

enablePlugins(JavaServerAppPackaging, JDebPackaging)
