package com.wutsi.platform.tenant.`delegate`

import com.wutsi.platform.core.error.Error
import com.wutsi.platform.core.error.Parameter
import com.wutsi.platform.core.error.ParameterType.PARAMETER_TYPE_PATH
import com.wutsi.platform.core.error.exception.NotFoundException
import com.wutsi.platform.tenant.dto.GetTenantResponse
import com.wutsi.platform.tenant.service.TenantService
import com.wutsi.platform.tenant.util.ErrorURN
import org.springframework.stereotype.Service

@Service
class GetTenantDelegate(private val service: TenantService) {
    fun invoke(id: Long): GetTenantResponse =
        GetTenantResponse(
            tenant = service.all().find { it.id == id }
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
