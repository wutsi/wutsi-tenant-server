package com.wutsi.platform.tenant.endpoint

import com.wutsi.platform.tenant.dto.GetTenantResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetTenantControllerTest : AbstractSecuredController() {
    @LocalServerPort
    public val port: Int = 0

    private lateinit var rest: RestTemplate

    @BeforeEach
    override fun setUp() {
        super.setUp()

        rest = createResTemplate(listOf("tenant-read"), subjectId = 100)
    }

    @Test
    fun `get a tenant`() {
        val url = "http://localhost:$port/v1/tenants/1"
        val response = rest.getForEntity(url, GetTenantResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val tenant = response.body.tenant
        assertEquals(1L, tenant.id)
        assertEquals("Wutsi", tenant.name)
        assertEquals("www.wutsi.com", tenant.domainName)
        assertEquals("XAF", tenant.currency)
        assertEquals(listOf("CA", "CM", "DE", "FR", "GB", "US"), tenant.countries)
        assertEquals(listOf("en", "fr"), tenant.languages)
        assertEquals(2, tenant.logos.size)

        assertEquals("PICTORIAL", tenant.logos[0].type)
        assertEquals("http://localhost:0/static/tenants/1/logos/pictorial.png", tenant.logos[0].url)

        assertEquals("WORDMARK", tenant.logos[1].type)
        assertEquals("http://localhost:0/static/tenants/1/logos/wordmark.png", tenant.logos[1].url)
    }

    @Test
    fun `tenant not found`() {
        val url = "http://localhost:$port/v1/tenants/9999"
        val ex = assertThrows<HttpClientErrorException> {
            rest.getForEntity(url, GetTenantResponse::class.java)
        }

        assertEquals(404, ex.rawStatusCode)
    }

    @Test
    fun `unauthenticated call found`() {
        val url = "http://localhost:$port/v1/tenants/1"

        rest = RestTemplate()
        val ex = assertThrows<HttpClientErrorException> {
            rest.getForEntity(url, GetTenantResponse::class.java)
        }

        assertEquals(401, ex.rawStatusCode)
    }

    @Test
    fun `bad permisn call found`() {
        val url = "http://localhost:$port/v1/tenants/1"

        rest = createResTemplate(listOf("xxx"), subjectId = 100)
        val ex = assertThrows<HttpClientErrorException> {
            rest.getForEntity(url, GetTenantResponse::class.java)
        }

        assertEquals(403, ex.rawStatusCode)
    }
}
