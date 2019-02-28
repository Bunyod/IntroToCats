name := "IntroToCats"

version := "0.1"

scalaVersion := "2.12.8"

val akkaVersion = "2.5.21"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "org.typelevel" %% "cats-core" % "1.5.0",
  "org.scalaz" %% "scalaz-core" % "7.2.27"
)

scalacOptions += "-language:implicitConversions"
scalacOptions += "-Ypartial-unification"
