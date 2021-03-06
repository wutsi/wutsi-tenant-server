package com.wutsi.platform.tenant.delegate

import com.wutsi.platform.tenant.dto.Fee
import com.wutsi.platform.tenant.dto.Limits
import com.wutsi.platform.tenant.dto.Logo
import com.wutsi.platform.tenant.dto.MobileCarrier
import com.wutsi.platform.tenant.dto.PhonePrefix
import com.wutsi.platform.tenant.dto.Product
import com.wutsi.platform.tenant.dto.Tenant
import com.wutsi.platform.tenant.dto.TenantSummary
import com.wutsi.platform.tenant.dto.Toggle
import com.wutsi.platform.tenant.entity.FeeEntity
import com.wutsi.platform.tenant.entity.LimitsEntity
import com.wutsi.platform.tenant.entity.LogoEntity
import com.wutsi.platform.tenant.entity.MobileCarrierEntity
import com.wutsi.platform.tenant.entity.ProductEntity
import com.wutsi.platform.tenant.entity.TenantEntity
import com.wutsi.platform.tenant.entity.ToggleEntity
import org.springframework.core.env.Environment

fun TenantEntity.toTenantSummary() = TenantSummary(
    id = this.id,
    domainName = this.domainName,
    webappUrl = this.webappUrl,
    name = this.name,
)

fun TenantEntity.toTenant(carriers: Map<String, MobileCarrierEntity>, toggles: List<ToggleEntity>, env: Environment) =
    Tenant(
        id = this.id,
        domainName = this.domainName,
        supportEmail = this.supportEmail,
        currency = this.currency,
        installUrl = this.installUrl,
        installAndroidUrl = this.installAndroidUrl,
        installIOSUrl = this.installIOSUrl,
        webappUrl = this.webappUrl,
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
        limits = this.limits.toLimits(),
        currencySymbol = this.currencySymbol,
        dateFormat = this.dateFormat,
        timeFormat = this.timeFormat,
        dateTimeFormat = this.dateTimeFormat,
        fees = this.fees.map { it.toFee() },
        product = this.product.toProduct(),
        toggles = toggles.filter { it.isEnabled(this, env) }.map { it.toToggle() },
        testPhoneNumbers = this.testPhoneNumbers,
        testUserIds = this.testUserIds
    )

fun ToggleEntity.toToggle() = Toggle(
    name = this.name,
    description = this.description,
    environments = this.environments,
    tenantIds = this.tenantIds
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

fun FeeEntity.toFee() = Fee(
    transactionType = this.transactionType,
    percent = this.percent,
    amount = this.amount,
    applyToSender = this.applyToSender,
    paymentMethodType = this.paymentMethodType
)

fun ProductEntity.toProduct() = Product(
    defaultPictureUrl = this.defaultPictureUrl
)
