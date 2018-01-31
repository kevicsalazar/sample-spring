package com.kevicsalazar.spring.domain

import com.fasterxml.jackson.databind.node.ObjectNode
import com.kevicsalazar.spring.data.repository.PatientRepository
import com.kevicsalazar.spring.utils.obj
import com.kevicsalazar.spring.utils.objectNode
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ProfileUseCase(val rep: PatientRepository) {

    fun profile(id: String): Mono<ObjectNode> {
        return rep.findById(id).map {
            val patient = it.obj().apply {
                remove("passCode")
            }
            objectNode("patient" to patient)
        }
    }

    fun update(id: String): Mono<ObjectNode> {
        return Mono.create { it.success(objectNode("message" to "success")) }
    }

}