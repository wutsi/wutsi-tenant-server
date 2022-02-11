package com.wutsi.platform.tenant.service

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.wutsi.platform.core.stream.EventStream
import com.wutsi.platform.tenant.endpoint.AbstractSecuredController
import com.wutsi.platform.tenant.event.EventURN
import com.wutsi.platform.tenant.event.TenantLoadedEventPayload
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class TenantServiceTest : AbstractSecuredController() {
    @MockBean
    private lateinit var eventStream: EventStream

    @Test
    fun loadMessageFired() {
        val payload = argumentCaptor<TenantLoadedEventPayload>()
        verify(eventStream).publish(eq(EventURN.TENANT_LOADED.urn), payload.capture())
        assertEquals(listOf(1L), payload.firstValue.tenantIds)
    }
}
