package com.ivanshilyaev.demo.http

interface CustomHttpClient {

    fun sendRequest(uri: String): String
}
