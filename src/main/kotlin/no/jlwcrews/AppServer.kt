package no.jlwcrews

import no.jlwcrews.servlets.ApiServlet
import no.jlwcrews.servlets.CookieServlet
import no.jlwcrews.servlets.FreeMarkerServlet
import no.jlwcrews.servlets.PingServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AppServer(port: Int) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val server: Server = Server(port)

    fun startServer() {
        val handlerCollection = ContextHandlerCollection()
        handlerCollection.addHandler(setServlets())
        server.handler = handlerCollection
        server.start()
        logger.info("Starting server...")
    }

    private fun setServlets(): WebAppContext {
        val webAppContext = WebAppContext()
        webAppContext.contextPath = "/"
        webAppContext.resourceBase = "webapp/target/webapp"
        webAppContext.addServlet(ServletHolder(PingServlet()), "/ping/*")
        webAppContext.addServlet(ServletHolder(ApiServlet()), "/api/*")
        webAppContext.addServlet(ServletHolder(CookieServlet()), "/cookie/*")
        webAppContext.addServlet(ServletHolder(FreeMarkerServlet()), "/freemarker/*")
        return webAppContext
    }

    fun stopServer() {
        server.stop()
        logger.info("Shutting down server...")
    }
}