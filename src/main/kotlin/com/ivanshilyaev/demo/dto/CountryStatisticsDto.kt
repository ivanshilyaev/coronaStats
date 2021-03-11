package com.ivanshilyaev.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryStatisticsDto (

    @JsonProperty("name") val name: String,
    @JsonProperty("updated_at") val updatedAt: String,
    @JsonProperty("today") val statisticsTodayToday: CountryStatisticsTodayDto,
    @JsonProperty("latest_data") val generalStatistics: CountryGeneralStatisticsDto
)
