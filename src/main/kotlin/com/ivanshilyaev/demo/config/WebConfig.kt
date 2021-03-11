package com.ivanshilyaev.demo.config

import org.springframework.context.annotation.ComponentScan

@ComponentScan(value = ["src/main/kotlin/com/ivanshilyaev/demo/controller"])
class WebConfig { }
