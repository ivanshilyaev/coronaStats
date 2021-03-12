package com.ivanshilyaev.demo.http

import java.net.http.HttpResponse

interface CustomHttpClient {

    fun sendRequest(uri: String) : HttpResponse<String>
}
