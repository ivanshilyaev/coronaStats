package com.ivanshilyaev.demo.http

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class DefaultCustomHttpClient : CustomHttpClient {

    override fun sendRequest(uri: String): HttpResponse<String> {

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder(
            URI.create(uri)
        )
            .header("accept", "application/json")
            .build()

        return try {
            client.send(request, HttpResponse.BodyHandlers.ofString())
        } catch (e: Exception) {
            throw CustomHttpClientException("Couldn't send api request")
        }
    }
}
