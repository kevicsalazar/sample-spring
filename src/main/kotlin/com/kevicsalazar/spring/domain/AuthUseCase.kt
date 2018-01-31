package com.kevicsalazar.spring.domain

import com.kevicsalazar.spring.config.PropertiesConfig
import com.kevicsalazar.spring.utils.claims
import com.kevicsalazar.spring.utils.sign
import com.kevicsalazar.spring.utils.string
import com.kevicsalazar.spring.utils.sub
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component

@Component
class AuthUseCase(val props: PropertiesConfig) {

    fun patientToken(id: String): String {
        return Jwts.builder().sub(id).claims(
                "role" to "patient"
        ).sign(props.jwtSecretKey).compact()
    }

    fun patientId(auth: String?): String? {
        return auth?.claims(props.jwtSecretKey)?.string("sub")
    }

}