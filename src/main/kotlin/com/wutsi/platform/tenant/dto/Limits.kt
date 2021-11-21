package com.wutsi.platform.tenant.dto

import kotlin.Double

public data class Limits(
    public val minCashin: Double = 0.0,
    public val minCashout: Double = 0.0
)
