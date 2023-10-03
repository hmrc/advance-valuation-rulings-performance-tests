import sbt._

object Dependencies {

  val gatlingVersion = "3.6.1"

  val test = Seq(
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
    "uk.gov.hmrc"          %% "performance-test-runner"   % "5.6.0"        % Test,
    "com.typesafe"          % "config"                    % "1.4.2"        % Test
  )
}
