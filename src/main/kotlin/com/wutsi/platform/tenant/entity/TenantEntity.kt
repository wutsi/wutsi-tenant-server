package com.wutsi.platform.tenant.entity

data class TenantEntity(
    var id: Long = 0,
    var name: String = "",
    var domainName: String = "",
    var supportEmail: String = "",
    var installUrl: String = "",
    var installAndroidUrl: String? = null,
    var installIOSUrl: String? = null,
    var webappUrl: String = "",
    var currency: String = "",
    var numberFormat: String = "",
    var monetaryFormat: String = "",
    var currencySymbol: String = "",
    var dateFormat: String = "",
    var timeFormat: String = "",
    var dateTimeFormat: String = "",
    var countries: List<String> = emptyList(),
    var languages: List<String> = emptyList(),
    var logos: List<LogoEntity> = emptyList(),
    var mobileCarriers: List<String> = emptyList(),
    var limits: LimitsEntity = LimitsEntity(),
    var fees: List<FeeEntity> = emptyList(),
    var product: ProductEntity = ProductEntity(),
    var testUserIds: List<Long> = emptyList(),
    var testPhoneNumbers: List<String> = emptyList()
)
