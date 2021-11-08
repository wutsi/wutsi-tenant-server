package com.wutsi.platform.tenant.service

import com.wutsi.platform.tenant.entity.MobileCarrierEntity
import com.wutsi.platform.tenant.entity.TenantEntity
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@ConfigurationProperties(prefix = "wutsi")
class TenantRepository {
    var tenants: List<TenantEntity> = emptyList()
    var mobileCarriers: List<MobileCarrierEntity> = emptyList()
}

@Service
@EnableConfigurationProperties(TenantRepository::class)
class TenantService(private val repository: TenantRepository) {
    fun tenants(): List<TenantEntity> = repository.tenants
    fun mobileCarriers(): List<MobileCarrierEntity> = repository.mobileCarriers
}
