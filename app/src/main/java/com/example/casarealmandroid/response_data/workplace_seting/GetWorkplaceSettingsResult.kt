package com.example.casarealmandroid.response_data.workplace_seting

data class GetWorkplaceSettingsResult(
    val ErrorCode: Int,
    val ErrorText: Any,
    val FiscalDevice: FiscalDevice,
    val PaymentTypes: List<PaymentType>
)
