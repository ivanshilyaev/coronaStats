package com.ivanshilyaev.demo.config

import com.ivanshilyaev.demo.http.DefaultCustomHttpClient
import com.ivanshilyaev.demo.mapper.DefaultEntityMapper
import com.ivanshilyaev.demo.service.DefaultCoronaStatisticsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.http.HttpClient

@Configuration
class CoronaStatisticsConfiguration {

    @Bean
    fun coronaStatisticsService() = DefaultCoronaStatisticsService()

    @Bean
    fun customHttpClient() = DefaultCustomHttpClient(HttpClient.newHttpClient())

    @Bean
    fun entityMapper() = DefaultEntityMapper()
}
