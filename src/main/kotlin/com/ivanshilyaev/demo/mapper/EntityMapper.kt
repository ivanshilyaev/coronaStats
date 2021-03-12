package com.ivanshilyaev.demo.mapper

import com.ivanshilyaev.demo.dto.CountryStatisticsDto
import com.ivanshilyaev.demo.entity.CountryStatistics

interface EntityMapper {

    fun mapCountryStatistics(dto: CountryStatisticsDto) : CountryStatistics
}
