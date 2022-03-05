package com.wutsi.platform.tenant.entity

public data class FeeEntity(
    public var transactionType: String = "",
    public var fromRetail: Boolean? = null,
    public var toRetail: Boolean? = null,
    public var toBusiness: Boolean? = null,
    public var threshold: Double = 0.0,
    public var amount: Double = 0.0,
    public var percent: Double = 0.0,
    public var applyToSender: Boolean = false,
)
