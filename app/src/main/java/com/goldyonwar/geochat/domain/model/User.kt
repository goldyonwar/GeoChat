package com.goldyonwar.geochat.domain.model

data class User(
    val id: String = "",
    val email: String = "",
    val username: String = "",
    val avatar: String = "gyw_logo",
    val location: UserLocation? = null
)
