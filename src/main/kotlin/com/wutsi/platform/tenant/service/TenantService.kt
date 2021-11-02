package com.wutsi.platform.tenant.service

import com.wutsi.platform.tenant.dto.Tenant
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@ConfigurationProperties(prefix = "wutsi")
class TenantRepository {
    var tenants: List<Tenant> = emptyList()
}

@Service
@EnableConfigurationProperties(TenantRepository::class)
class TenantService(private val repository: TenantRepository) {
    fun all(): List<Tenant> = repository.tenants
}
