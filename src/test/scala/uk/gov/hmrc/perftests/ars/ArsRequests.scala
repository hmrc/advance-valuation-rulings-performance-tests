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
import uk.gov.hmrc.perftests.ars.Requests._

object ArsRequests extends ServicesConfiguration {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  val authUrl: String = baseUrlFor("auth-login-stub")
  val arsUrl: String  = baseUrlFor("ars-frontend")

  private val baseUrl        = arsUrl
  private val urlWithDraftId = s"$baseUrl/advance-valuation-ruling/$${draftId}"

  def navigateToAccountHome =
    getPage(
      "account home",
      true,
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings"
    )

  def startNewApp =
    postPageAndExtractDraftId(
      "Start new app",
      postToken = true,
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings",
      s"describe-role-importer",
      Map.empty[String, String]
    )

  def navigateToSelectYourRolePage =
    getPage(
      "Select you role as an importer",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain"
    )

  def selectARole(SelectARole: Boolean) = {
    val selectMethod = Map(
      "value" -> "agentOnBehalfOfTrader"
    )
    postPage(
      "select a role",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/describe-role-importer",
      if (SelectARole) selectMethod else Map.empty[String, String]
    )
  }

  def submitStarterChecklist =
    getPage(
      "starter checklist",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/start-application"
    )

  def navigateToPlanningToImportGoods =
    getPage(
      "Are you planning to import goods to the UK",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain"
    )

  def submitPlanningToImportGoods(answer: Boolean) =
    postPage(
      "Are you planning to import goods to the UK",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain",
      answer.toPayload
    )

  def navigateToContactAboutYourApp =
    getPage(
      "Contact about your app",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/need-to-contact-you"
    )

  def navigateToEnterTradersEori =
    getPage(
      "Enter trader's EORI number page",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-trader-eori"
    )

  def submitTradersEori(EoriNo: Boolean) = {
    val enterEori = Map(
      "value" -> "GB112888888040"
    )
    postPage(
      "enter traders EORI number",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-trader-eori?saveDraft=false",
      if (EoriNo) enterEori else Map.empty[String, String]
    )
  }

  def navigateToCheckRegisteredDetails() =
    getPage(
      "Check registered details",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/check-EORI-details?csrfToken=" + "${csrfToken}"
    )

  def navigateToCheckTraderEoriDetails() =
    getPage(
      "Check trader EORI details",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/check-trader-EORI-details"
    )

  def navigateToProvideContactDetails() =
    getPage(
      "Enter Contact details",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-contact-details"
    )

  def navigateToProvideBusinessContactDetails() =
    getPage(
      "Enter Business Contact details",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/business-contact-details"
    )

  def submitProvideContactDetails() = {

    val enterTextAllBoxes = Map(
      "name"  -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678"
    )

    postPage(
      "Provide Contact Details",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-contact-details",
      enterTextAllBoxes
    )
  }

  def submitBusinessContactDetails() = {

    val enterTextAllBoxes = Map(
      "name"         -> "test",
      "email"        -> "test@gmail.com",
      "phone"        -> "12345678",
      "company name" -> "test Inc"
    )

    postPage(
      "Provide Business Contact Details",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-contact-details?saveDraft=false",
      enterTextAllBoxes
    )
  }

  def navigateToMethodNamePage() =
    getPage(
      "Method Name Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/select-valuation-method"
    )

  def selectMethod4(selectAnyOneMethod: Boolean) = {
    val selectMethod = Map(
      "value" -> "method4"
    )
    postPage(
      "selectMethod",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/select-valuation-method",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1to3Page =
    getPage(
      "Why Not Selected Method 1-3 Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/explain-why-not-methods-1-3"
    )

  def enterReasonNotSelectedMethod1(enterReason: Boolean) = {
    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1-3",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/explain-why-not-methods-1-3?saveDraft=false",
      if (enterReason) enterText else Map.empty[String, String]
    )
  }

  def enterReasonWhySelectedMethod4(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for selecting Method 4",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/explain-why-method-4?saveDraft=false",
      enterText
    )
  }

  def navigateNameOfTheGoodsPage =
    getPage(
      "Name Of Short description of goods",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/give-short-description-goods"
    )
  def enterNameofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter short description of goods",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/give-short-description-goods?saveDraft=false",
      enterText
    )
  }

  def navigateFoundCommodityCodePage =
    getPage(
      "Found Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/do-you-have-commodity-code"
    )

  def submitFoundCommodityCode(answer: Boolean) =
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/do-you-have-commodity-code",
      answer.toPayload
    )

  def navigateCommodityCodePage =
    getPage(
      "Navigate Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/enter-commodity-code"
    )
  def enterCommodityCode(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "1234"
    )

    postPage(
      "Enter Commodity Code",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/enter-commodity-code?saveDraft=false",
      enterText
    )
  }

  def navigateLegalChallengePage =
    getPage(
      "Found Legal challenges Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/any-legal-challenges"
    )

  def submityesOrNolChallengePage(answer: Boolean) =
    postPage(
      "Click Yes or No in  Legal challenges Page",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/any-legal-challenges",
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
      s"$baseUrl/advance-valuation-ruling/$${draftId}/add-confidential-information"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean) = postPage(
    "Select Yes in Confidential Page",
    s"$baseUrl/advance-valuation-ruling/$${draftId}/add-confidential-information",
    answer.toPayload
  )

  def navigateToEnterConfidentialInfoPage =
    getPage(
      "Navigate To Confidential  info Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-confidential-information"
    )

  def submitTheConfidentialInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "enter The Goods Confidential Info",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/provide-confidential-information?saveDraft=false",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      true,
      s"$baseUrl/advance-valuation-ruling/$${draftId}/add-supporting-documents"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean) = postPage(
    "Select No Upload Supporting Docs Page",
    s"$baseUrl/advance-valuation-ruling/$${draftId}/add-supporting-documents",
    answer.toPayload
  )

  def navigateToCheckYourAnswerPage =
    getPage(
      "Navigate To check Your Answers Page",
      s"$baseUrl/advance-valuation-ruling/$${draftId}/check-your-answers"
    )
}
