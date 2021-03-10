package com.ivanshilyaev.demo.config

import com.ivanshilyaev.demo.service.CoronaStatisticsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoronaStatisticsConfiguration {

    @Bean
    fun coronaStatisticsService() = CoronaStatisticsService()
}
