package com.wutsi.platform.tenant.entity

import org.springframework.core.env.Environment

public data class ToggleEntity(
    public var name: String = "",
    public var description: String? = null,
    public var tenantIds: List<Long> = emptyList(),
    public var environments: List<String> = emptyList(),
) {
    fun isEnabled(tenant: TenantEntity, env: Environment): Boolean {
        return (tenantIds.isEmpty() || tenantIds.contains(tenant.id)) &&
            (environments.isEmpty() || env.activeProfiles.any { environments.contains(it) })
    }
}
