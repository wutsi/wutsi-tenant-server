package com.wutsi.platform.tenant.entity

import org.springframework.core.env.Environment

data class ToggleEntity(
    var name: String = "",
    var description: String? = null,
    var enabled: Boolean = true,
    var tenantIds: List<Long> = emptyList(),
    var environments: List<String> = emptyList(),
) {
    fun isEnabled(tenant: TenantEntity, env: Environment): Boolean {
        return enabled &&
            (tenantIds.isEmpty() || tenantIds.contains(tenant.id)) &&
            (environments.isEmpty() || env.activeProfiles.any { environments.contains(it) })
    }
}
