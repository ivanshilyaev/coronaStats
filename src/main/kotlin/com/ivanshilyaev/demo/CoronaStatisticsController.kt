package com.ivanshilyaev.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/statistics")
class CoronaStatisticsController(
    private val coronaStatisticsService: CoronaStatisticsService
) {

    @GetMapping("/")
    fun findAll(): String {

        return "<pre>" + coronaStatisticsService.getAllStatistics() + "</pre>"
    }
}
