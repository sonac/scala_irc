import Dependencies._

lazy val root = (project in file("."))
  .settings(
    organization := "sonac.github.io",
    name := "irc",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.7",
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= dependencies,
    addCompilerPlugin("org.spire-math" %% "kind-projector"     % "0.9.6"),
    addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % "0.2.4")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
)
