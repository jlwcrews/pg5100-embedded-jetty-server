import no.jlwcrews.AppServer
import org.junit.jupiter.api.*
import java.net.HttpURLConnection
import java.net.URL

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
private class ServerTest {

    @BeforeEach
    fun initializeServer(){
        AppServer().startServer(9500)
    }

    @AfterEach
    fun teardownServer(){
        AppServer().stopServer()
    }

    @Test
    fun serverShouldStart(){
        val url = URL("http://localhost:9500/ping")
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        assert(connection.responseCode == 200)
    }
}