package com.ivanshilyaev.demo

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import java.net.http.HttpClient

@Service
class CoronaStatisticsService(
    private val client: OkHttpClient = OkHttpClient(),
) {

    fun getAllStatistics(): String {

        val request = Request.Builder()
            .url("https://corona-api.com/countries")
            .get()
            .build()
        val response = client.newCall(request).execute()
        val uglyJson = response.body?.string().toString()
        val jsonElement = JsonParser().parse(uglyJson)
        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(jsonElement)
    }
}
