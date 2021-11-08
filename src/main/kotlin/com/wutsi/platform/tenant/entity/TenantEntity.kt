package com.wutsi.platform.tenant.entity

public data class TenantEntity(
    public var id: Long = 0,
    public var name: String = "",
    public var domainName: String = "",
    public var currency: String = "",
    public var countries: List<String> = emptyList(),
    public var languages: List<String> = emptyList(),
    public var logos: List<LogoEntity> = emptyList(),
    public var mobileCarriers: List<String> = emptyList()
)
