package com.example.casarealmandroid.response_data.workplace

data class GetWorkplacesResult(
    val ErrorCode: Int,
    val ErrorText: String,
    val Workplaces: List<Workplace>
)
