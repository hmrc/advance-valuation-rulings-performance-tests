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
      "/start-application",
      Map.empty[String, String],
      useDraftId= false

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
      "/start-application",
      "/planning-import-goods-great-britain",
      if (allTicked) allBoxesTickedPayload else Map.empty[String, String]
    )
  }


  def navigateToPlanningToImportGoods =
    getPage(
      "Are you planning to import goods to the UK",
      true,
      "/planning-import-goods-great-britain"
    )

  def submitPlanningToImportGoods(answer: Boolean) = postPage(
    "Are you planning to import goods to the UK",
    s"/planning-import-goods-great-britain",
    s"/need-to-contact-you",
    answer.toPayload
  )

  def navigateToContactAboutYourApp =
    getPage(
      "Contact about your app",
      true,
      s"/need-to-contact-you"
    )

  def navigateToCheckRegisteredDetails(answer: Boolean) =
    getPage(
      "Check registered details",
      true,
      s"/check-EORI-details?csrfToken=" + "${csrfToken}"
    )

  def navigateToProvideContactDetails(answer: Boolean) =
    getPage(
      "Enter Contact details",
      true,
      s"/provide-contact-details"
    )

  def submitProvideContactDetails(enterText: Boolean) = {

    val enterTextAllBoxes = Map(
      "name"  -> "test",
      "email" -> "test@gmail.com",
      "phone" -> "12345678"
    )

    postPage(
      "Provide Contact Details",
      s"/provide-contact-details",
      s"/select-valuation-method",
      enterTextAllBoxes
    )
  }


  def navigateToMethodNamePage(answer: Boolean) =
    getPage(
      "Method Name Page",
      true,
      s"/select-valuation-method"
    )

  def selectMethod4(selectAnyOneMethod: Boolean) = {
    val selectMethod = Map(
      "value" -> "method4"
    )
    postPage(
      "selectMethod",
      s"/select-valuation-method",
      s"/explain-why-not-methods-1-3",
      if (selectAnyOneMethod) selectMethod else Map.empty[String, String]
    )
  }

  def navigateWhyNotSelectedMethod1to3Page =
    getPage(
      "Why Not Selected Method 1-3 Page",
      true,
      s"/explain-why-not-methods-1-3"
    )

  def enterReasonNotSelectedMethod1(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"/explain-why-not-methods-1-3",
      s"/explain-why-method-4",
      enterText
    )
  }

  def enterReasonWhySelectedMethod4(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "test"
    )

    postPage(
      "Enter reason for Not selecting Method 1",
      s"/explain-why-method-4",
      s"/give-short-description-goods",
      enterText
    )
  }

  def navigateNameOfTheGoodsPage =
    getPage(
      "Name Of Short description of goods",
      true,
      s"/give-short-description-goods"
    )
  def enterNameofTheGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter hort description of goods",
      s"/give-short-description-goods",
      s"/do-you-have-commodity-code",
      enterText
    )
  }

  def navigateFoundCommodityCodePage =
    getPage(
      "Found Commodity Code Page",
      true,
      s"/do-you-have-commodity-code"
    )

  def submitFoundCommodityCode(answer: Boolean) =
    postPage(
      "Click Yes or No in  Found Commodity Code",
      s"/do-you-have-commodity-code",
      s"/enter-commodity-code",
      answer.toPayload
    )

  def navigateCommodityCodePage =
    getPage(
      "Navigate Commodity Code Page",
      true,
      s"/enter-commodity-code"
    )
  def enterCommodityCode(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "1234"
    )

    postPage(
      "Enter Commodity Code",
      s"/enter-commodity-code",
      s"/any-legal-challenges",
      enterText
    )
  }

  def navigateLegalChallengePage =
    getPage(
      "Found Legal challenges Page",
      true,
      s"/any-legal-challenges"
    )

  def submityesOrNolChallengePage(answer: Boolean) =
    postPage(
      "Click Yes or No in  Legal challenges Page",
      s"/any-legal-challenges",
      s"/describe-legal-challenges",
      answer.toPayload
    )

  def enterNameofThelegalchallengesGoods(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "Enter the  description of Legal challenges to the goods",
      s"/describe-legal-challenges",
      s"/add-confidential-information",
      enterText
    )
  }

  def navigateToConfidentialInfoPage =
    getPage(
      "Navigate To Add Confidential Info Page",
      true,
      s"/add-confidential-information"
    )

  def submitYesInConfidentialInfoPage(answer: Boolean) = postPage(
    "Select Yes in Confidential Page",
    s"/add-confidential-information",
    s"/provide-confidential-information",
    answer.toPayload
  )

  def navigateToEnterConfidentialInfoPage =
    getPage(
      "Navigate To Confidential  info Page",
      true,
      s"/provide-confidential-information"
    )

  def submitTheConfidentialInfo(enterText: Boolean) = {

    val enterText = Map(
      "value" -> "text"
    )

    postPage(
      "enter The Goods Confidential Info",
      s"/provide-confidential-information",
      s"/add-supporting-documents",
      enterText
    )
  }

  def navigateToUploadSupportingDocsPage =
    getPage(
      "Navigate To Upload Supporting Docs Page",
      true,
      s"/add-supporting-documents"
    )

  def submitNoInUploadSupportingDocsPage(answer: Boolean) = postPage(
    "Select No Upload Supporting Docs Page",
    s"/add-supporting-documents",
    s"/check-your-answers",
    answer.toPayload
  )

  def navigateToCheckYourAnswerPage =
    getPage(
      "Navigate To check Your Answers Page",
      true,
      s"/check-your-answers"
    )

//  def clickAcceptAndApplyForRuling (answer: Boolean)=
//    postPage(
//      "click Accept And Apply on check Your Answers Page",
//      true,
//      s"/check-your-answers",
//      s"/application-complete?draftId=GBAVR002137474",
//      answer.toPayload
//    )


//  def submityesOrNolChallengePage(answer: Boolean) =
//    postPage(
//      "Click Yes or No in  Legal challenges Page",
//      s"$baseUrl/advance-valuation-ruling/DRAFT007085244/any-legal-challenges",
//      s"$baseUrl/advance-valuation-ruling/DRAFT007085244/describe-legal-challenges",
//      answer.toPayload
//    )
}
