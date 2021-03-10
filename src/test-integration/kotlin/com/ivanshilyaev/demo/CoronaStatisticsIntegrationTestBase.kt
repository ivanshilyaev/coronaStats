package com.ivanshilyaev.demo

import com.ivanshilyaev.demo.config.CoronaStatisticsConfiguration
import com.ivanshilyaev.demo.controller.CoronaStatisticsController
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockserver.client.MockServerClient
import org.mockserver.integration.ClientAndServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [CoronaStatisticsConfiguration::class, CoronaStatisticsController::class])
@WebAppConfiguration(value = "src/main/kotlin")
abstract class CoronaStatisticsIntegrationTestBase() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    lateinit var mockMvc: MockMvc
    private val port = 1080
    var mockServer: MockServerClient = MockServerClient("localhost", port)
    val url = "http://localhost:$port"

    @BeforeEach
    fun startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(port)
        mockMvc = webAppContextSetup(webApplicationContext).build()
    }

    @AfterEach
    fun stopMockServer() {
        mockServer.close()
    }
}
