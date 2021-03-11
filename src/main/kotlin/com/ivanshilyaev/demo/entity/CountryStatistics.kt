package com.ivanshilyaev.demo.entity

data class CountryStatistics (
    val name: String,
    val updatedAt: String,
    val deathsToday: Int,
    val confirmedCasesToday: Int,
    val totalDeaths: Int,
    val totalConfirmedCases: Int,
    val totalRecovered: Int,
    val totalCriticalCases: Int
)
