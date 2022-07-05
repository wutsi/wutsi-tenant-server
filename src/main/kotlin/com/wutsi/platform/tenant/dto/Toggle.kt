package com.wutsi.platform.tenant.dto

import kotlin.Long
import kotlin.String
import kotlin.collections.List

public data class Toggle(
    public val name: String = "",
    public val description: String? = null,
    public val environments: List<String> = emptyList(),
    public val tenantIds: List<Long> = emptyList(),
)
