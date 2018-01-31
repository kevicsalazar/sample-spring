package com.kevicsalazar.spring.domain

import com.fasterxml.jackson.databind.node.ObjectNode
import com.kevicsalazar.spring.data.repository.PatientRepository
import com.kevicsalazar.spring.utils.Abort
import com.kevicsalazar.spring.utils.MSG04
import com.kevicsalazar.spring.utils.obj
import com.kevicsalazar.spring.utils.objectNode
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class LoginUseCase(val rep: PatientRepository, val useCase: AuthUseCase) {

    fun login(document: String, passCode: String): Mono<ObjectNode> {
        return rep.findByDocumentAndPassCode(document, passCode)
                .map {
                    val token = useCase.patientToken(it._id!!)
                    val patient = it.obj().apply {
                        remove("passCode")
                    }
                    objectNode(
                            "token" to token,
                            "patient" to patient
                    )
                }.switchIfEmpty(Mono.create {
                    it.error(Abort.unauthorized(MSG04))
                })
    }
}