package com.ivanshilyaev.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CoronaStatisticsDto(
    @JsonProperty("data") val data: List<CountryStatisticsDto>
)
