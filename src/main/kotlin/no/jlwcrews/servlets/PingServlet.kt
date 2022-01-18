package no.jlwcrews.servlets

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException


class PingServlet: HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.status = HttpServletResponse.SC_OK
        val writer = resp?.writer
        writer?.write("<h1>Ping</h1>")
    }

}