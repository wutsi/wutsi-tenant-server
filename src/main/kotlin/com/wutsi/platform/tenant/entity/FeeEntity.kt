package com.wutsi.platform.tenant.entity

data class FeeEntity(
    var transactionType: String = "",
    var amount: Double = 0.0,
    var percent: Double = 0.0,
)
