import sbt.*

object Dependencies {

  private val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "performance-test-runner" % "6.1.0"
  ).map(_ % Test)

  def apply(): Seq[ModuleID]      = test

}
