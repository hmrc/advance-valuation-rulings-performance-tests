lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "advance-valuation-rulings-performance-tests",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.13",
    scalacOptions += "-feature",
    // Enabling sbt-auto-build plugin provides DefaultBuildSettings with default `testOptions` from `sbt-settings` plugin.
    // These testOptions are not compatible with `sbt gatling:test`. So we have to override testOptions here.
    Test / testOptions := Seq.empty,
    libraryDependencies ++= Dependencies()
  )

addCommandAlias("scalafmtAll", "all scalafmtSbt scalafmt Test/scalafmt")
addCommandAlias("scalastyleAll", "all scalastyle Test/scalastyle")
