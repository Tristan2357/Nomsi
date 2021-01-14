import de.nomsi.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/api/test").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Test respond Text", response.content)
            }
        }
    }
}
