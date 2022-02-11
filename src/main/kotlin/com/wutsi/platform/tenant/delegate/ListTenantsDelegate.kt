package com.wutsi.platform.tenant.`delegate`

import com.wutsi.platform.tenant.dto.ListTenantResponse
import com.wutsi.platform.tenant.service.TenantService
import org.springframework.stereotype.Service

@Service
public class ListTenantsDelegate(private val service: TenantService) {
    public fun invoke(): ListTenantResponse =
        ListTenantResponse(
            tenants = service.tenants().map { it.toTenantSummary() }
        )
}
