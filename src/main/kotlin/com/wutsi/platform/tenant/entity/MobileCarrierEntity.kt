package com.wutsi.platform.tenant.entity

public data class MobileCarrierEntity(
    public var code: String = "",
    public var name: String = "",
    public var countries: List<String> = emptyList(),
    public var logos: List<LogoEntity> = emptyList(),
    public var phonePrefixes: Map<String, List<String>> = emptyMap()
)
