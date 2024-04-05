/*
 * Copyright 2024 HM Revenue & Customs
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
import uk.gov.hmrc.perftests.ars.Requests._

object ArsRequests extends Configuration {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  def navigateToAccountHome: HttpRequestBuilder =
    getPage(
      "account home",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/applications-and-rulings"
    )

  def startNewApp: HttpRequestBuilder =
    postPageAndExtractDraftId(
      "Start new app",
      postToken = true,
      s"$arsUrl/advance-valuation-ruling/applications-and-rulings",
      "describe-role-importer",
      Map.empty[String, String]
    )

  def navigateToSelectYourRolePage: HttpRequestBuilder =
    getPage(
      "Select you role as an importer",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain"
    )

  def selectARole(SelectARole: Boolean): HttpRequestBuilder = {
    val selectMethod = Map("value" -> "agentOnBehalfOfTrader")
    postPage(
      "select a role",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/describe-role-importer",
      if (SelectARole) selectMethod else Map.empty[String, String]
    )
  }

  def getChangeImporterRole: HttpRequestBuilder =
    getPage(
      "GET ChangeImporterRole page",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/change-role-importer"
    )

  def postChangeImporterRole: HttpRequestBuilder =
    postPage(
      "POST ChangeImporterRole page",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/change-role-importer",
      Map("value" -> "false")
    )

  def submitStarterChecklist: HttpRequestBuilder =
    getPage(
      "starter checklist",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/start-application"
    )

  def navigateToPlanningToImportGoods: HttpRequestBuilder =
    getPage(
      "Are you planning to import goods to the UK",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain"
    )

  def submitPlanningToImportGoods(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Are you planning to import goods to the UK",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/planning-import-goods-great-britain",
      answer.toPayload
    )

  def navigateToContactAboutYourApp: HttpRequestBuilder =
    getPage(
      "Contact about your app",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/need-to-contact-you"
    )

  def navigateToEnterTradersEori: HttpRequestBuilder =
    getPage(
      "Enter trader's EORI number page",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/provide-trader-eori"
    )

  def submitTradersEori(EoriNo: Boolean): HttpRequestBuilder = {
    val enterEori = Map(
      "value" -> "GB112888888040"
    )
    postPage(
      "enter traders EORI number",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/provide-trader-eori?saveDraft=false",
      if (EoriNo) enterEori else Map.empty[String, String]
    )
  }

  def navigateToCheckTraderEoriDetails(): HttpRequestBuilder =
    getPage(
      "Check trader EORI details",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/check-trader-EORI-details"
    )

  def navigateToProvideBusinessContactDetails(): HttpRequestBuilder =
    getPage(
      "Enter Business Contact details",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/business-contact-details"
    )

  def submitBusinessContactDetails(): HttpRequestBuilder = {

    val enterTextAllBoxes = Map(
      "name"         -> "test",
      "email"        -> "test@gmail.com",
      "phone"        -> "12345678",
      "company name" -> "test Inc",
      "jobTitle"     -> "Agent for trader"
    )

    postPage(
      "Provide Business Contact Details",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/provide-contact-details?saveDraft=false",
      enterTextAllBoxes
    )
  }

  def navigateToMethodNamePage(): HttpRequestBuilder =
    getPage(
      "Method Name Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/select-valuation-method"
    )

  def selectMethod4(selectAnyOneMethod: Boolean): HttpRequestBuilder = {
    val selectMethod = Map(
      "value" -> "method4"
    )
    postPage(
      "selectMethod",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/select-valuation-method",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1to3Page: HttpRequestBuilder =
    getPage(
      "Why Not Selected Method 1-3 Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/explain-why-not-methods-1-3"
    )

  def enterReasonNotSelectedMethod1(enterReason: Boolean): HttpRequestBuilder = {
    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1-3",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/explain-why-not-methods-1-3?saveDraft=false",
      if (enterReason) enterText else Map.empty[String, String]
    )
  }

  def enterReasonWhySelectedMethod4(): HttpRequestBuilder = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for selecting Method 4",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/explain-why-method-4?saveDraft=false",
      enterText
    )
  }

  def navigateNameOfTheGoodsPage: HttpRequestBuilder =
    getPage(
      "Name Of Short description of goods",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/give-short-description-goods"
    )

  def enterNameofTheGoods(): HttpRequestBuilder = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter short description of goods",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/give-short-description-goods?saveDraft=false",
      enterText
    )
  }

  def navigateFoundCommodityCodePage: HttpRequestBuilder =
    getPage(
      "Found Commodity Code Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/do-you-have-commodity-code"
    )

  def submitFoundCommodityCode(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/do-you-have-commodity-code",
      answer.toPayload
    )

  def navigateCommodityCodePage: HttpRequestBuilder =
    getPage(
      "Navigate Commodity Code Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/enter-commodity-code"
    )

  def enterCommodityCode(): HttpRequestBuilder = {

    val enterText = Map(
      "value" -> "1234"
    )

    postPage(
      "Enter Commodity Code",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/enter-commodity-code?saveDraft=false",
      enterText
    )
  }

  def navigateLegalChallengePage: HttpRequestBuilder =
    getPage(
      "Found Legal challenges Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/any-legal-challenges"
    )

  def submitYesOrNolChallengePage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No in  Legal challenges Page",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/any-legal-challenges",
      answer.toPayload
    )

  def navigateToConfidentialInfoPage: HttpRequestBuilder =
    getPage(
      "Navigate To Add Confidential Info Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/add-confidential-information"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean): HttpRequestBuilder = postPage(
    "Select Yes in Confidential Page",
    s"$arsUrl/advance-valuation-ruling/$${draftId}/add-confidential-information",
    answer.toPayload
  )

  def navigateToEnterConfidentialInfoPage: HttpRequestBuilder =
    getPage(
      "Navigate To Confidential  info Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/provide-confidential-information"
    )

  def submitTheConfidentialInfo(): HttpRequestBuilder = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "enter The Goods Confidential Info",
      s"$arsUrl/advance-valuation-ruling/$${draftId}/provide-confidential-information?saveDraft=false",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage: HttpRequestBuilder =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      saveToken = true,
      s"$arsUrl/advance-valuation-ruling/$${draftId}/add-supporting-documents"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean): HttpRequestBuilder = postPage(
    "Select No Upload Supporting Docs Page",
    s"$arsUrl/advance-valuation-ruling/$${draftId}/add-supporting-documents",
    answer.toPayload
  )
}
