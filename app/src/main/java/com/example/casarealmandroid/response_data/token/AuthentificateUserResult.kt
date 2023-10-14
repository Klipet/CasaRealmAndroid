package com.example.casarealmandroid.response_data.token

data class AuthentificateUserResult(
    val ErrorCode: Int?,
    val ErrorText: String?,
    val Token: String?,
    val TokenValidTo: String?
)
