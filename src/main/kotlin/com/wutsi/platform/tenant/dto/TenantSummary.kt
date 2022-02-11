package com.wutsi.platform.tenant.dto

import kotlin.Long
import kotlin.String

public data class TenantSummary(
    public val id: Long = 0,
    public val name: String = "",
    public val domainName: String = "",
    public val webappUrl: String = ""
)
