package com.kevicsalazar.spring.utils

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


val MSG01 = Pair(101, "Bad Request")
val MSG02 = Pair(102, "Unauthorized")
val MSG04 = Pair(103, "Invalid Access Data")
val MSG40 = Pair(140, "Internal Error")

object Abort {

    fun badRequest(error: Pair<Int, String> = MSG01) = BadRequestException(error)

    fun unauthorized(error: Pair<Int, String> = MSG02) = UnauthorizedException(error)

}

open class CustomException(error: Pair<Int, String>) : RuntimeException(error.second) {

    val code = error.first

}

open class BadRequestException(error: Pair<Int, String>) : CustomException(error)

open class UnauthorizedException(error: Pair<Int, String>) : CustomException(error)

@ControllerAdvice
class CustomRestExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleException(ex: Exception): ResponseEntity<Any> {
        ex.printStackTrace()
        val headers = HttpHeaders()
        val status = when (ex) {
            is BadRequestException   -> HttpStatus.BAD_REQUEST
            is UnauthorizedException -> HttpStatus.UNAUTHORIZED
            else                     -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        val error = ex.message ?: "Unknown Error"
        val code = (ex as? CustomException)?.code ?: 140
        val body = objectNode(
                "error" to error,
                "code" to code
        )
        return ResponseEntity(body, headers, status)
    }

}