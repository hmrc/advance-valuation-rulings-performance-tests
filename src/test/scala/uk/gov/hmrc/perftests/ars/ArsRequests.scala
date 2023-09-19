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
import uk.gov.hmrc.perftests.ars.Requests._

trait ArsRequests {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  private val baseUrl = Configuration.arsUrl
  private val urlWithDraftId = s"$baseUrl/advance-valuation-ruling/$${draftId}"

  def navigateToAccountHome =
    getPage(
      "account home",
      true,
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings"
    )

  def startNewApp =
    postPageAndExtractDraftId(
      "start new app",
      postToken = true,
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings",
      s"describe-role-importer",
      Map.empty[String, String]
    )

  def navigateToSelectYourRolePage =
    getPage(
      "Select you role as an importer",
      s"$urlWithDraftId/planning-import-goods-great-britain"
    )

  def selectARole(SelectARole: Boolean) = {
    val selectMethod = Map(
      "value" -> "agentOnBehalfOfTrader"
    )
    postPage(
      "select a role",
      s"$urlWithDraftId/describe-role-importer",
      if (SelectARole) selectMethod else Map.empty[String, String]
    )
  }

  def submitStarterChecklist = {
    getPage(
      "starter checklist",
      s"$urlWithDraftId/start-application"
    )
  }

  def navigateToPlanningToImportGoods =
    getPage(
      "Are you planning to import goods to the UK",
      true,
      s"$urlWithDraftId/planning-import-goods-great-britain"
    )

  def submitPlanningToImportGoods(answer: Boolean) = postPage(
    "Are you planning to import goods to the UK",
    s"$urlWithDraftId/planning-import-goods-great-britain",
    answer.toPayload
  )

  def navigateToContactAboutYourApp =
    getPage(
      "Contact about your app",
      s"$urlWithDraftId/need-to-contact-you"
    )

  def navigateToEnterTradersEori =
    getPage(
      "Enter trader's EORI number page",
      s"$urlWithDraftId/provide-trader-eori"
    )

  def submitTradersEori(EoriNo: Boolean) = {
    val enterEori = Map(
      "value" -> "GB112888888040"
    )
    postPage(
      "enter traders EORI number",
      s"$urlWithDraftId/provide-trader-eori?saveDraft=false",
      if (EoriNo) enterEori else Map.empty[String, String]
    )
  }

  def navigateToCheckRegisteredDetails() =
    getPage(
      "Check registered details",
      true,
      s"$urlWithDraftId/check-EORI-details?csrfToken=" + "${csrfToken}"
    )

  def navigateToCheckTraderEoriDetails() =
    getPage(
      "Check trader EORI details",
      s"$urlWithDraftId/check-trader-EORI-details"
    )

  def navigateToProvideContactDetails() =
    getPage(
      "Enter Contact details",
      true,
      s"$urlWithDraftId/provide-contact-details"
    )

  def navigateToProvideBusinessContactDetails() =
    getPage(
      "Enter Business Contact details",
      true,
      s"$urlWithDraftId/business-contact-details"
    )

  def submitProvideContactDetails() = {

    val enterTextAllBoxes = Map(
      "name"  -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678"
    )

    postPage(
      "Provide Contact Details",
      s"$urlWithDraftId/provide-contact-details",
      enterTextAllBoxes
    )
  }

  def submitBusinessContactDetails() = {

    val enterTextAllBoxes = Map(
      "name" -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678",
      "company name" -> "test Inc"
    )

    postPage(
      "Provide Business Contact Details",
      s"$urlWithDraftId/provide-contact-details?saveDraft=false",
      enterTextAllBoxes
    )
  }

  def navigateToMethodNamePage() =
    getPage(
      "Method Name Page",
      true,
      s"$urlWithDraftId/select-valuation-method"
    )

  def selectMethod4(selectAnyOneMethod: Boolean) = {
    val selectMethod = Map(
      "value" -> "method4"
    )
    postPage(
      "selectMethod",
      s"$urlWithDraftId/select-valuation-method",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1to3Page =
    getPage(
      "Why Not Selected Method 1-3 Page",
      true,
      s"$urlWithDraftId/explain-why-not-methods-1-3"
    )

  def enterReasonNotSelectedMethod1(enterReason: Boolean) = {
    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1-3",
      s"$urlWithDraftId/explain-why-not-methods-1-3?saveDraft=false",
      if (enterReason) enterText else Map.empty[String, String]

    )
  }

  def enterReasonWhySelectedMethod4(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for selecting Method 4",
      s"$urlWithDraftId/explain-why-method-4?saveDraft=false",
      enterText
    )
  }

  def navigateNameOfTheGoodsPage =
    getPage(
      "Name Of Short description of goods",
      true,
      s"$urlWithDraftId/give-short-description-goods"
    )
  def enterNameofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter short description of goods",
      s"$urlWithDraftId/give-short-description-goods?saveDraft=false",
      enterText
    )
  }

  def navigateFoundCommodityCodePage =
    getPage(
      "Found Commodity Code Page",
      true,
      s"$urlWithDraftId/do-you-have-commodity-code"
    )

  def submitFoundCommodityCode(answer: Boolean) =
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"$urlWithDraftId/do-you-have-commodity-code",
      answer.toPayload
    )

  def navigateCommodityCodePage =
    getPage(
      "Navigate Commodity Code Page",
      true,
      s"$urlWithDraftId/enter-commodity-code"
    )
  def enterCommodityCode(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "1234"
    )

    postPage(
      "Enter Commodity Code",
      s"$urlWithDraftId/enter-commodity-code?saveDraft=false",
      enterText
    )
  }

  def navigateLegalChallengePage =
    getPage(
      "Found Legal challenges Page",
      true,
      s"$urlWithDraftId/any-legal-challenges"
    )

  def submityesOrNolChallengePage(answer: Boolean) =
    postPage(
      "Click Yes or No in  Legal challenges Page",
      s"$urlWithDraftId/any-legal-challenges",
      answer.toPayload
    )

  def enterNameofThelegalchallengesGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter the  description of Legal challenges to the goods",
      s"$baseUrl/advance-valuation-ruling/describe-legal-challenges",
      enterText
    )
  }

  def navigateToConfidentialInfoPage =
    getPage(
      "Navigate To Add Confidential Info Page",
      true,
      s"$urlWithDraftId/add-confidential-information"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean) = postPage(
    "Select Yes in Confidential Page",
    s"$urlWithDraftId/add-confidential-information",
    answer.toPayload
  )

  def navigateToEnterConfidentialInfoPage =
    getPage(
      "Navigate To Confidential  info Page",
      true,
      s"$urlWithDraftId/provide-confidential-information"
    )

  def submitTheConfidentialInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "enter The Goods Confidential Info",
      s"$urlWithDraftId/provide-confidential-information?saveDraft=false",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      true,
      s"$urlWithDraftId/add-supporting-documents"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean) = postPage(
    "Select No Upload Supporting Docs Page",
    s"$urlWithDraftId/add-supporting-documents",
    answer.toPayload
  )

  def navigateToCheckYourAnswerPage =
    getPage(
      "Navigate To check Your Answers Page",
      s"$urlWithDraftId/check-your-answers"
    )
}