package no.jlwcrews

import no.jlwcrews.servlets.ApiServlet
import no.jlwcrews.servlets.CookieServlet
import no.jlwcrews.servlets.FreeMarkerServlet
import no.jlwcrews.servlets.PingServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandlerCollection
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.util.resource.Resource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AppServer(port: Int) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val server: Server = Server(port)

    fun startServer() {
        val handlerCollection = ContextHandlerCollection()
        handlerCollection.addHandler(setStaticContent())
        handlerCollection.addHandler(setServlets())
        server.handler = handlerCollection
        server.start()
        logger.info("Starting server...")
    }

    private fun setStaticContent(): ServletContextHandler {
        val contentUrl = "/src/main/resources/webapp"
        val servletContextHandler = ServletContextHandler()
        servletContextHandler.contextPath = "/"
        servletContextHandler.welcomeFiles = arrayOf("index.html")
        servletContextHandler.baseResource = Resource.newResource(contentUrl)
        servletContextHandler.addServlet(DefaultServlet::class.java, "/")
        return servletContextHandler
    }

    private fun setServlets(): ServletContextHandler {
        val contextHandler = ServletContextHandler()

        contextHandler.addServlet(ServletHolder(PingServlet()), "/ping/*")
        contextHandler.addServlet(ServletHolder(ApiServlet()), "/api/*")
        contextHandler.addServlet(ServletHolder(CookieServlet()), "/cookie/*")
        contextHandler.addServlet(ServletHolder(FreeMarkerServlet()), "/freemarker/*")
        return contextHandler
    }

    fun stopServer() {
        server.stop()
        logger.info("Shutting down server...")
    }
}