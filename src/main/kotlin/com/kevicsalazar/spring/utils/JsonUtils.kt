package com.kevicsalazar.spring.utils

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private fun objectNode(values: Iterator<Pair<String, *>>): ObjectNode {
    val obj = jacksonObjectMapper().createObjectNode()
    for ((key, value) in values) {
        obj.set(key, jacksonObjectMapper().convertValue(value!!))
    }
    return obj
}


fun objectNode(vararg values: Pair<String, *>) = objectNode(values.iterator())

fun <T : Any> T.obj() = jacksonObjectMapper().convertValue(this) as ObjectNode