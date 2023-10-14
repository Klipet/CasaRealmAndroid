package com.example.casarealmandroid.response_data.user

data class GetUsersListResult(
    val ErrorCode: Int,
    val ErrorText: Any,
    val Users: List<UserX>
)
