package com.kevicsalazar.spring.controllers

import com.fasterxml.jackson.databind.node.ObjectNode
import com.kevicsalazar.spring.domain.AuthUseCase
import com.kevicsalazar.spring.domain.LoginUseCase
import com.kevicsalazar.spring.domain.ProfileUseCase
import com.kevicsalazar.spring.domain.RegisterUseCase
import com.kevicsalazar.spring.utils.Abort
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/patient")
class PatientController(val useCase1: LoginUseCase,
                        val useCase2: RegisterUseCase,
                        val useCase3: ProfileUseCase,
                        val useCase4: AuthUseCase) {

    @PostMapping("/login")
    fun login(@RequestBody req: ObjectNode): Mono<ObjectNode> {

        val document = req.get("document")?.asText() ?: throw Abort.badRequest()
        val passCode = req.get("passCode")?.asText() ?: throw Abort.badRequest()

        return useCase1.login(document, passCode)
    }

    @PostMapping("/register")
    fun register(@RequestBody req: ObjectNode): Mono<ObjectNode> {

        val firstName = req.get("firstName")?.asText() ?: throw Abort.badRequest()
        val lastName  = req.get("lastName")?.asText()  ?: throw Abort.badRequest()
        val document  = req.get("document")?.asText()  ?: throw Abort.badRequest()
        val passCode  = req.get("passCode")?.asText()  ?: throw Abort.badRequest()

        return useCase2.register(firstName, lastName, document, passCode)
    }

    @GetMapping("/profile")
    fun profile(@RequestHeader("Authorization") auth: String?): Mono<ObjectNode> {

        val id = useCase4.patientId(auth) ?: throw Abort.unauthorized()

        return useCase3.profile(id)
    }

    @PostMapping("/profile")
    fun update(@RequestHeader("Authorization") auth: String?, @RequestBody req: ObjectNode): Mono<ObjectNode> {

        val id = useCase4.patientId(auth) ?: throw Abort.unauthorized()

        return useCase3.update(id)
    }

}