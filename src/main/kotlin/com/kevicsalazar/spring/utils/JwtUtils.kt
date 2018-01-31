package com.kevicsalazar.spring.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

fun String.claims(secretKey: String): Claims? {
    return try {
        assert(startsWith("Bearer "))
        val token = substring(7)
        Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body
    } catch (e: Exception) {
        return null
    }
}

fun Claims.string(key: String): String? {
    return get(key).toString()
}

fun JwtBuilder.sub(sub: String) = apply {
    setSubject(sub)
}

fun JwtBuilder.claims(vararg claims: Pair<String, *>) = apply {
    claims.forEach { claim(it.first, it.second) }
}

fun JwtBuilder.sign(secretKey: String) = apply {
    setIssuedAt(Date())
    signWith(SignatureAlgorithm.HS256, secretKey)
}