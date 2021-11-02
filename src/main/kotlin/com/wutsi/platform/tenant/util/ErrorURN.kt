package com.wutsi.platform.tenant.util

import com.wutsi.platform.core.util.URN

enum class ErrorURN(val urn: String) {
    TENANT_NOT_FOUND(URN.of("error", "tenant", "tenant-not-found").value),
}
