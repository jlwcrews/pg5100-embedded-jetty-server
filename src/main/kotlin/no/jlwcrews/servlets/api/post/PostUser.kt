package no.jlwcrews.servlets.api.post

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.http.HttpServletRequest
import no.jlwcrews.models.User
import no.jlwcrews.servlets.api.Command

class PostUser: Command {

    fun postUser(req: HttpServletRequest): String {
        val body = req.reader
        val mapper = jacksonObjectMapper()
        val user = mapper.readValue(body, User::class.java)
        return "Ok"
    }
}