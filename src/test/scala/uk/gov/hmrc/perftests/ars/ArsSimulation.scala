/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.ars
import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.ars.AuthRequests._

import scala.util.Random

class ArsSimulation extends Simulation with ServicesConfiguration with ArsRequests {

  private val random            = new Random
  private val currentTimeFeeder = Iterator.continually(Map("currentTime" -> System.currentTimeMillis().toString))
  private val randomFeeder      = Iterator.continually(Map("random" -> Math.abs(random.nextInt())))

  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("image/png,image/*;q=0.8,*/*;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-gb,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0")
    .header("True-Client-IP", "${random}")

  val scn = scenario("registration-login")
    .feed(currentTimeFeeder)
    .feed(randomFeeder)
    .exec(navigateToAuthWizard)
    .exec(submitAuthWizard.disableFollowRedirect)
    .exec(navigateToAccountHome)
    .exec(startNewApp)
    .exec(navigateToSelectYourRolePage)
    .exec(selectARole(true))
    .exec(submitStarterChecklist)
    .exec(navigateToPlanningToImportGoods)
    .exec(submitPlanningToImportGoods(true))
    .exec(navigateToContactAboutYourApp)
    .exec(navigateToEnterTradersEori)
    .exec(submitTradersEori(true))
    .exec(navigateToCheckTraderEoriDetails())
    .exec(navigateToProvideBusinessContactDetails())
    .exec(submitBusinessContactDetails())
    .exec(navigateToMethodNamePage())
    .exec(selectMethod4(true))
    .exec(navigateWhyNotSelectedMethod1to3Page)
    .exec(enterReasonNotSelectedMethod1(true))
    .exec(enterReasonWhySelectedMethod4(true))
    .exec(navigateNameOfTheGoodsPage)
    .exec(enterNameofTheGoods(true))
    .exec(navigateFoundCommodityCodePage)
    .exec(submitFoundCommodityCode(true))
    .exec(navigateCommodityCodePage)
    .exec(enterCommodityCode(true))
    .exec(navigateLegalChallengePage)
    .exec(submityesOrNolChallengePage(true))
    .exec(navigateToConfidentialInfoPage)
    .exec(submitYesInConfidentialInfoPage(true))
    .exec(navigateToEnterConfidentialInfoPage)
    .exec(submitTheConfidentialInfo(true))
    .exec(navigateToUploadSupportingDocsPage)
    .exec(submitNoInUploadSupportingDocsPage(false))

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)

}
