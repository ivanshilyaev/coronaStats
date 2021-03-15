package com.ivanshilyaev.demo.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ivanshilyaev.demo.dto.CoronaStatisticsDto
import com.ivanshilyaev.demo.entity.CoronaStatistics
import com.ivanshilyaev.demo.http.CustomHttpClient
import com.ivanshilyaev.demo.mapper.EntityMapper
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired

private val logger = KotlinLogging.logger {}

class DefaultCoronaStatisticsService() : CoronaStatisticsService {

    @Autowired
    private lateinit var customHttpClient: CustomHttpClient

    @Autowired
    private lateinit var entityMapper: EntityMapper

    companion object {

        const val API_URI = "https://corona-api.com/countries"
    }

    override fun getAllStatistics(): CoronaStatistics {

        val apiData = customHttpClient.sendRequest(API_URI)
        logger.info { "Retrieve data from http response" }

        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val coronaStatisticsDto = mapper.readValue(apiData, CoronaStatisticsDto::class.java)
        logger.info { "Map data from json to dto" }

        val countryStatistics = coronaStatisticsDto.data.map { entityMapper.mapCountryStatistics(it) }
        logger.info { "Map data from dto to entity" }

        return CoronaStatistics(countryStatistics)
    }
}
