package com.ivanshilyaev.demo.service

import com.ivanshilyaev.demo.entity.CoronaStatistics

interface CoronaStatisticsService {

    fun getAllStatistics(): CoronaStatistics
}
