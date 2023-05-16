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

  def navigateToAccountHome =
    getPage(
      "account home",
      true,
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings",
      expectedStatus = 200
    )

  def startNewApp =
    postPage(
      "start new app",
      s"$baseUrl/advance-valuation-ruling/applications-and-rulings",
      s"$baseUrl/advance-valuation-ruling/start-application",
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
      s"$baseUrl/advance-valuation-ruling/start-application",
      s"$baseUrl/advance-valuation-ruling/planning-import-goods-great-britain",
      if (allTicked) allBoxesTickedPayload else Map.empty[String, String]
    )
  }

  def navigateToPlanningToImportGoods =
    getPage(
      "Are you planning to import goods to the UK",
      true,
      s"$baseUrl/advance-valuation-ruling/planning-import-goods-great-britain"
    )

  def submitPlanningToImportGoods(answer: Boolean) = postPage(
    "Are you planning to import goods to the UK",
    s"$baseUrl/advance-valuation-ruling/planning-import-goods-great-britain",
    s"$baseUrl/advance-valuation-ruling/need-to-contact-you",
    answer.toPayload
  )

  def navigateToContactAboutYourApp =
    getPage(
      "Contact about your app",
      true,
      s"$baseUrl/advance-valuation-ruling/need-to-contact-you"
    )

  def navigateToCheckRegisteredDetails(answer: Boolean) =
    getPage(
      "Check registered details",
      true,
      s"$baseUrl/advance-valuation-ruling/check-EORI-details?csrfToken=" + "${csrfToken}"
    )

  def navigateToProvideContactDetails(answer: Boolean) =
    getPage(
      "Enter Contact details",
      true,
      s"$baseUrl/advance-valuation-ruling/provide-contact-details"
    )

  def submitProvideContactDetails(enterText: Boolean) = {

    val enterTextAllBoxes = Map(
      "name"  -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678"
    )

    postPage(
      "Provide Contact Details",
      s"$baseUrl/advance-valuation-ruling/provide-contact-details",
      s"$baseUrl/advance-valuation-ruling/select-valuation-method",
      enterTextAllBoxes
    )
  }

  def navigateToMethodNamePage(answer: Boolean) =
    getPage(
      "Method Name Page",
      true,
      s"$baseUrl/advance-valuation-ruling/select-valuation-method"
    )

  def selectMethod4(selectAnyOneMethod: Boolean) = {
    val selectMethod = Map(
      "value" -> "method4"
    )
    postPage(
      "selectMethod",
      s"$baseUrl/advance-valuation-ruling/select-valuation-method",
      s"$baseUrl/advance-valuation-ruling/explain-why-not-methods-1-3",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1to3Page =
    getPage(
      "Why Not Selected Method 1-3 Page",
      true,
      s"$baseUrl/advance-valuation-ruling/explain-why-not-methods-1-3"
    )

  def enterReasonNotSelectedMethod1(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"$baseUrl/advance-valuation-ruling/explain-why-not-methods-1-3",
      s"$baseUrl/advance-valuation-ruling/explain-why-method-4",
      enterText
    )
  }

  def enterReasonWhySelectedMethod4(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"$baseUrl/advance-valuation-ruling/explain-why-method-4",
      s"$baseUrl/advance-valuation-ruling/give-short-description-goods",
      enterText
    )
  }

  def navigateNameOfTheGoodsPage =
    getPage(
      "Name Of Short description of goods",
      true,
      s"$baseUrl/advance-valuation-ruling/give-short-description-goods"
    )
  def enterNameofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter hort description of goods",
      s"$baseUrl/advance-valuation-ruling/give-short-description-goods",
      s"$baseUrl/advance-valuation-ruling/do-you-have-commodity-code",
      enterText
    )
  }

  def navigateFoundCommodityCodePage =
    getPage(
      "Found Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/do-you-have-commodity-code"
    )

  def submitFoundCommodityCode(answer: Boolean) =
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"$baseUrl/advance-valuation-ruling/do-you-have-commodity-code",
      s"$baseUrl/advance-valuation-ruling/enter-commodity-code",
      answer.toPayload
    )

  def navigateCommodityCodePage =
    getPage(
      "Navigate Commodity Code Page",
      true,
      s"$baseUrl/advance-valuation-ruling/enter-commodity-code"
    )
  def enterCommodityCode(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "1234"
    )

    postPage(
      "Enter Commodity Code",
      s"$baseUrl/advance-valuation-ruling/enter-commodity-code",
      s"$baseUrl/advance-valuation-ruling/any-legal-challenges",
      enterText
    )
  }

  def navigateLegalChallengePage =
    getPage(
      "Found Legal challenges Page",
      true,
      s"$baseUrl/advance-valuation-ruling/any-legal-challenges"
    )

  def submityesOrNolChallengePage(answer: Boolean) =
    postPage(
      "Click Yes or No in  Legal challenges Page",
      s"$baseUrl/advance-valuation-ruling/any-legal-challenges",
      s"$baseUrl/advance-valuation-ruling/describe-legal-challenges",
      answer.toPayload
    )

  def enterNameofThelegalchallengesGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter the  description of Legal challenges to the goods",
      s"$baseUrl/advance-valuation-ruling/describe-legal-challenges",
      s"$baseUrl/advance-valuation-ruling/add-confidential-information",
      enterText
    )
  }

  def navigateToConfidentialInfoPage =
    getPage(
      "Navigate To Add Confidential Info Page",
      true,
      s"$baseUrl/advance-valuation-ruling/add-confidential-information"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean) = postPage(
    "Select Yes in Confidential Page",
    s"$baseUrl/advance-valuation-ruling/add-confidential-information",
    s"$baseUrl/advance-valuation-ruling/provide-confidential-information",
    answer.toPayload
  )

  def navigateToEnterConfidentialInfoPage =
    getPage(
      "Navigate To Confidential  info Page",
      true,
      s"$baseUrl/advance-valuation-ruling/provide-confidential-information"
    )

  def submitTheConfidentialInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "enter The Goods Confidential Info",
      s"$baseUrl/advance-valuation-ruling/provide-confidential-information",
      s"$baseUrl/advance-valuation-ruling/add-supporting-documents",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      true,
      s"$baseUrl/advance-valuation-ruling/add-supporting-documents"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean) = postPage(
    "Select No Upload Supporting Docs Page",
    s"$baseUrl/advance-valuation-ruling/add-supporting-documents",
    s"$baseUrl/advance-valuation-ruling/check-your-answers",
    answer.toPayload
  )

  def navigateToCheckYourAnswerPage =
    getPage(
      "Navigate To check Your Answers Page",
      true,
      s"$baseUrl/advance-valuation-ruling/check-your-answers"
    )
}
