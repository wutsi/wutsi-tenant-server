package com.wutsi.platform.tenant.dto

import kotlin.Long
import kotlin.String
import kotlin.collections.List

public data class Tenant(
    public val id: Long = 0,
    public val name: String = "",
    public val domainName: String = "",
    public val installUrl: String = "",
    public val currency: String = "",
    public val numberFormat: String = "",
    public val monetaryFormat: String = "",
    public val limits: Limits = Limits(),
    public val countries: List<String> = emptyList(),
    public val languages: List<String> = emptyList(),
    public val logos: List<Logo> = emptyList(),
    public val mobileCarriers: List<MobileCarrier> = emptyList()
)
