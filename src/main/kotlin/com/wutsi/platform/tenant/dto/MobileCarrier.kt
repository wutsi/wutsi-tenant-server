package com.wutsi.platform.tenant.dto

import kotlin.String
import kotlin.collections.List

public data class MobileCarrier(
    public val code: String = "",
    public val name: String = "",
    public val countries: List<String> = emptyList(),
    public val logos: List<Logo> = emptyList(),
    public val phonePrefixes: List<PhonePrefix> = emptyList(),
)
