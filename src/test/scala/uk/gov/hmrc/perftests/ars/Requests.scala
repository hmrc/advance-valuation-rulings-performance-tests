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

object Requests {
  def getPage(
    stepName: String,
    saveToken: Boolean,
    url: String,
    pageContent: Option[String] = None,
    expectedStatus: Int = 200
  ): HttpRequestBuilder = {
    val builder = http("Get " + stepName)
      .get(url)
      .check(status.is(expectedStatus))
      .check(currentLocation.is(url))

    val httpRequestBuilder = pageContent match {
      case Some(value) => builder.check(substring(value))
      case None        => builder
    }

    if (saveToken) {
      httpRequestBuilder.check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
    } else {
      httpRequestBuilder
    }
  }

  def getPage(stepName: String, url: String): HttpRequestBuilder =
    getPage(stepName, saveToken = false, url, pageContent = None)

  def postPage(
    stepName: String,
    currentPage: String,
    nextPage: String,
    value: String
  ): HttpRequestBuilder =
    postPage(stepName, postToken = true, currentPage, nextPage, value)

  def postPage(
    stepName: String,
    postToken: Boolean,
    currentPage: String,
    nextPage: String,
    value: String
  ): HttpRequestBuilder =
    postPage(stepName, postToken, currentPage, nextPage, Map("value" -> value))

  def postPage(
    stepName: String,
    currentPage: String,
    nextPage: String,
    values: Map[String, String]
  ): HttpRequestBuilder =
    postPage(stepName, postToken = true, currentPage, nextPage, values)

  def postPageFor(
    currentPagePath: String,
    values: Map[String, String] = Map.empty
  ): HttpRequestBuilder = {
    require(currentPagePath.startsWith("/"))

    val url             = s"${Configuration.arsUrl}$currentPagePath"
    val stepDescription =
      url.drop(currentPagePath.lastIndexOf("/") + 1).split("-").map(_.capitalize).mkString(" ")
    val stepName        = s"Get Page for $stepDescription"

    postPage(stepName, postToken = true, url, "", values)
  }

  def postPage(
    stepName: String,
    postToken: Boolean,
    currentPage: String,
    nextPage: String,
    values: Map[String, String]
  ): HttpRequestBuilder =
    http(_ => "Post " + stepName)
      .post(currentPage)
      .formParamMap(
        if (postToken) {
          values + ("csrfToken" -> f"$${csrfToken}")
        } else {
          values
        }
      )
      .check(status.is(303))
      .check(currentLocation.is(currentPage))
}
