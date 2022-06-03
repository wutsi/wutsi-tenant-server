package com.wutsi.platform.tenant.dto

import kotlin.Double
import kotlin.String

public data class Fee(
    public val transactionType: String = "",
    public val amount: Double = 0.0,
    public val percent: Double = 0.0
)
