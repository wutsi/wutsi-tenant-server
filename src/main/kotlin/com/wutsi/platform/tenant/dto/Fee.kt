package com.wutsi.platform.tenant.dto

import kotlin.Boolean
import kotlin.Double
import kotlin.String

public data class Fee(
    public val transactionType: String = "",
    public val fromRetail: Boolean? = null,
    public val toRetail: Boolean? = null,
    public val toBusinees: Boolean? = null,
    public val threshold: Double = 0.0,
    public val amount: Double = 0.0,
    public val percent: Double = 0.0,
    public val applyToSender: Boolean = false
)
