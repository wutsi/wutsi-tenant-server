package com.wutsi.platform.tenant.entity

public data class LimitsEntity(
    public var country: String = "",
    public var minCashin: Double = 0.0,
    public var minCashout: Double = 0.0
)
