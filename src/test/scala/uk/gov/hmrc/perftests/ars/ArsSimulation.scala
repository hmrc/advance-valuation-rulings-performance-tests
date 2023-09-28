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

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.ars.ArsRequests._
import uk.gov.hmrc.perftests.ars.AuthRequests._

class ArsSimulation extends PerformanceTestRunner {

  val fullJourney: Seq[HttpRequestBuilder] =
    Seq(
      navigateToAuthWizard,
      submitAuthWizard.disableFollowRedirect,
      navigateToAccountHome,
      startNewApp,
      navigateToSelectYourRolePage,
      selectARole(true),
      submitStarterChecklist,
      navigateToPlanningToImportGoods,
      submitPlanningToImportGoods(true),
      navigateToContactAboutYourApp,
      navigateToEnterTradersEori,
      submitTradersEori(true),
      navigateToCheckTraderEoriDetails(),
      navigateToProvideBusinessContactDetails(),
      submitBusinessContactDetails(),
      navigateToMethodNamePage(),
      selectMethod4(true),
      navigateWhyNotSelectedMethod1to3Page,
      enterReasonNotSelectedMethod1(true),
      enterReasonWhySelectedMethod4(true),
      navigateNameOfTheGoodsPage,
      enterNameofTheGoods(true),
      navigateFoundCommodityCodePage,
      submitFoundCommodityCode(true),
      navigateCommodityCodePage,
      enterCommodityCode(true),
      navigateLegalChallengePage,
      submityesOrNolChallengePage(true),
      navigateToConfidentialInfoPage,
      submitYesInConfidentialInfoPage(true),
      navigateToEnterConfidentialInfoPage,
      submitTheConfidentialInfo(true),
      navigateToUploadSupportingDocsPage,
      submitNoInUploadSupportingDocsPage(false)
    )

  setup("full-journey", "Method 1")
    .withRequests(fullJourney: _*)

  runSimulation()

}
