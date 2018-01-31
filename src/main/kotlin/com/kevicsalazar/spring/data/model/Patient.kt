package com.kevicsalazar.spring.data.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "patients")
data class Patient(
        val firstName: String,
        val lastName: String,
        val document: String,
        val passCode: String? = null,
        @Id val _id: String? = null
)