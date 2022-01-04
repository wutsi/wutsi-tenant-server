package com.wutsi.platform.tenant.entity

public data class FeeEntity(
    public var accountType: String = "",
    public var transactionType: String = "",
    public var retail: Boolean? = null,
    public var amount: Double = 0.0,
    public var percent: Double = 0.0
)
