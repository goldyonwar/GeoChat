package com.goldyonwar.geochat.data.remote.dto

data class UserDto(
    val id: String = "",
    val email: String = "",
    val username: String = "",
    val avatar: String = "gyw_logo",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
