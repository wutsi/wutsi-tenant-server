package com.wutsi.platform.tenant.dto

import kotlin.Long
import kotlin.String
import kotlin.collections.List

public data class Tenant(
    public val id: Long = 0,
    public val name: String = "",
    public val domainName: String = "",
    public val webappUrl: String = "",
    public val supportEmail: String = "",
    public val installUrl: String = "",
    public val installAndroidUrl: String? = null,
    public val installIOSUrl: String? = null,
    public val currency: String = "",
    public val currencySymbol: String = "",
    public val numberFormat: String = "",
    public val monetaryFormat: String = "",
    public val dateFormat: String = "",
    public val timeFormat: String = "",
    public val dateTimeFormat: String = "",
    public val limits: Limits = Limits(),
    public val countries: List<String> = emptyList(),
    public val languages: List<String> = emptyList(),
    public val logos: List<Logo> = emptyList(),
    public val mobileCarriers: List<MobileCarrier> = emptyList(),
    public val fees: List<Fee> = emptyList(),
    public val product: Product = Product(),
    public val toggles: List<Toggle> = emptyList()
)
