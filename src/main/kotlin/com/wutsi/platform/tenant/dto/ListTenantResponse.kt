package com.wutsi.platform.tenant.dto

import kotlin.collections.List

public data class ListTenantResponse(
    public val tenants: List<TenantSummary> = emptyList(),
)
