package no.jlwcrews

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        AppServer(8080).startServer()
    }
}
