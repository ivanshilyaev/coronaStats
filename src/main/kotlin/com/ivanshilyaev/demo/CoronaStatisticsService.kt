package com.ivanshilyaev.demo

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val API_URI = "https://corona-api.com/countries"

@Service
class CoronaStatisticsService(
    private val client: HttpClient = HttpClient.newHttpClient()
) {

    fun getAllStatistics(): String {

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
