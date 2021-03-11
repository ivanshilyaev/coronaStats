package com.ivanshilyaev.demo.config

import com.ivanshilyaev.demo.service.DefaultCoronaStatisticsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoronaStatisticsConfiguration {

    @Bean
    fun coronaStatisticsService() = DefaultCoronaStatisticsService()
}
