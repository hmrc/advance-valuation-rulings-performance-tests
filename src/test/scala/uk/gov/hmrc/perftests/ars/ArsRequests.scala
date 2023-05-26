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
      s"start-application",
      Map.empty[String, String]
    )

  def submitStarterChecklist(allTicked: Boolean) = {
    val allBoxesTickedPayload = Map(
      "value[0]" -> "option1",
      "value[1]" -> "option2",
      "value[2]" -> "option3",
      "value[3]" -> "option4",
      "value[4]" -> "option5",
      "value[5]" -> "option6"
    )
    postPage(
      "starter checklist",
      s"$urlWithDraftId/start-application",
      if (allTicked) allBoxesTickedPayload else Map.empty[String, String]
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
      true,
      s"$urlWithDraftId/need-to-contact-you"
    )

  def navigateToCheckRegisteredDetails(answer: Boolean) =
    getPage(
      "Check registered details",
      true,
      s"$urlWithDraftId/check-EORI-details?csrfToken=" + "${csrfToken}"
    )

  def navigateToProvideContactDetails(answer: Boolean) =
    getPage(
      "Enter Contact details",
      true,
      s"$urlWithDraftId/provide-contact-details"
    )

  def submitProvideContactDetails(enterText: Boolean) = {

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

  def navigateToMethodNamePage(answer: Boolean) =
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

  def enterReasonNotSelectedMethod1(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"$urlWithDraftId/explain-why-not-methods-1-3",
      enterText
    )
  }

  def enterReasonWhySelectedMethod4(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"$urlWithDraftId/explain-why-method-4",
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
      "Enter hort description of goods",
      s"$urlWithDraftId/give-short-description-goods",
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
      s"$urlWithDraftId/enter-commodity-code",
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
      s"$urlWithDraftId/provide-confidential-information",
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
      true,
      s"$urlWithDraftId/check-your-answers"
    )
}