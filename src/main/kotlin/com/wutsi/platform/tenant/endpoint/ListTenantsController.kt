package com.wutsi.platform.tenant.endpoint

import com.wutsi.platform.tenant.`delegate`.ListTenantsDelegate
import com.wutsi.platform.tenant.dto.ListTenantResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.RestController

@RestController
public class ListTenantsController(
    public val `delegate`: ListTenantsDelegate,
) {
    @GetMapping("/v1/tenants")
    @PreAuthorize(value = "hasAuthority('tenant-read')")
    public fun invoke(): ListTenantResponse = delegate.invoke()
}
