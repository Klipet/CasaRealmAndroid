package com.example.casarealmandroid.response_data.asl

data class Promotion(
    val AllowDiscount: Boolean,
    val EndDate: String,
    val ID: String,
    val Price: Double,
    val StartDate: String,
    val TimeBegin: String,
    val TimeEnd: String
)
