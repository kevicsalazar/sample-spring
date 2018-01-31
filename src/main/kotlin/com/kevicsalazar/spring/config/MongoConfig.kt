package com.kevicsalazar.spring.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
class MongoConfig(val props: PropertiesConfig) {

    @Bean
    @Throws(Exception::class)
    fun mongo(): MongoClient {
        return MongoClient(props.mongoHost, props.mongoPort)
    }

    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongo(), props.mongoName)
    }

}
