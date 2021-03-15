package com.ivanshilyaev.demo.http

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class DefaultCustomHttpClient(
    private val client: HttpClient
) : CustomHttpClient {

    override fun sendRequest(uri: String): String {

        try {
            val request = HttpRequest.newBuilder(
                URI.create(uri)
            )
                .header("accept", "application/json")
                .build()
            val responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
            if (responseBody == null || responseBody.isEmpty()) {
                throw CustomHttpClientException("Invalid response body")
            }

            return responseBody
        } catch (e: Exception) {
            throw CustomHttpClientException("Couldn't send api request")
        }
    }
}
