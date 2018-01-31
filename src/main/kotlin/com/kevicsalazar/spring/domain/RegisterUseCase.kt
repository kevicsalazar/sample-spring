package com.kevicsalazar.spring.domain

import com.fasterxml.jackson.databind.node.ObjectNode
import com.kevicsalazar.spring.data.model.Patient
import com.kevicsalazar.spring.data.repository.PatientRepository
import com.kevicsalazar.spring.utils.objectNode
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class RegisterUseCase(val rep: PatientRepository) {

    fun register(firstName: String, lastName: String, document: String, passCode: String): Mono<ObjectNode> {
        val patient = Patient(firstName, lastName, document, passCode)
        return rep.save(patient).map {
            objectNode("message" to "success")
        }
    }

}