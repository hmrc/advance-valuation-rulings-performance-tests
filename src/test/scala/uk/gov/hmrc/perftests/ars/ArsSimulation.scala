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

import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.ars.AuthRequests._
import uk.gov.hmrc.perftests.ars.Requests._

class ArsSimulation extends PerformanceTestRunner with ServicesConfiguration with ArsRequests {
  setup(
    "registration-login",
    "Log in to auth"
  ).withRequests(
    navigateToAuthWizard,
    submitAuthWizard
  )

  setup("initial-journey", "Method 4 journey with No Upload")
    .withActions(
      navigateToAccountHome,
      startNewApp,
      submitStarterChecklist(allTicked = true),
      navigateToPlanningToImportGoods,
      navigateToContactAboutYourApp,
      navigateToCheckRegisteredDetails(answer = true),
      navigateToProvideContactDetails(true),
      submitProvideContactDetails(true),
      navigateToMethodNamePage(true),
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
      submitNoInUploadSupportingDocsPage(false),
      navigateToCheckYourAnswerPage,
      postPageFor("/advance-valuation-ruling/check-your-answers")
    )

  runSimulation()
}
