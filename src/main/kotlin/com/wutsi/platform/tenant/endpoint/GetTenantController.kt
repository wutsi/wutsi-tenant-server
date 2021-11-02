package com.wutsi.platform.tenant.endpoint

import com.wutsi.platform.tenant.`delegate`.GetTenantDelegate
import com.wutsi.platform.tenant.dto.GetTenantResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.`annotation`.GetMapping
import org.springframework.web.bind.`annotation`.PathVariable
import org.springframework.web.bind.`annotation`.RestController
import javax.validation.constraints.NotNull
import kotlin.Long

@RestController
public class GetTenantController(
    private val `delegate`: GetTenantDelegate
) {
    @GetMapping("/v1/tenants/{id}")
    @PreAuthorize(value = "hasAuthority('tenant-read')")
    public fun invoke(@PathVariable(name = "id") @NotNull id: Long): GetTenantResponse =
        delegate.invoke(id)
}
