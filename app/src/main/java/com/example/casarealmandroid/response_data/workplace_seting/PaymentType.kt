package com.example.casarealmandroid.response_data.workplace_seting

data class PaymentType(
    val Code: String,
    val ExternalId: String,
    val Name: String,
    val PredefinedIndex: Int,
    val PrintFiscalCheck: Boolean
)
