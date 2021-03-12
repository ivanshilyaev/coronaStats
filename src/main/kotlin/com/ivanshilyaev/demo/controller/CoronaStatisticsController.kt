package com.ivanshilyaev.demo.controller

import com.ivanshilyaev.demo.service.CoronaStatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/statistics")
class CoronaStatisticsController() {

    @Autowired
    private lateinit var coronaStatisticsService: CoronaStatisticsService

    @GetMapping("/")
    fun findAll(): String {

        return try {
            coronaStatisticsService.getAllStatistics().toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}
