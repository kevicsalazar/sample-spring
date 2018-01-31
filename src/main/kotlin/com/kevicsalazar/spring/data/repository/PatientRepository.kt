package com.kevicsalazar.spring.data.repository

import com.kevicsalazar.spring.data.model.Patient
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
interface PatientRepository : ReactiveCrudRepository<Patient, String> {

    fun findByDocumentAndPassCode(document: String, passcode: String): Mono<Patient>

}