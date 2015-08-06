import sbt._
import sbt.Keys._

object MyBuild extends Build {

  /** This is the main project */
  lazy val root: Project = Project(
    "ildl-example",
    file("."),
    settings = Defaults.defaultSettings ++ Seq[Setting[_]](
      organization := "ch.epfl.lamp",
      version := "0.0.1-SNAPSHOT",
      // The plugin requires the latest version of the 2.11 scalac compiler:
      scalaVersion := "2.11.6"
    ) ++ ildlSettings
  )

  lazy val ildlSettings = Seq[Setting[_]](
    libraryDependencies += "org.scala-miniboxing.plugins" %% "ildl-runtime" % "0.1-M1",
    addCompilerPlugin("org.scala-miniboxing.plugins" %% "ildl-plugin" % "0.1-M1")
  )
}
