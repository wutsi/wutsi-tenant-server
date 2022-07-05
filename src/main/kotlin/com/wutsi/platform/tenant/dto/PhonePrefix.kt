package com.wutsi.platform.tenant.dto

import kotlin.String
import kotlin.collections.List

public data class PhonePrefix(
    public val country: String = "",
    public val prefixes: List<String> = emptyList(),
)
