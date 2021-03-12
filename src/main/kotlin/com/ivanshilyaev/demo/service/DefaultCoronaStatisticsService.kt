package com.ivanshilyaev.demo.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ivanshilyaev.demo.dto.CoronaStatisticsDto
import com.ivanshilyaev.demo.entity.CoronaStatistics
import com.ivanshilyaev.demo.http.CustomHttpClient
import com.ivanshilyaev.demo.mapper.EntityMapper
import org.springframework.beans.factory.annotation.Autowired

class DefaultCoronaStatisticsService() : CoronaStatisticsService {

    @Autowired
    private lateinit var customHttpClient: CustomHttpClient

    @Autowired
    private lateinit var entityMapper: EntityMapper

    companion object {
        const val API_URI = "https://corona-api.com/countries"
    }

    override fun getAllStatistics(): CoronaStatistics {

        val apiData = customHttpClient.sendRequest(API_URI).body()
        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val dto = mapper.readValue(apiData, CoronaStatisticsDto::class.java)
        val data = dto.data.map { entityMapper.mapCountryStatistics(it) }

        return CoronaStatistics(data)
    }
}
