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
        assertEquals(listOf("CM"), tenant.countries)
        assertEquals(listOf("en", "fr"), tenant.languages)
        assertEquals(2, tenant.logos.size)

        assertEquals("PICTORIAL", tenant.logos[0].type)
        assertEquals("http://localhost:0/static/tenants/1/logos/pictorial.png", tenant.logos[0].url)

        assertEquals("WORDMARK", tenant.logos[1].type)
        assertEquals("http://localhost:0/static/tenants/1/logos/wordmark.png", tenant.logos[1].url)

        val carriers = response.body.tenant.mobileCarriers
        assertEquals(2, carriers.size)

        assertEquals("mtn", carriers[0].code)
        assertEquals("MTN", carriers[0].name)
        assertEquals(listOf("CM"), carriers[0].countries)

        assertEquals(1, carriers[0].logos.size)
        assertEquals("PICTORIAL", carriers[0].logos[0].type)
        assertEquals("http://localhost:0/static/mobile-carriers/mtn/logos/pictorial.png", carriers[0].logos[0].url)

        assertEquals(1, carriers[0].phonePrefixes.size)
        assertEquals("CM", carriers[0].phonePrefixes[0].country)
        assertEquals(listOf("+237745", "+237746", "+237747", "+237748", "+237749", "+23775", "+23777"), carriers[0].phonePrefixes[0].prefixes)

        assertEquals("orange", carriers[1].code)
        assertEquals("Orange", carriers[1].name)
        assertEquals(listOf("CM"), carriers[1].countries)

        assertEquals(1, carriers[1].logos.size)
        assertEquals("PICTORIAL", carriers[1].logos[0].type)
        assertEquals("http://localhost:0/static/mobile-carriers/orange/logos/pictorial.png", carriers[1].logos[0].url)

        assertEquals(1, carriers[1].phonePrefixes.size)
        assertEquals("CM", carriers[1].phonePrefixes[0].country)
        assertEquals(listOf("+237940", "+237941", "+237942", "+237943", "+237944", "+23796", "+23799"), carriers[1].phonePrefixes[0].prefixes)
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
