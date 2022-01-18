package no.jlwcrews.servlets

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class CookieServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val cookie = Cookie("name", "JimBobLuke")
        cookie.domain = "lizard.com"
        val otherCookie = Cookie ("name", "JoeBobBriggs")
        otherCookie.domain = "secure.lizard.com"
        val cookieToSend = when(req?.requestURI){
            "/cookie" -> cookie
            "/otherCookie" -> otherCookie
            else -> throw Exception("Invalid path for cookie")
        }
        resp?.addCookie(cookieToSend)
    }
}