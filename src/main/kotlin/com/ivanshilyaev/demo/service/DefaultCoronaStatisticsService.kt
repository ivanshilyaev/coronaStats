package com.ivanshilyaev.demo.service

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val API_URI = "https://corona-api.com/countries"

class DefaultCoronaStatisticsService(
    private val client: HttpClient = HttpClient.newHttpClient()
) : CoronaStatisticsService {

    override fun getAllStatistics(): String {

        val request = HttpRequest.newBuilder(
            URI.create(API_URI)
        )
            .header("accept", "application/json")
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val parser = JsonParser()
        val json = parser.parse(response.body()).asJsonObject
        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(json)
    }
}
