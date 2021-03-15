package com.ivanshilyaev.demo.http

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.should
import io.kotlintest.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito
import randomStr
import randomUrl
import java.net.http.HttpClient
import java.net.http.HttpResponse

internal class DefaultCustomHttpClientSpec : Spek({

    describe("#${DefaultCustomHttpClient::sendRequest.name}") {

        val client = mock<HttpClient>()
        val customHttpClient = DefaultCustomHttpClient(client)

        beforeEachTest { reset(client) }

        on("valid response from http client") {

            val response = mock<HttpResponse<String>>()
            whenever(client.send(Mockito.any(), Mockito.any(HttpResponse.BodyHandler::class.java)))
                .thenReturn(response)

            val responseBody = randomStr()
            whenever(response.body()).thenReturn(responseBody)

            val validUri = "https://corona-api.com/countries"
            val result = customHttpClient.sendRequest(validUri)

            it("return string api data") {

                result shouldBe responseBody
            }
        }

        on("invalid response from http client") {

            val response = mock<HttpResponse<String>>()
            whenever(client.send(Mockito.any(), Mockito.any(HttpResponse.BodyHandler::class.java)))
                .thenReturn(response)

            val responseBody = ""
            whenever(response.body()).thenReturn(responseBody)

            val validUri = "https://corona-api.com/countries"
            val result = runCatching { customHttpClient.sendRequest(validUri) }

            it("throw ${CustomHttpClientException::class.java.simpleName} exception") {

                result should beInstanceOf(CustomHttpClientException::class)
            }
        }

        on("invalid uri passed") {

            val invalidUri = randomUrl()
            val result = runCatching { customHttpClient.sendRequest(invalidUri) }

            it("throw ${CustomHttpClientException::class.java.simpleName} exception") {

                result should beInstanceOf(CustomHttpClientException::class)
            }
        }
    }
})
