import sbt.*

object Dependencies {

  private val gatlingVersion = "3.6.1"

  private val test: Seq[ModuleID] = Seq(
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
    "uk.gov.hmrc"          %% "performance-test-runner"   % "5.6.0"
  ).map(_ % Test)

  def apply(): Seq[ModuleID]      = test
}
