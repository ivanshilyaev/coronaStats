package com.ivanshilyaev.demo

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.nhaarman.mockito_kotlin.any
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import io.kotlintest.shouldBe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.net.http.HttpClient
import java.net.http.HttpResponse

internal class CoronaStatisticsServiceSpec : Spek({

    describe("#${CoronaStatisticsService::getAllStatistics.name}") {

        val client = mock<HttpClient>()

        val service = CoronaStatisticsService(client)

        beforeEachTest { reset(client) }

        on("valid response from http client") {
            val response = mock<HttpResponse<String>>()
            whenever(client.send(any(), any<HttpResponse.BodyHandler<String>>())).thenReturn(response)

            val responseBody = randomJson()
            whenever(response.body()).thenReturn(responseBody)

            val parser = JsonParser()
            val json = parser.parse(responseBody).asJsonObject
            val gson = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gson.toJson(json)

            val result = run { service.getAllStatistics() }

            it("returns pretty json") {
                result shouldBe jsonString
            }
        }
    }
})
