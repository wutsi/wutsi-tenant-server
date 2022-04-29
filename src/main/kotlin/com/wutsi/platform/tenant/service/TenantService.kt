package com.wutsi.platform.tenant.service

import com.wutsi.platform.core.stream.EventStream
import com.wutsi.platform.tenant.entity.MobileCarrierEntity
import com.wutsi.platform.tenant.entity.TenantEntity
import com.wutsi.platform.tenant.entity.ToggleEntity
import com.wutsi.platform.tenant.event.EventURN
import com.wutsi.platform.tenant.event.TenantLoadedEventPayload
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@ConfigurationProperties(prefix = "wutsi")
class TenantRepository {
    var tenants: List<TenantEntity> = emptyList()
    var mobileCarriers: List<MobileCarrierEntity> = emptyList()
    var toggles: List<ToggleEntity> = emptyList()
}

@Service
@EnableConfigurationProperties(TenantRepository::class)
class TenantService(
    private val repository: TenantRepository,
    private val eventStream: EventStream
) {
    fun tenants(): List<TenantEntity> = repository.tenants
    fun mobileCarriers(): List<MobileCarrierEntity> = repository.mobileCarriers
    fun toggles(): List<ToggleEntity> = repository.toggles

    @PostConstruct
    fun init() {
        eventStream.publish(
            EventURN.TENANT_LOADED.urn,
            TenantLoadedEventPayload(tenantIds = tenants().map { it.id })
        )
    }
}
