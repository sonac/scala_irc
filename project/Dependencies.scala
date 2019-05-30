import sbt._

object Dependencies {

  lazy val catsEffectV = "1.3.0"
  lazy val fsV = "0.10.7"

  lazy val castEffect = "org.typelevel" %% "cats-effect" % catsEffectV
  lazy val fs = "co.fs2" %% "fs2-core" % fsV

  lazy val dependencies: Seq[ModuleID] = Seq(
    castEffect,
    fs
  )
}
