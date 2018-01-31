package com.kevicsalazar.spring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class PropertiesConfig {

    @Value("\${jwt.secret_key}")
    var jwtSecretKey: String = ""

    @Value("\${mongo.host}")
    var mongoHost: String = ""

    @Value("\${mongo.port}")
    var mongoPort: Int = 0

    @Value("\${mongo.name}")
    var mongoName: String = ""

}