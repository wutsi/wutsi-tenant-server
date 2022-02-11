package com.wutsi.platform.tenant.endpoint

import com.wutsi.platform.tenant.dto.ListTenantResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListTenantsControllerTest : AbstractSecuredController() {
    @LocalServerPort
    public val port: Int = 0

    private lateinit var rest: RestTemplate

    @BeforeEach
    override fun setUp() {
        super.setUp()

        rest = createResTemplate(listOf("tenant-read"), subjectId = 100)
    }

    @Test
    fun `list tenants`() {
        val url = "http://localhost:$port/v1/tenants"
        val response = rest.getForEntity(url, ListTenantResponse::class.java)

        assertEquals(200, response.statusCodeValue)

        val tenants = response.body!!.tenants
        assertEquals(1, tenants.size)

        val tenant = tenants[0]
        assertEquals(1L, tenant.id)
        assertEquals("Wutsi", tenant.name)
        assertEquals("www.wutsi.com", tenant.domainName)
    }
}
