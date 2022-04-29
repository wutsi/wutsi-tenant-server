package com.wutsi.platform.tenant.`delegate`

import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType.PARAMETER_TYPE_PATH
import com.wutsi.platform.core.error.exception.NotFoundException
import com.wutsi.platform.tenant.dto.GetTenantResponse
import com.wutsi.platform.tenant.service.TenantService
import com.wutsi.platform.tenant.util.ErrorURN
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class GetTenantDelegate(
    private val service: TenantService,
    private val env: Environment
) {
    fun invoke(id: Long): GetTenantResponse {
        val carriers = service.mobileCarriers().map { it.code to it }.toMap()
        val toggles = service.toggles()

        return GetTenantResponse(
            tenant = service.tenants().find { it.id == id }?.toTenant(carriers, toggles, env)
                ?: throw NotFoundException(
                    error = Error(
                        code = ErrorURN.TENANT_NOT_FOUND.urn,
                        parameter = Parameter(
                            name = "id",
                            type = PARAMETER_TYPE_PATH,
                            value = id
                        )
                    )
                )
        )
    }
}
