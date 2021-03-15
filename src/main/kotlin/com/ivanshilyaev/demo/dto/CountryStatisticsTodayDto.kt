package com.ivanshilyaev.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryStatisticsTodayDto (
    @JsonProperty("deaths") val deaths: Int,
    @JsonProperty("confirmed") val confirmed: Int
)
