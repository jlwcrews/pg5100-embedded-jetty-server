package no.jlwcrews.servlets

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import no.jlwcrews.models.User
import java.io.File
import java.time.LocalDateTime


class FreeMarkerServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val coolUserMap = userToMap(
            User(
                "jlwcrews",
                "jimbobhas10toes",
                LocalDateTime.now(),
                "jlwcrews@gmail.com"
                ))
        val config = createFreemarkerConfig()
        val template = config.getTemplate("user.ftl")
        template.process(coolUserMap, resp?.writer)
    }

    private fun userToMap(user: User): Map<String, User> {
        return mapOf("user" to user)
    }

    private fun createFreemarkerConfig(): Configuration {
        val cfg = Configuration(Configuration.VERSION_2_3_29)
        cfg.setDirectoryForTemplateLoading(File("src/main/resources/templates"))
        cfg.defaultEncoding = "UTF-8"
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        cfg.logTemplateExceptions = false
        cfg.wrapUncheckedExceptions = true
        cfg.fallbackOnNullLoopVariable = false
        return cfg
    }
}