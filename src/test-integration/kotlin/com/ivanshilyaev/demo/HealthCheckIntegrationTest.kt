package com.ivanshilyaev.demo

import org.junit.jupiter.api.Test
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class HealthCheckIntegrationTest : CoronaStatisticsIntegrationTestBase() {

    @Test
    fun get() {

        mockServer.`when`(
            request()
                .withMethod("GET")
                .withPath("$url/api/statistics/")
        )
            .respond(
                response()
                    .withStatusCode(200)
            )

        mockMvc.perform(get("$url/api/statistics/").accept("application/json"))
            .andExpect(status().isOk)
            .andReturn()
    }
}
