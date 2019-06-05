name := "playground_kamon_tracing"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "io.kamon" %% "kamon-core" % "1.1.3",
  "io.kamon" %% "kamon-akka-2.5" % "1.1.2",
  "io.kamon" %% "kamon-prometheus" % "1.1.1",
  "io.kamon" %% "kamon-zipkin" % "1.0.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

lazy val distProject = project.in(file("."))
  .enablePlugins(JavaAgent)
  .settings(
    javaAgents += "org.aspectj" % "aspectjweaver" % "1.9.4"
  )