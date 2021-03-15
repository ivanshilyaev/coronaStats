package com.ivanshilyaev.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryGeneralStatisticsDto(
    @JsonProperty("deaths") val deaths: Int,
    @JsonProperty("confirmed") val confirmed: Int,
    @JsonProperty("recovered") val recovered: Int,
    @JsonProperty("critical") val critical: Int
)
