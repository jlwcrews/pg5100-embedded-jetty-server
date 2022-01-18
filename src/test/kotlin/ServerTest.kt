import no.jlwcrews.AppServer
import org.junit.jupiter.api.*
import java.net.HttpURLConnection
import java.net.URL

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
private class ServerTest {

    @BeforeEach
    fun initializeServer(){
        AppServer(9000).startServer()
    }

    @AfterEach
    fun teardownServer(){
        AppServer(9000).stopServer()
    }

    @Test
    fun serverShouldStart(){
        val url = URL("http://localhost:9000/ping")
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()
        assert(connection.responseCode == 200)
    }
}