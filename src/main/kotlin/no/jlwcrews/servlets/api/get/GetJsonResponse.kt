package no.jlwcrews.servlets.api.get

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import no.jlwcrews.models.User
import no.jlwcrews.servlets.api.Query
import java.time.LocalDateTime

class GetJsonResponse: Query {

    fun getJsonResponse(): String {
        val user = User("dicknose", "jackhole", LocalDateTime.now(), "dicknose@nose.dick")
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(user)
    }

}