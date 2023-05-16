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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object AuthRequests {
  val authWizardUrl: String        = s"${Configuration.authUrl}/auth-login-stub/gg-sign-in"
  val redirectionUrl: String       = s"/advance-valuation-ruling/applications-and-rulings"
  val authWizardSessionUrl: String = s"${Configuration.authUrl}/auth-login-stub/session"

  val navigateToAuthWizard: HttpRequestBuilder =
    http("Navigate to /auth-login-stub/gg-sign-in")
      .get(authWizardUrl)
      .check(status.is(200))

  val navigateToAuthWizardSession: HttpRequestBuilder =
    http("Navigate to /auth-login-stub/session")
      .get(authWizardSessionUrl)
      .check(status.is(200))

  def submitAuthWizard: HttpRequestBuilder =
    http("Log in to auth")
      .post(authWizardUrl)
      .formParam("redirectionUrl", redirectionUrl)
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("credentialRole", "User")
      .formParam("enrolment[0].name", "HMRC-ATAR-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "EORINumber")
      .formParam("enrolment[0].taxIdentifier[0].value", "GB1234567890")
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(303))
      .check(header("Location").is(redirectionUrl))
}
