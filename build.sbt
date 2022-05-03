ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "MyScalaMongoProject"
  )

// https://mvnrepository.com/artifact/org.mongodb.scala/mongo-scala-driver
libraryDependencies ++= Seq(
"com.typesafe" % "config" % "1.4.2",
  "com.softwaremill.sttp" %% "core" % "1.7.2",



"com.typesafe.akka" %% "akka-slf4j" % "2.5.26",
  "com.typesafe.akka" %% "akka-actor" % "2.5.26",
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe.akka" %% "akka-stream" % "2.5.26",
  "ch.rasc" % "bsoncodec" % "1.0.1",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
  "com.google.code.gson" % "gson" % "2.9.0"




)
