package com.ivanshilyaev.demo.mapper

import com.ivanshilyaev.demo.dto.CountryStatisticsDto
import com.ivanshilyaev.demo.entity.CountryStatistics

class DefaultEntityMapper : EntityMapper {

    override fun mapCountryStatistics(dto: CountryStatisticsDto): CountryStatistics {

        return CountryStatistics(
            dto.name,
            dto.updatedAt,
            dto.statisticsTodayToday.deaths,
            dto.statisticsTodayToday.confirmed,
            dto.generalStatistics.deaths,
            dto.generalStatistics.confirmed,
            dto.generalStatistics.recovered,
            dto.generalStatistics.critical
        )
    }
}
