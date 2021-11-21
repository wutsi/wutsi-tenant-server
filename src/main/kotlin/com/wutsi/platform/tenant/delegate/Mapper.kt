package com.wutsi.platform.tenant.delegate

import com.wutsi.platform.tenant.dto.Limits
import com.wutsi.platform.tenant.dto.Logo
import com.wutsi.platform.tenant.dto.MobileCarrier
import com.wutsi.platform.tenant.dto.PhonePrefix
import com.wutsi.platform.tenant.dto.Tenant
import com.wutsi.platform.tenant.entity.LimitsEntity
import com.wutsi.platform.tenant.entity.LogoEntity
import com.wutsi.platform.tenant.entity.MobileCarrierEntity
import com.wutsi.platform.tenant.entity.TenantEntity

fun TenantEntity.toTenant(carriers: Map<String, MobileCarrierEntity>) = Tenant(
    id = this.id,
    domainName = this.domainName,
    currency = this.currency,
    name = this.name,
    countries = this.countries,
    languages = this.languages,
    numberFormat = this.numberFormat,
    monetaryFormat = this.monetaryFormat,
    logos = this.logos.map { it.toLogo() },
    mobileCarriers = this.mobileCarriers.map {
        if (carriers.containsKey(it))
            carriers[it]?.toMobileCarrier()
        else
            null
    }.filterNotNull(),
    limits = this.limits.toLimits()
)

fun LimitsEntity.toLimits() = Limits(
    minCashin = this.minCashin,
    minCashout = this.minCashout
)

fun LogoEntity.toLogo() = Logo(
    type = this.type,
    url = this.url
)

fun MobileCarrierEntity.toMobileCarrier() = MobileCarrier(
    code = this.code,
    name = this.name,
    countries = this.countries,
    logos = this.logos.map { it.toLogo() },
    phonePrefixes = this.phonePrefixes.keys.map {
        PhonePrefix(country = it, prefixes = this.phonePrefixes[it] ?: emptyList())
    }
)
