package com.wutsi.platform.tenant.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@ConfigurationProperties(prefix = "wutsi")
class TenantRepository {
    var tenants: List<TenantEntity> = emptyList()
}

public data class TenantEntity(
    public var id: Long = 0,
    public var name: String = "",
    public var domainName: String = "",
    public var currency: String = "",
    public var countries: List<String> = emptyList(),
    public var languages: List<String> = emptyList(),
    public var logos: List<LogoEntity> = emptyList()
)

public data class LogoEntity(
    public var type: String = "",
    public var url: String = ""
)

@Service
@EnableConfigurationProperties(TenantRepository::class)
class TenantService(private val repository: TenantRepository) {
    fun all(): List<TenantEntity> = repository.tenants
}
