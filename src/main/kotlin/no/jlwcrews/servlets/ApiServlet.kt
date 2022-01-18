package no.jlwcrews.servlets

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import no.jlwcrews.servlets.api.get.GetApiStatus
import no.jlwcrews.servlets.api.get.GetJsonResponse
import no.jlwcrews.servlets.api.post.PostUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ApiServlet: HttpServlet() {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpServletResponse.SC_OK
        val textResponse = when(req.pathInfo){
            "/status" -> GetApiStatus().getApiStatus()
            "/json" -> GetJsonResponse().getJsonResponse()
            else -> "<h1>Welcome to the boring part of the api</h1>"
        }
        resp.writer?.write(textResponse)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.status = HttpServletResponse.SC_OK
        val textResponse = when(req.pathInfo){
            "/postUser" -> PostUser().postUser(req)
            else -> "<h1>No payload</h1>"
        }
        resp.writer?.write(textResponse)
    }
}