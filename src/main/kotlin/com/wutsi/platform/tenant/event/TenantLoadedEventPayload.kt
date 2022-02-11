package com.wutsi.platform.tenant.event

data class TenantLoadedEventPayload(
    val tenantIds: List<Long>
)
