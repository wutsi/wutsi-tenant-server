package com.wutsi.platform.tenant.endpoint

import com.wutsi.platform.tenant.dto.GetTenantResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetTenantControllerTest : AbstractSecuredController() {
    @LocalServerPort
    val port: Int = 0

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

        val tenant = response.body!!.tenant
        assertEquals(1L, tenant.id)
        assertEquals("Wutsi", tenant.name)
        assertEquals("www.wutsi.com", tenant.domainName)
        assertEquals("support@wutsi.app", tenant.supportEmail)
        assertEquals("https://www.wutsi.app", tenant.installUrl)
        assertEquals("https://play.google.com/store/apps/details?id=com.wutsi.wutsi_wallet", tenant.installAndroidUrl)
        assertNull(tenant.installIOSUrl)
        assertEquals("https://wutsi-web-test.herokuapp.com", tenant.webappUrl)
        assertEquals("XAF", tenant.currency)
        assertEquals("#,###,##0", tenant.numberFormat)
        assertEquals("#,###,##0 FCFA", tenant.monetaryFormat)
        assertEquals("FCFA", tenant.currencySymbol)
        assertEquals("dd MMM yyy", tenant.dateFormat)
        assertEquals("HH:mm", tenant.timeFormat)
        assertEquals("dd MMM yyy, HH:mm", tenant.dateTimeFormat)
        assertEquals(listOf("CM", "GB", "CA"), tenant.countries)
        assertEquals(listOf("en", "fr"), tenant.languages)
        assertEquals(2, tenant.logos.size)

        assertEquals("PICTORIAL", tenant.logos[0].type)
        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/tenants/1/logos/pictorial-round.png",
            tenant.logos[0].url
        )

        assertEquals("WORDMARK", tenant.logos[1].type)
        assertEquals("http://localhost:0/static/wutsi-tenant-server/tenants/1/logos/wordmark.png", tenant.logos[1].url)

        assertEquals(500.0, tenant.limits.minCashin)
        assertEquals(500.0, tenant.limits.minCashout)

        val carriers = response.body!!.tenant.mobileCarriers
        assertEquals(2, carriers.size)

        assertEquals("mtn", carriers[0].code)
        assertEquals("MTN", carriers[0].name)
        assertEquals(listOf("CM"), carriers[0].countries)

        assertEquals(2, carriers[0].logos.size)
        assertEquals("PICTORIAL", carriers[0].logos[0].type)
        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/mobile-carriers/mtn/logos/pictorial.png",
            carriers[0].logos[0].url
        )
        assertEquals("WORDMARK", carriers[0].logos[1].type)
        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/mobile-carriers/mtn/logos/wordmark.png",
            carriers[0].logos[1].url
        )

        assertEquals(1, carriers[0].phonePrefixes.size)
        assertEquals("CM", carriers[0].phonePrefixes[0].country)
        assertEquals(
            listOf(
                "+23767",
                "+237650",
                "+237651",
                "+237652",
                "+237653",
                "+237654",
                "+237675",
                "+237676",
                "+237677",
                "+237678",
                "+237679",
                "+237680",
                "+237681",
            ),
            carriers[0].phonePrefixes[0].prefixes
        )

        assertEquals("orange", carriers[1].code)
        assertEquals("Orange", carriers[1].name)
        assertEquals(listOf("CM", "GB", "CA"), carriers[1].countries)

        assertEquals(2, carriers[1].logos.size)
        assertEquals("PICTORIAL", carriers[1].logos[0].type)
        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/mobile-carriers/orange/logos/pictorial.png",
            carriers[1].logos[0].url
        )
        assertEquals("WORDMARK", carriers[1].logos[1].type)
        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/mobile-carriers/orange/logos/wordmark.png",
            carriers[1].logos[1].url
        )

        assertEquals(3, carriers[1].phonePrefixes.size)
        assertEquals("CM", carriers[1].phonePrefixes[0].country)
        assertEquals(
            listOf("+23769", "+237655", "+237656", "+237657", "+237658", "+237659"),
            carriers[1].phonePrefixes[0].prefixes
        )
        assertEquals("GB", carriers[1].phonePrefixes[1].country)
        assertEquals(listOf("+44"), carriers[1].phonePrefixes[1].prefixes)
        assertEquals("CA", carriers[1].phonePrefixes[2].country)
        assertEquals(listOf("+1"), carriers[1].phonePrefixes[2].prefixes)

        assertEquals(3, tenant.fees.size)
        assertEquals("CHARGE", tenant.fees[0].transactionType)
        assertEquals(0.0, tenant.fees[0].amount)
        assertEquals(0.1, tenant.fees[0].percent)
        assertEquals(false, tenant.fees[0].applyToSender)
        assertNull(tenant.fees[0].paymentMethodType)

        assertEquals("CASHIN", tenant.fees[1].transactionType)
        assertEquals(0.0, tenant.fees[1].amount)
        assertEquals(0.0, tenant.fees[1].percent)
        assertEquals(true, tenant.fees[1].applyToSender)
        assertEquals("MOBILE", tenant.fees[1].paymentMethodType)

        assertEquals("CASHOUT", tenant.fees[2].transactionType)
        assertEquals(0.0, tenant.fees[2].amount)
        assertEquals(0.020, tenant.fees[2].percent)
        assertEquals(true, tenant.fees[2].applyToSender)
        assertEquals("MOBILE", tenant.fees[2].paymentMethodType)

        assertEquals(
            "http://localhost:0/static/wutsi-tenant-server/products/nopicture.png",
            tenant.product.defaultPictureUrl
        )

        assertEquals(
            listOf(
                16L, // Herve
                18L, // Eric - phone #2
                29L, // Eric - phone #1
                19L, // Louis
                23L, // Mathis
                39L, // Maison H
                47L, // MTN Retail Account #1
                86L, // Nadine
            ),
            tenant.testUserIds
        )
        assertEquals(
            listOf(
                "+237670000001", // MTN Test Account #1
                "+237670000002", // MTN Test Account #2
                "+237670000010", // MTN Test Account #3
                "+237670000011", // MTN Test Account #4
                "+237670000012", // MTN Test Account #5
                "+237690000001", // OM Test Account #1 - Maison H (business account)
                "+237690000002", // OM Test Account #2
                "+237690000010", // OM Test Account #3
                "+237690000011", // OM Test Account #4
            ),
            tenant.testPhoneNumbers
        )

        assertEquals(
            listOf(
                "ACCOUNT",
                "ACCOUNT_MOBILE_MONEY",
                "CASHIN",
                "CASHOUT",
                "CART",
                "CONTACT",
                "NEWS",
                "ORDER",
                "ORDER_PAYMENT",
                "SCAN",
                "SEND",
                "SHIPPING",
                "SHIPPING_IN_STORE_PICKUP",
                "SHIPPING_LOCAL_DELIVERY",
                "STORE",
                "TRANSACTION_HISTORY",
                "SWITCH_ENVIRONMENT",
            ),
            tenant.toggles.map { it.name }
        )
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
