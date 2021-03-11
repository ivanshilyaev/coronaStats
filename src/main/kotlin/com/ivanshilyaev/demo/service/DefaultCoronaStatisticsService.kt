package com.ivanshilyaev.demo.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ivanshilyaev.demo.dto.CoronaStatisticsDto
import com.ivanshilyaev.demo.entity.CoronaStatistics
import com.ivanshilyaev.demo.entity.CountryStatistics
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private const val API_URI = "https://corona-api.com/countries"

class DefaultCoronaStatisticsService(
    private val client: HttpClient = HttpClient.newHttpClient()
) : CoronaStatisticsService {

    override fun getAllStatistics(): CoronaStatistics {

        val request = HttpRequest.newBuilder(
            URI.create(API_URI)
        )
            .header("accept", "application/json")
            .build()
        val apiResponse = client.send(request, HttpResponse.BodyHandlers.ofString())

        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val dto = mapper.readValue(apiResponse.body(), CoronaStatisticsDto::class.java)

        val data = dto.data.map {
            CountryStatistics(
                it.name,
                it.updatedAt,
                it.statisticsTodayToday.deaths,
                it.statisticsTodayToday.confirmed,
                it.generalStatistics.deaths,
                it.generalStatistics.confirmed,
                it.generalStatistics.recovered,
                it.generalStatistics.critical
            )
        }

        return CoronaStatistics(data)
    }
}
