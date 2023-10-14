package com.example.casarealmandroid.response_data.asl

data class Assortiment(
    val Assortments: List<Assortment>,
    val ErrorCode: Int,
    val ErrorText: Any,
    val QuickGroups: Any
)
