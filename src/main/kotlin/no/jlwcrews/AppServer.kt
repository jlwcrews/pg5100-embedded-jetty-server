package no.jlwcrews

import no.jlwcrews.servlets.ApiServlet
import no.jlwcrews.servlets.CookieServlet
import no.jlwcrews.servlets.FreeMarkerServlet
import no.jlwcrews.servlets.PingServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.util.resource.Resource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AppServer() {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val server: Server = Server()

    fun startServer() {
        System.setProperty("org.eclipse.jetty.LEVEL", "INFO")
        val connector = ServerConnector(server)
        connector.port = 8080
        server.addConnector(connector)
        val contextHandler = ServletContextHandler(ServletContextHandler.SESSIONS);
        server.handler = contextHandler
        setStaticContent(contextHandler)
        setServlets(contextHandler)
        server.start()
        server.join()
    }

    private fun setStaticContent(contextHandler: ServletContextHandler) {
        val webAppLocation = this::class.java.classLoader.getResource("webapp/")?.toURI()
        contextHandler.baseResource = Resource.newResource(webAppLocation)
        contextHandler.contextPath = "/"
        val webAppServlet = ServletHolder("default", DefaultServlet::class.java)
        webAppServlet.setInitParameter("dirAllowed", "true")
        contextHandler.addServlet(webAppServlet, "/*")
    }

    private fun setServlets(contextHandler: ServletContextHandler) {
        contextHandler.addServlet(ServletHolder(PingServlet()), "/ping/*")
        contextHandler.addServlet(ServletHolder(ApiServlet()), "/api/*")
        contextHandler.addServlet(ServletHolder(CookieServlet()), "/cookie/*")
        contextHandler.addServlet(ServletHolder(FreeMarkerServlet()), "/freemarker/*")
    }

    fun stopServer() {
        server.stop()
        logger.info("Shutting down server...")
    }
}