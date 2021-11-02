package com.wutsi.platform.tenant.delegate

import com.wutsi.platform.tenant.dto.Logo
import com.wutsi.platform.tenant.dto.Tenant
import com.wutsi.platform.tenant.service.LogoEntity
import com.wutsi.platform.tenant.service.TenantEntity

fun TenantEntity.toTenant() = Tenant(
    id = this.id,
    domainName = this.domainName,
    currency = this.currency,
    name = this.name,
    countries = this.countries,
    languages = this.languages,
    logos = this.logos.map { it.toLogo() }
)

fun LogoEntity.toLogo() = Logo(
    type = this.type,
    url = this.url
)
